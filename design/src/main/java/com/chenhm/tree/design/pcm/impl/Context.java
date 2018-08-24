package com.chenhm.tree.design.pcm.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author chen-hongmin
 * @date 2018/4/25 19:34
 * @since V1.0
 */
public class Context {


    private static ArrayBlockingQueue<Request> reqQueue = new ArrayBlockingQueue(1024);
    private static ArrayBlockingQueue<Response> resQueue = new ArrayBlockingQueue(1024);

    private static Map<Long, DefaultFuture> futureMap = new HashMap<>();


    public static DefaultFuture getFuture(Long futureId) {

        DefaultFuture defaultFuture = futureMap.get(futureId);

        return defaultFuture;
    }


    public static DefaultFuture sendRequest(Request request) {

        try {
            DefaultFuture defaultFuture = new DefaultFuture();
            futureMap.put(request.getId().get(), defaultFuture);
            reqQueue.put(request);

            return defaultFuture;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void sendResponse(Response response) {

        try {
            resQueue.put(response);
            DefaultFuture defaultFuture = futureMap.get(response.getId());
            defaultFuture.response(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static DefaultFuture getResponse() {

        try {
            Response take = resQueue.take();
            if (take != null) {
                return futureMap.get(take.getId());
            }
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Request getRequest() {

        try {
            Request take = reqQueue.take();

            return take;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

}
