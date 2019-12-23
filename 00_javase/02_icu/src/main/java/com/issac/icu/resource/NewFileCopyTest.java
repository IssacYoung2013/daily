package com.issac.icu.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc: JDK1.7后文件拷贝
 */
public class NewFileCopyTest {

    @Test
    public void copyFile() {
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/FileCopyTest.java";

        try (
                FileInputStream originFileInputStream = new FileInputStream(originalUrl);
                FileOutputStream targetFileOutputStream = new FileOutputStream(targetUrl);
        ) {
            // 读取的字节信息
            int content;
            // 迭代，读取写入字节
            while ((content = originFileInputStream.read()) != -1) {
                targetFileOutputStream.write(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
