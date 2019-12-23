package com.issac.icu.guava;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-12-23
 * @desc:
 */
public class ImmutableTest {

    public static void test(List<Integer> list) {
        list.remove(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> newList = Collections.unmodifiableList(list);
        test(newList);
        System.out.println(newList);
    }

    public void immutable() {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        /**
         * 构造不可变集合对象三种方式
         */
        // 通过已经存在的集合创建
        ImmutableSet<Integer> immutableSet = ImmutableSet.copyOf(list);

        // 通过值
        ImmutableSet.of(1,2,3);

        // 通过构建器
        ImmutableSet.builder()
                .add(1).add(2).add(3).build();

    }
}
