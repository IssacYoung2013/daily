package com.issac.icu.resource;

import com.issac.icu.lambda.file.FileConsumer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc:
 */
public class ResourceCloseVs {
    /**
     * 实现通过url获取本地文件内容，调用函数式接口处理
     *
     * @param url
     * @param fileConsumer
     */
    public void oldFileHandle(String url, FileConsumer fileConsumer) throws IOException {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(url);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            // 定义行变量和内容sb
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            // 循环读取文件内容
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            // 调用函数式接口方法，将文件内容传给lambda表达式
            fileConsumer.fileHandler(stringBuilder.toString());
        } catch (Exception ex) {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    public void newFileHandle(String url, FileConsumer fileConsumer) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(url);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {

            // 定义行变量和内容sb
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            // 循环读取文件内容
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            // 调用函数式接口方法，将文件内容传给lambda表达式
            fileConsumer.fileHandler(stringBuilder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
