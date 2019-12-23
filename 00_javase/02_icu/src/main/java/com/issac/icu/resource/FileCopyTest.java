package com.issac.icu.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc: JDK1.7之前文件拷贝功能
 */
public class FileCopyTest {

    @Test
    public void copyFile() {
        /**
         * 1. 创建输入输出流
         * 2. 执行文件拷贝，读取文件内容，写入到另一个文件中国呢
         * 3. 关闭文件资源
         */
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/FileCopyTest.java";

        // 声明文件输入流、输出流
        FileInputStream originFileInputStream = null;
        FileOutputStream targetFileOutputStream = null;

        try {
            // 实例化文件流对象
            originFileInputStream = new FileInputStream(originalUrl);
            targetFileOutputStream = new FileOutputStream(targetUrl);
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
        } finally {
            //关闭流资源
            if (targetFileOutputStream != null) {
                try {
                    targetFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (originFileInputStream != null) {
                try {
                    originFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
