package com.issac.interview.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class ReflectSample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> rc = Class.forName("com.issac.interview.reflect.Robot");
        Robot o = (Robot) rc.newInstance();
        System.out.println("Class name is " + rc.getName());
        Method method = rc.getDeclaredMethod("throwHello", String.class);
        method.setAccessible(true);
        Object robot = method.invoke(o, "robot");
        System.out.println(robot);
    }
}
