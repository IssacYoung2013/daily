package com.issac.jvm.charpter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-11-20
 * @desc: VM args: -Xms20m -Xmx20m 参数设置的一样即可避免自动扩展
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
