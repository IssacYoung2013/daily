package com.issac.spring.designpattern.decorate;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class iPhoneDecorate implements Phone {

    /**
     * 被装饰的类
     */
    private iPhone6 phone;

    /**
     * 通过构造参数
     * @param phone
     */
    public iPhoneDecorate(iPhone6 phone) {
        this.phone = phone;
    }

    @Override
    public void call() {
        System.out.println("bell ring");
        this.phone.call();
    }
}
