package com.issac.algo;

import java.io.Serializable;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc:
 */
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student that) {
        if (this.age < that.age) {
            return -1;
        } else if (this.age > that.age) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
