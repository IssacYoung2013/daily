package com.issac.icu.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author: ywy
 * @date: 2019-12-25
 * @desc: 演示 流 Source  汇 Sink 对文件操作
 */
public class IOTest {

    @Test
    public void copyFile() throws IOException {
        /**
         * 1. 创建对应的Source和Sink
         */
        CharSource charSource = Files.asCharSource(new File("lib/SourceText.txt"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(new File("targetTest/SinkText.txt"), Charsets.UTF_8);

        /**
         * 2. 拷贝
         */
//        charSource.copyTo(charSink);
    }
}
