package com.issac.icu.lambda.file;

import java.io.*;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc:
 */
public class FileService {
    /**
     * 实现通过url获取本地文件内容，调用函数式接口处理
     *
     * @param url
     * @param fileConsumer
     */
    public void fileHandle(String url, FileConsumer fileConsumer) throws IOException {
        // 创建文件读取流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
        // 定义行变量和内容sb
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        // 循环读取文件内容
        while ((line = bufferedReader.readLine())!= null) {
            stringBuilder.append(line + "\n");
        }
        // 调用函数式接口方法，将文件内容传给lambda表达式
        fileConsumer.fileHandler(stringBuilder.toString());
    }
}
