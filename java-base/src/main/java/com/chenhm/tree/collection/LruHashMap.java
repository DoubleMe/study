package com.chenhm.tree.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * linkedHashMap 用作LRU 缓存
 *
 * @author chen-hongmin
 * @date 2018/8/24 9:44
 * @since V1.0
 */
public class LruHashMap<K, V> extends LinkedHashMap {

    /**
     * 加载因子是表示hash表中元素的填满的程度。
     * 加载因子越大,填满的元素越多,空间利用率越高，但冲突的机会加大了。
     * 反之,加载因子越小,填满的元素越少,冲突的机会减小,但空间浪费多了。
     * <p>
     * 冲突的机会越大,则查找的成本越高。反之,查找的成本越小。
     * 因此,必须在 "冲突的机会"与"空间利用率"之间寻找一种平衡与折衷
     * <p>
     * <p>
     * 在理想情况下,使用随机哈希码,节点出现的频率在hash桶中遵循泊松分布
     * 泊松分布知识：http://www.ruanyifeng.com/blog/2015/06/poisson-distribution.html#comment-356111
     * <p>
     * 负载因子是0.75的情况下 链表长度超过８个是几乎不可能的,具体见下面对照表
     * <p>
     * 0: 0.60653066
     * 1: 0.30326533
     * 2: 0.07581633
     * 3: 0.01263606
     * 4: 0.00157952
     * 5: 0.00015795
     * 6: 0.00001316
     * 7: 0.00000094
     * 8: 0.00000006
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;


    private int CACHE_SIZE;

    public LruHashMap(int cacheSize) {

        super(cacheSize, DEFAULT_LOAD_FACTOR, true);
        this.CACHE_SIZE = cacheSize;
    }

    public LruHashMap(int cacheSize, float loadFactor) {

        super(cacheSize, loadFactor, true);
        this.CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {

        return size() > CACHE_SIZE;
    }
}
