package com.issac.icu.guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: ywy
 * @date: 2019-12-23
 * @desc:
 */
public class OptionalTest {

    @Test
    public void test() {
        /**
         * 三种创建 Optional对象方式
         */

        // 空
        Optional.empty();

        // 调用非null值引用
        Optional.of("issac");

        // 接收任何值
        Optional<Object> optional = Optional.ofNullable("issac");

        /**
         * 判断是否引用缺失方法(建议不直接使用)
         */
        optional.isPresent();

        /**
         * 当optional引用存在时执行
         * 类似的方法：map filter flatMap
         */
        optional.ifPresent(System.out::println);

        /**
         * 当optional引用缺失时执行
         */
        optional.orElse("引用缺失");

        optional.orElseGet(() -> {
            // 自定义引用缺失时返回值
            return "自定义引用缺失";
        });

        optional.orElseThrow(() -> {
            throw new RuntimeException("引用缺失异常");
        });
    }

    public static void stream(List<String> list) {
//        list.stream().forEach(System.out::println);
        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        stream(null);
    }
}
