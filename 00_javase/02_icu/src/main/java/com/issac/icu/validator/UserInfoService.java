package com.issac.icu.validator;

import javax.validation.Valid;

/**
 * @author: ywy
 * @date: 2019-12-28
 * @desc:
 */
public class UserInfoService {

    /**
     * 方法输入参数UserInfo校验
     *
     * @param userInfo
     */
    public void setUserInfo(@Valid UserInfo userInfo) {

    }

    /**
     * 方法输出参数UserInfo 校验
     *
     * @return
     */
    public @Valid UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(28);
        userInfo.setUserId("issac");
        userInfo.setUserName("young");
        userInfo.setPassword("12312354");
        return userInfo;
    }

    public UserInfoService() {
    }

    public UserInfoService(@Valid UserInfo userInfo) {
    }
}
