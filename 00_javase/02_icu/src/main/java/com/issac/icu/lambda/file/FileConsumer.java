package com.issac.icu.lambda.file;

/**
 * @author: ywy
 * @date: 2019-12-21
 * @desc: 文件处理函数式接口
 */
@FunctionalInterface
public interface FileConsumer {
    /**
     * 函数式接口抽象方法
     *
     * @param fileContent
     */
    void fileHandler(String fileContent);
}
