package com.issac.icu.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;


/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class CartServiceTest {

    @Test
    public void filterElectronicsSkus() {
        List<Sku> elecSkus = CartService.filterElectronicsSkus(CartService.getCartSkuList());
        System.out.println(JSON.toJSONString(elecSkus,true));
    }

    @Test
    public void filterSkusByCategory() {
        List<Sku> elecSkus = CartService.filterSkusByCategory(CartService.getCartSkuList(),SkuCategoryEnum.ELECTRONICS);
        System.out.println(JSON.toJSONString(elecSkus,true));
    }

    @Test
    public void filterSkus() {
        List<Sku> elecSkus = CartService.filterSkus(CartService.getCartSkuList(), new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
                return SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory());
            }
        });
        System.out.println(JSON.toJSONString(elecSkus,true));
    }

    @Test
    public void filterSkusLambda() {
        List<Sku> elecSkus = CartService.filterSkus(CartService.getCartSkuList(),
                (Sku sku)-> SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory()));
        System.out.println(JSON.toJSONString(elecSkus,true));
    }
}