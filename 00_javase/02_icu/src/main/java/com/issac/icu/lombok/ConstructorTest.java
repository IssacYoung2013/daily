package com.issac.icu.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class ConstructorTest {

    private final String field1;

    @NonNull
    private String field2;
    private String field3;
}
