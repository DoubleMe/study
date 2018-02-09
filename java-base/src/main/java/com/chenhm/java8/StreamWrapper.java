package com.chenhm.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chen-hongmin
 * @date 2018/2/7 16:50
 * @since V1.0
 */
public class StreamWrapper {

    /**
     * 将T 装换为R
     *
     * @param list     T 集合
     * @param function 装换函数
     * @param <T>      待转类型
     * @param <R>      目标类型
     * @return List<R>
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {

        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        List<R> collect = list.stream().map(function).collect(Collectors.toList());

        return collect;
    }

    /**
     * 将T 装换为R
     *
     * @param target
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Optional<R> map(T target, Function<T, R> function) {

        if (target == null) {
            return Optional.empty();
        }

        Stream<R> stream = Stream.of(target).map(function);

        return stream.findFirst();
    }

    /**
     * 数据过滤
     *
     * @param list      目标集合
     * @param predicate 过滤器
     * @return
     */
    public static <T> List<T> conditionFilter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * list join delimiter
     *
     * @param list      list<String> 目标集合
     * @param delimiter 分割符号
     * @param prefix    前缀
     * @param suffix    后缀
     * @return string
     */
    public static <T> String joining(List<T> list, Function<T, String> function, CharSequence delimiter,
                                     CharSequence prefix,
                                     CharSequence suffix) {

        if (list == null || list.isEmpty()) {
            return "";
        }
        String collect = list.stream().map(function).collect(Collectors.joining(delimiter, prefix, suffix));

        return collect;
    }

    /**
     * list join delimiter
     *
     * @param list      list<String> 目标集合
     * @param delimiter 分割符号
     * @param prefix    前缀
     * @param suffix    后缀
     * @return string
     */
    public static String joining(List<String> list, CharSequence delimiter,
                                 CharSequence prefix,
                                 CharSequence suffix) {

        if (list == null || list.isEmpty()) {
            return "";
        }
        Collector<CharSequence, ?, String> joining = Collectors.joining(delimiter, prefix, suffix);
        String collect = list.stream().collect(joining);

        return collect;
    }

    /**
     * list 转为map
     * @param list 目标集合
     * @param collector
     * @param <K>
     * @param <T>
     * @return Map<K, List<T>>
     */
    public static<K,T> Map<K, List<T>> toMap(List<T> list, Collector<T, ?, Map<K, List<T>>> collector) {

        Map<K, List<T>> map = list.stream().collect(collector);

        return map;
    }


}
