package com.chenhm.tree.java8.lambda;

/**
 * @author chen-hongmin
 * @date 2018/2/9 11:13
 * @since V1.0
 */
@FunctionalInterface
public interface Convert<T,R> {

    /**
     * 对象转换
     * @param t
     * @return R
     */
    R convert(T t);
}
