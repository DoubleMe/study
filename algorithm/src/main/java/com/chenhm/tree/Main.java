package com.chenhm.tree;

import com.chenhm.tree.impl.BinarySortTree;

import java.util.TreeMap;

/**
 * @author chen-hongmin
 * @date 2018/8/24 14:17
 * @since V1.0
 */
public class Main {

    public static void main(String[] args) {

        BinarySortTree<Integer,Integer> tree = new BinarySortTree<>();
        TreeMap<Integer,Integer> map = new TreeMap<>();
        long s = System.currentTimeMillis();

        tree.put(2,2);
        tree.put(20,20);
        tree.put(10,10);
        tree.put(11,11);
        tree.put(9,9);
        tree.put(1,1);

        long e = System.currentTimeMillis();

        tree.delete(2);
        System.out.println(tree.get(20));
        System.out.println(tree);
    }
}
