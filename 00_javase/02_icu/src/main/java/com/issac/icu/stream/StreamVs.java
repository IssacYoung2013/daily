package com.issac.icu.stream;

import com.alibaba.fastjson.JSON;
import com.issac.icu.lambda.cart.CartService;
import com.issac.icu.lambda.cart.Sku;
import com.issac.icu.lambda.cart.SkuCategoryEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc: 对比：原始集合操作和Stream集合操作
 */
public class StreamVs {

    /**
     * 原始集合
     */
    public void oldCartHandle() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        /**
         * 1.打印所有商品
         */
        for (Sku sku : cartSkuList) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        /**
         * 2. 图书类过滤掉
         */
        List<Sku> notBooksSkuList = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())) {
                notBooksSkuList.add(sku);
            }
        }

        /**
         * 排序
         */
        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku sku1, Sku sku2) {
                return -sku1.getTotalPrice().compareTo(sku2.getTotalPrice());
            }
        });

        /**
         * top2
         */
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBooksSkuList.get(i));
        }

        /**
         * 求总价
         */
        Double money = 0.0;
        for (Sku sku : top2SkuList) {
            money += sku.getTotalPrice();
        }

        /**
         * 获取两件商品名称
         */
        List<String> resultSkuNameList = new ArrayList<>();
        for (Sku sku : top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);
    }

    /**
     * Stream
     */
    public void newCartHandle() {

        List<Sku> cartSkuList = CartService.getCartSkuList();
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));

        List<String> resultSkuNameList = cartSkuList.stream()
                /**
                 * 打印商品信息
                 */
                .peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                /**
                 * 过滤掉所有图书类商品
                 */
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                /**
                 * 排序
                 */
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                /**
                 * TOP2
                 */
                .limit(2)
                /**
                 * 累加商品总金额
                 */
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                /**
                 * 收集商品名称
                 */
                .map(sku -> sku.getSkuName())
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money.get());
    }
}
