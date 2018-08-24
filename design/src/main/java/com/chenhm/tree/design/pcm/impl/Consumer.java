package com.chenhm.tree.design.pcm.impl;

/**
 * @author chen-hongmin
 * @date 2018/4/25 18:51
 * @since V1.0
 */
public class Consumer implements Runnable {

    @Override
    public void run() {
        while (true){
            Request request = Context.getRequest();
            if (request != null){
                Response response = new Response(request.getId().get(),request.getMessage());
                Context.sendResponse(response);
            }
        }
    }
}
