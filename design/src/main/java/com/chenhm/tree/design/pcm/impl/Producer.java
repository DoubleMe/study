package com.chenhm.tree.design.pcm.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chen-hongmin
 * @date 2018/4/25 18:50
 * @since V1.0
 */
public class Producer implements Runnable {

    private AtomicLong count = new AtomicLong(0);

    @Override
    public void run() {

        while (count.getAndIncrement() < 10) {

            System.out.println(Thread.currentThread().getName() + "send message 你好" + count.get());
            DefaultFuture defaultFuture = Context.sendRequest(new Request("你好"));
            Response response = (Response) defaultFuture.get();
            System.out.println(Thread.currentThread().getName() + "recevied message " + response.getMessage() + response.getId());
        }
    }
}
