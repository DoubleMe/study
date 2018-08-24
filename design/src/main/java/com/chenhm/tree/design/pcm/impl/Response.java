package com.chenhm.tree.design.pcm.impl;

/**
 * @author chen-hongmin
 * @date 2018/4/25 19:16
 * @since V1.0
 */
public class Response {

    private Long id;

    private String message;

    public Response(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
