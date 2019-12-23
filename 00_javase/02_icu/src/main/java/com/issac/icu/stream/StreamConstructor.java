package com.issac.icu.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc:
 */
public class StreamConstructor {

    /**
     * 数值直接构成流
     */
    @Test
    public void streamFromValue() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        stream.forEach(System.out::println);
    }

    /**
     * 数组
     */
    @Test
    public void streamFromArray() {
        int[] nums = {1, 3, 4, 5, 6};
        Arrays.stream(nums).forEach(System.out::println);
    }

    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("/Users/Issac/workspace/" +
                "java/daily/00_javase/02_icu/src/main/java/com/" +
                "issac/icu/stream/StreamConstructor.java"));
        stream.forEach(System.out::println);
    }

    /**
     * 函数 limit 限制个数
     */
    @Test
    public void streamFromFunction() {
//        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        Stream stream = Stream.generate(Math::random);
        stream.limit(100).forEach(System.out::println);
    }
}
