package com.chenhm.java8.lambda;

/**
 * @author chen-hongmin
 * @date 2018/2/9 11:15
 * @since V1.0
 */
public class BaseConvert {

    public static <T,R> R convert(T t,Convert<T,R> convert){

        R r = convert.convert(t);
        return r;
    }
}
