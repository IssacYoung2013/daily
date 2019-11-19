package com.issac.webflux.common.util;

import com.issac.webflux.common.exception.StudentException;

import java.util.stream.Stream;

/**
 * @author: ywy
 * @date: 2019-11-06
 * @desc:
 */
public class NameValidateUtil {


    public static final String[] INVALIDATE_NAMES = {"admin", "administrator"};

    public static void validateName(String name) {

        Stream.of(INVALIDATE_NAMES)
                .filter(it -> it.equalsIgnoreCase(name))
                .findAny()
                .ifPresent(it -> {
                    throw new StudentException("name", it, "使用了非法姓名");
                });
    }
}
