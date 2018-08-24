package com.chenhm.tree.design.pcm;

import com.chenhm.tree.design.pcm.impl.Consumer;
import com.chenhm.tree.design.pcm.impl.Producer;

/**
 * 生产者 消费者模型
 *
 * @author chen-hongmin
 * @date 2018/4/25 20:06
 * @since V1.0
 */
public class Main {

    public static void main(String[] args) {

        Thread producer1 = new Thread(new Producer());
        Thread producer2 = new Thread(new Producer());
        Thread producer3 = new Thread(new Producer());
        Thread producer4 = new Thread(new Producer());
        Thread producer5 = new Thread(new Producer());
        Thread producer6 = new Thread(new Producer());

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();


        Thread consumer1 = new Thread(new Consumer());
        Thread consumer2 = new Thread(new Consumer());
        Thread consumer3 = new Thread(new Consumer());
        Thread consumer4 = new Thread(new Consumer());
        Thread consumer5 = new Thread(new Consumer());

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
    }
}
