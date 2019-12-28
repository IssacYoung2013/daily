package com.issac.icu.lombok;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
public class SetterTest {

    @Setter
    private String field1;

    @Setter(
            value = AccessLevel.PRIVATE,
            onMethod = {},
            onParam = {}
    )
    private String field2;
}
