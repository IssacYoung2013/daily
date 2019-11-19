package com.issac.bfxy.order.constant;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public enum OrderStatus {
    ORDER_CREATED("1"),
    ORDER_PAYED("2"),
    ORDER_FAILED("3");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
