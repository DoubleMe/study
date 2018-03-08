package com.chenhm.concurrent;

/**
 * 线程join 方法测试
 * <p>线程的join方法会使线程独占锁,优先执行本线程</p>
 *
 * @author chen-hongmin
 * @date 2018/3/8 11:20
 * @since V1.0
 */
public class ThreadTest {


    class Thread1 extends Thread {

        public Thread1(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++){

                System.out.println(getName() + i);
            }
        }
    }


    public static void main(String[] args) throws Exception{

        ThreadTest threadTest = new ThreadTest();

        ThreadTest.Thread1 thread1 = threadTest.new Thread1("testA");
        ThreadTest.Thread1 thread2 = threadTest.new Thread1("testB");
        ThreadTest.Thread1 thread3 = threadTest.new Thread1("testC");
        ThreadTest.Thread1 thread4 = threadTest.new Thread1("testD");

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        thread4.start();
    }
}
