package com.issac.interview.reflect;

/**
 * @author: ywy
 * @date: 2019-12-30
 * @desc:
 */
public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader cl = Robot.class.getClassLoader();
        //Class.forName("com.issac.interview.reflect.Robot");
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
