package com.issac.icu.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ywy
 * @date: 2019-12-26
 * @desc:
 */
public class GetterTest {

    @Getter(
            lazy = true
    )
    private final String field1 = "issac";

    @Getter(
            value = AccessLevel.PRIVATE,
            onMethod = {}
    )
    private String field2;
}
