package com.issac.icu.lombok;

import lombok.EqualsAndHashCode;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
@EqualsAndHashCode(
        exclude = "field1"
)
public class EqualsAndHashCodeTest {
    private String field1;
    private String field2;
}
