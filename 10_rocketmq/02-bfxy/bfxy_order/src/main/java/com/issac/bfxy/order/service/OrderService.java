package com.issac.bfxy.order.service;

/**
 *
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public interface OrderService {

    boolean createOrder(String cityId, String platformId, String userId, String suppliedId, String goodsId);

    void sendOrderlyMessage4Pkg(String userId, String orderId);
}
