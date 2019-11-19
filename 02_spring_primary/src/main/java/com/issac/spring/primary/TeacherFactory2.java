package com.issac.spring.primary;

import com.issac.spring.beans.Teacher;

/**
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
public class TeacherFactory2 {

    /**
     * 节省内存消耗
     * @return
     */
    public static Teacher createTeacher() {
        Teacher teacher = new Teacher();
        teacher.setTname("Mr");
        System.out.println("TeacherFactory 负责创建Teacher实例对象");
        return teacher;
    }
}
