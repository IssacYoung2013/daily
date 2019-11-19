package com.issac.mybatis.util;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
public class SQLUtil {

    public static String createSaveSql(Object obj) {
        // 1. 生成一个StringBuffer对象保存生成sql命令
        StringBuffer sql = new StringBuffer();

        // 2. 生成sql第一部分
        // 2.1 定位实体类对象隶属的类文件在内存的引用地址
        Class clazz = obj.getClass();
        String classPathName = clazz.getName();
        return "";
    }
}
