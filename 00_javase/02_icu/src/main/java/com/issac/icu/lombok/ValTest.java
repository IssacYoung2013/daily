package com.issac.icu.lombok;

import lombok.var;

import java.util.ArrayList;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
public class ValTest {

    public ValTest() {
        var field = "issac";
        var list = new ArrayList<String>();
        list.add("issacyoung");
    }
}
