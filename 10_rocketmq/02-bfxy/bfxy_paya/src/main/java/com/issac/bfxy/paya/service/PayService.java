package com.issac.bfxy.paya.service;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public interface PayService {
    String payment(String userId,String orderId,String accountId,double money);
}
