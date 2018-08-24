package com.chenhm.tree.collection;

import java.util.Collection;

/**
 * @author chen-hongmin
 * @date 2018/8/24 10:24
 * @since V1.0
 */
public class Main {

    public static void main(String[] args) {

        LruHashMap lruMap = new LruHashMap(6);
        lruMap.put("k1",1);
        lruMap.put("k2",2);
        lruMap.put("k3",3);
        lruMap.put("k4",4);
        lruMap.put("k5",5);
        lruMap.put("k6",6);
        lruMap.put("k7",7);
        lruMap.put("k8",8);
        lruMap.put("k9",9);
        lruMap.put("k10",10);
        lruMap.put("k11",11);
        lruMap.put("k12",12);
        lruMap.put("k13",13);

        lruMap.get("k10");

        Collection values = lruMap.values();

        System.out.println(values);

    }
}
