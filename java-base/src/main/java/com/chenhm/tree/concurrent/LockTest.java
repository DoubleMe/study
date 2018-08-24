package com.chenhm.tree.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen-hongmin
 * @date 2018/3/5 16:20
 * @since V1.0
 */
public class LockTest {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition con = lock.newCondition();

    public static void main(String[] args) throws Exception {

        start();
        start1();
        System.in.read();

    }

    public static void start(){


        Thread thread = new Thread(() -> {
            System.out.println("process-pre : " + lock.isLocked());
            lock.lock();
            System.out.println("process-lock : " + lock.isLocked());
            try {
                Long startTime = System.currentTimeMillis();
                con.await(10000000, TimeUnit.MICROSECONDS);
                Long endTime = System.currentTimeMillis();

                System.out.println("process-await : " + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread.start();
    }

    public static void start1(){

        Thread thread = new Thread(() -> {

            lock.lock();
            try {
                con.signal();

            } finally {
                lock.unlock();
            }
        });
        thread.start();
    }
}
