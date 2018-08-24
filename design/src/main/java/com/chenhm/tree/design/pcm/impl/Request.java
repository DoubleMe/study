package com.chenhm.tree.design.pcm.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chen-hongmin
 * @date 2018/4/25 19:14
 * @since V1.0
 */
public class Request {

    private static AtomicLong id = new AtomicLong(0);

    private String message;

    public Request(String message) {
        id.getAndIncrement();
        this.message = message;
    }

    public AtomicLong getId() {
        return id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
