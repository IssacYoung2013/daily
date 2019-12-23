package com.issac.icu.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Chars;
import org.junit.Test;

import java.util.List;

/**
 * @author: ywy
 * @date: 2019-12-23
 * @desc:
 */
public class MultisetTest {

    private static final String text = "南陵别儿童入京⑴\n" +
            "白酒新熟山中归⑵，黄鸡啄黍秋正肥。\n" +
            "呼童烹鸡酌白酒，儿女嬉笑牵人衣⑶。\n" +
            "高歌取醉欲自慰，起舞落日争光辉⑷。\n" +
            "游说万乘苦不早⑸，著鞭跨马涉远道。\n" +
            "会稽愚妇轻买臣⑹，余亦辞家西入秦⑺。\n" +
            "仰天大笑出门去，我辈岂是蓬蒿人⑻。";

    @Test
    public void handle() {
        Multiset<Character> multiset = HashMultiset.create();
        // string 转成 char 数组
        char[] chars = text.toCharArray();
        List<Character> characters = Chars.asList(chars);
        // 遍历数组，添加 multiset
        characters.stream()
                .forEach(item -> {
                    multiset.add(item);
                });
        System.out.println("size:" + multiset.size());
        System.out.println("count:" + multiset.count('人'));


    }
}
