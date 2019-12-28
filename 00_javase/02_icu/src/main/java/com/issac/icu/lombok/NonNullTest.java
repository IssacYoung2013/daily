package com.issac.icu.lombok;

import lombok.NonNull;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
public class NonNullTest {

    public NonNullTest(@NonNull String field) {
        System.out.println("构造函数");
    }
}
