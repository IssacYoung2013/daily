package com.issac.spring.beans;

import lombok.Data;

import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-26
 * @desc:
 */
@Data
public class Teacher1 {
    private String teacherName;
    private String[] friendArray;
    private List<String> school;

    public void setFriendArray(Object friendArray) {
        this.friendArray = (String[]) friendArray;
    }
}
