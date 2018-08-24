package com.chenhm.tree.spi;

import java.util.ServiceLoader;

/**
 * @author chen-hongmin
 * @date 2018/4/8 14:28
 * @since V1.0
 */
public class JavaSpi {

    public static void main(String[] args) {

        //参数是Class 接口的class
        //返回结果是一个ServiceLoader（继承了Iterable）
        ServiceLoader<DemoService> load = ServiceLoader.load(DemoService.class);

        //遍历services 调用
        for (DemoService demoService : load){
            demoService.sayHello();
        }
    }
}
