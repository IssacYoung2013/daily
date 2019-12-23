package com.issac.icu.lambda.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class CartService {
    private static List<Sku> cartSkuList = new ArrayList<Sku>() {
        {
            add(new Sku(654032, "无人机",
                    4999.00, 1,
                    4999.00, SkuCategoryEnum.ELECTRONICS));
            add(new Sku(642934, "VR一体机",
                    2299.00, 1,
                    2299.00, SkuCategoryEnum.ELECTRONICS));
            add(new Sku(654321, "纯色衬衫",
                    409.00, 1,
                    409.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(654327, "牛仔裤",
                    528.00, 1,
                    528.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(675489, "跑步机",
                    2699.00, 1,
                    2699.00, SkuCategoryEnum.SPORTS));
            add(new Sku(644564, "Java编程思想",
                    79.80, 1,
                    79.80, SkuCategoryEnum.BOOKS));
            add(new Sku(644569, "Java核心技术",
                    149.80, 1,
                    149.80, SkuCategoryEnum.BOOKS));
            add(new Sku(644571, "算法",
                    78.80, 1,
                    78.80, SkuCategoryEnum.BOOKS));
            add(new Sku(644599, "TensorFlow进阶指南",
                    85.80, 1,
                    85.80, SkuCategoryEnum.BOOKS));

        }
    };

    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }

    /**
     * Version 1.0.0
     *
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuList) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    public static List<Sku> filterSkusByCategory(List<Sku> cartSkuList, SkuCategoryEnum category) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (category.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * 类似策略模式
     * @param cartSkuList
     * @param predicate 不同的sku判断策略
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuPredicate predicate) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (predicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
}
