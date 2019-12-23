package com.issac.icu.lambda.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
@Data
@AllArgsConstructor
public class Sku {
    private Integer skuId;
    private String skuName;
    private Double skuPrice;
    private Integer totalNum;
    private Double totalPrice;
    private Enum skuCategory;
}
