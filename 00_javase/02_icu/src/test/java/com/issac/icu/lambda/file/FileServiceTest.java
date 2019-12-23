package com.issac.icu.lambda.file;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author: ywy
 * @date: 2019-12-22
 * @desc:
 */
public class FileServiceTest {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();
        fileService.fileHandle("/Users/Issac/workspace/java/" +
                        "daily/00_javase/02_icu/src/main/java/com/" +
                        "issac/icu/lambda/file/FileService.java",
                fileContent ->System.out.println(fileContent));
    }
}