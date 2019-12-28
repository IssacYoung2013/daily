package com.issac.icu.lombok;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
public class CleanupTest {

    public void copyFile(String in, String out) throws Exception {
        @Cleanup FileInputStream fileInputStream = new FileInputStream(in);
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(out);

        int r;
        while ((r = fileInputStream.read()) != -1) {
            fileOutputStream.write(r);
        }
    }
}
