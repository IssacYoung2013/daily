package com.issac.icu.lambda.cart;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public interface SkuPredicate {
    /**
     * 选择判断标准
     *
     * @param sku
     * @return
     */
    boolean test(Sku sku);
}
