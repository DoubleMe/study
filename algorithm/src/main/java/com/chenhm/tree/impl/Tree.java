package com.chenhm.tree.impl;

/**
 * @author chen-hongmin
 * @date 2018/8/24 10:58
 * @since V1.0
 */
public interface Tree<K, V> {

    /**
     * add a value
     * if value is exist not process
     *
     * @param value
     * @param key
     */
    void put(K key, V value);

    /**
     * delete a value
     *
     * @param key
     */
    void delete(K key);

    /**
     * get the value of key
     *
     * @param key
     * @return if key not exist return null
     */
    V get(K key);

    /**
     * the size of node
     *
     * @return
     */
    long size();

}
