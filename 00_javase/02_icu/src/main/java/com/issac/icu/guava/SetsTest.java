package com.issac.icu.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author: ywy
 * @date: 2019-12-24
 * @desc:
 */
public class SetsTest {

    /**
     * Sets 工具类
     * 并集 / 交集 / 差集 / 所有子集 / 笛卡尔
     * <p>
     * Lists 工具类
     * 反转、拆分
     */

    private static final Set set1 = Sets.newHashSet(1, 2, 3, 4);

    private static final Set set2 = Sets.newHashSet(4, 5, 6);

    private static final Set set3 = Sets.newHashSet(4);


    @Test
    public void union() {
        Set<Integer> set = Sets.union(set1, set2);
        System.out.println(set);
    }

    @Test
    public void intersection() {
        Set<Integer> set = Sets.intersection(set1, set2);
        System.out.println(set);
    }

    @Test
    public void difference() {
        Set<Integer> set = Sets.difference(set1, set2);
        System.out.println(set);

        // 相对差集：属于A而且不属于B 或者 属于B而且不属于A
        Set<Integer> set3 = Sets.symmetricDifference(set1, set2);
        System.out.println(set3);
    }


    @Test
    public void powerSet() {
        Set<Set<Integer>> set = Sets.powerSet(set1);
        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void cartesianProduct() {
        Set set = Sets.cartesianProduct(set1, set3);
        System.out.println(JSON.toJSONString(set));
    }

    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);

    @Test
    public void partition() {
        List<List<Integer>> partition = Lists.partition(list,3);
        System.out.println(JSON.toJSONString(partition));
    }

    @Test
    public void reverse() {
        list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> reverse = Lists.reverse(list);
        System.out.println(reverse);
    }
}
