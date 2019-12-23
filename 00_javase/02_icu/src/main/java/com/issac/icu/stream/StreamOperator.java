package com.issac.icu.stream;

import com.issac.icu.lambda.cart.CartService;
import com.issac.icu.lambda.cart.Sku;
import com.issac.icu.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc:
 */
public class StreamOperator {

    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    @Test
    public void filterTest() {
        list.stream()
                /**
                 * filter
                 */
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        list.stream()
                /**
                 * map 对象转换成对象
                 */
                .map(sku -> sku.getSkuName())
                .forEach(System.out::println);
    }

    @Test
    public void flatMapTest() {
        list.stream()
                /**
                 * flatMap 对象转换成流
                 */
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(System.out::println);
    }

    @Test
    public void peekTest() {
        list.stream()
                /**
                 * peek forEach 交替执行,流是惰性执行
                 */
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(System.out::println);
    }

    @Test
    public void sortTest() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                /**
                 * sort
                 */
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .forEach(System.out::println);
    }

    @Test
    public void distinctTest() {
        list.stream()
                .map(sku -> sku.getSkuCategory())
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void skipTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .skip(3)
                .forEach(System.out::println);
    }

    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .skip(2 * 3)
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void allMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // allMatch
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    @Test
    public void anyMatch() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // anyMatch
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    @Test
    public void noneMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // noneMatch
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);
        System.out.println(match);
    }

    @Test
    public void findFirstTest() {
        Optional<Sku> sku = list.stream()
                .findFirst();
        System.out.println(sku.get());
    }

    /**
     * 并行处理同findFirst区别
     */
    @Test
    public void findAnyTest() {
        Optional<Sku> sku = list.stream()
                .findAny();
        System.out.println(sku.get());
    }

    @Test
    public void maxTest() {
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    @Test
    public void minTest() {
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    @Test
    public void countTest() {
        long count = list.stream()
                .count();
        System.out.println(count);
    }
}
