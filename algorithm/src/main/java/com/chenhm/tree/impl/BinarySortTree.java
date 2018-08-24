package com.chenhm.tree.impl;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 二叉搜索树
 * <p>
 * 二叉排序树的性能取决于二叉树的层数：
 * 最好的情况是 O(logn)，存在于完全二叉排序树情况下，其访问性能近似于折半查找；
 * 最差时候会是 O(n)，比如插入的元素是有序的，生成的二叉排序树就是一个链表，这种情况下，需要遍历全部元素才行
 * </p>
 *
 * @author chen-hongmin
 * @date 2018/8/24 10:43
 * @since V1.0
 */
public class BinarySortTree<K, V> implements Tree<K, V> {

    private BinarySortNode<K, V> root;

    private Comparator<K> comparator;

    private AtomicLong size = new AtomicLong(1);

    private class BinarySortNode<K, V> {

        BinarySortNode<K, V> left;

        BinarySortNode<K, V> right;

        BinarySortNode<K, V> parent;

        volatile V value;

        K key;

        public BinarySortNode(K key, V value) {

            this.value = value;
            this.key = key;
        }


        @Override
        public String toString() {

            return key + "=" + value;
        }
    }

    public BinarySortTree() {

    }

    public BinarySortTree(Comparator<K> comparator) {

        this.comparator = comparator;
    }

    @Override
    public void put(K key, V value) {

        if (root == null) {
            root = new BinarySortNode<>(key, value);
        } else {

            BinarySortNode<K, V> cNode = root;
            BinarySortNode<K, V> lNode;
            for (; ; cNode = lNode) {
                //查找下一个node 如果相等重置当前 node的value
                if (cNode.key.equals(key)) {
                    cNode.value = value;
                    return;
                }
                lNode = findNode(key, cNode);
                //下一节点没有数据,将一下节点置为新节点
                if (lNode == null) {
                    addNode(key, value, cNode);
                    return;
                }
            }
        }
    }

    /**
     * 通过key 查找下一个节点
     *
     * @param key  key
     * @param node 当前node
     * @return node 的下一节点
     */
    private BinarySortNode<K, V> findNode(K key, BinarySortNode<K, V> node) {

        if (comparator != null) {
            return comparator.compare(node.key, key) > 0 ? node.right : node.left;
        } else {
            Comparable<? super K> comparable = (Comparable<? super K>) key;
            return comparable.compareTo(node.key) > 0 ? node.right : node.left;
        }

    }

    /**
     * 添加节点
     *
     * @param key   key
     * @param value value
     * @param node  单签node
     */
    private void addNode(K key, V value, BinarySortNode<K, V> node) {

        BinarySortNode<K, V> newNode = new BinarySortNode<>(key, value);
        if (comparator != null) {
            if (comparator.compare(node.key, key) > 0) {
                node.right = newNode;

            } else {
                node.left = newNode;
            }
        } else {
            Comparable<? super K> comparable = (Comparable<? super K>) key;
            if (comparable.compareTo(node.key) > 0) {
                node.right = newNode;
            } else {
                node.left = newNode;
            }
        }
        newNode.parent = node;
        size.incrementAndGet();
    }

    @Override
    public void delete(K key) {

        BinarySortNode<K, V> node = findByKey(key);
        if (node == null) {
            return;
        }
        size.decrementAndGet();
        //要删除的是根节点
        if (node == root) {
            BinarySortNode left = root.left;
            BinarySortNode right = root.right;
            //根节点左边不为空,将左边 设为根节点,并将左边父节点设置为null
            if (left != null) {
                root = root.left;
                root.parent = null;
                BinarySortNode<K, V> rightNode = maxLeafNode(root);
                //left 右边为空,直接将 root 右边接上
                if (rightNode == null) {
                    root.right = right;
                } else {
                    rightNode.right = right;
                }
            } else {
                //root 等于右边
                root = right;
                //右边不等于null 的话 需要将right的父节点 置为null，以便root可以被垃圾回收
                if (right != null) {
                    right.parent = null;
                }
            }
        } else {

            BinarySortNode<K, V> left = node.left;
            BinarySortNode<K, V> parent = node.parent;
            //左节点为空
            if (left == null) {
                if (parent.left == node) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
                //右节点不为空 将右节点的父亲 设置为 当前node.parent
                if (node.right != null) {
                    node.right.parent = parent;
                }
            } else {

                BinarySortNode<K, V> rightNode = maxLeafNode(left);

                if (parent.left == node) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
                //右节点为空,直接将 root 右边接上
                if (rightNode != null) {
                    rightNode.right = node.right;
                }
                left.parent = parent;
            }
        }
    }

    /**
     * 获取最大的叶子节点
     *
     * @param node
     * @return
     */
    private BinarySortNode<K, V> maxLeafNode(BinarySortNode<K, V> node) {

        for (BinarySortNode<K, V> leafNode = node; ; leafNode = leafNode.right) {
            if (leafNode.right == null) {
                return leafNode;
            }
        }
    }

    /**
     * 获取最大的叶子节点
     *
     * @param node
     * @return
     */
    private BinarySortNode<K, V> minLeafNode(BinarySortNode<K, V> node) {

        System.out.println(node.toString());
        for (BinarySortNode<K, V> leafNode = node; ; leafNode = leafNode.left) {
            if (leafNode.left == null) {
                return leafNode;
            }
        }
    }

    @Override
    public V get(K key) {

        BinarySortNode<K, V> node = findByKey(key);
        return node == null ? null : node.value;
    }

    /**
     * 查找 node.key == key
     *
     * @param key key
     * @return
     */
    private BinarySortNode<K, V> findByKey(K key) {

        BinarySortNode<K, V> vNode = root;

        while (vNode != null) {

            if (vNode.key.equals(key)) {
                return vNode;
            }
            vNode = findNode(key, vNode);
        }
        return null;
    }

    @Override
    public long size() {

        return size.get();
    }

    BinarySortNode<K, V> next(BinarySortNode<K, V> node) {

        BinarySortNode<K, V> leftNode = minLeafNode(node);

        if (leftNode != null) {
            return leftNode;
        }

        return maxLeafNode(node);
    }


}
