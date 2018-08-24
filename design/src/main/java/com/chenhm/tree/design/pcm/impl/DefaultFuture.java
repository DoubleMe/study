package com.chenhm.tree.design.pcm.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen-hongmin
 * @date 2018/4/25 19:18
 * @since V1.0
 */
public class DefaultFuture {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private volatile Request request;

    private volatile Response response;

    private static final long DEFAULT_TIMEOUT = 10000;


    public boolean isDone() {
        return response != null;
    }


    public Object get() {

        try {
            return get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object get(long timeout, TimeUnit unit) throws TimeoutException {

        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            while (!isDone()) {
                try {
                    condition.await(timeout, unit);
                    if (isDone() && System.currentTimeMillis() - start < timeout) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
        return response;
    }

    public void response(Response response) {

        lock.lock();
        try {
            this.response = response;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
