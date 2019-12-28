package com.issac.icu.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
public class ValidationTest {

    private Validator validator;

    private UserInfo userInfo;

    /**
     * 验证结果集合
     */
    private Set<ConstraintViolation<UserInfo>> set;

    Set<ConstraintViolation<UserInfoService>> otherSet;

    @Before
    public void init() {
        // 初始化验证
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        userInfo = new UserInfo();
//        userInfo.setUserId("issac");
        userInfo.setUserName("issacyoung");
        userInfo.setPassword("1234511");
        userInfo.setEmail("issacyoung@msn.cn");
        userInfo.setAge(29);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 1, 1);
        userInfo.setBirthday(calendar.getTime());

        UserInfo friend = new UserInfo();
        friend.setUserId("tom");
        friend.setAge(28);
        friend.setUserName("tom");
        friend.setPassword("123456");
//        friend.setEmail("tom@msn.cn");

        userInfo.setFriends(new ArrayList<UserInfo>() {{
            add(friend);
        }});
    }

    @After
    public void print() {
        if (set != null) {
            set.forEach(item -> {
                System.out.println(item.getMessage());
            });
        }

        if (otherSet != null) {
            otherSet.forEach(item -> {
                System.out.println(item.getMessage());
            });
        }
    }

    /**
     * 级联验证
     */
    @Test
    public void graphValidation() {
        set = validator.validate(userInfo);
    }

    @Test
    public void testNotnull() {
        // 使用验证器验证对象
        set = validator.validate(userInfo);
    }

    @Test
    public void groupValidation() {
        set = validator.validate(userInfo, UserInfo.RegisterGroup.class, UserInfo.LoginGroup.class);
    }

    /**
     * 组序列
     */
    @Test
    public void groupSeqValidation() {
        set = validator.validate(userInfo, UserInfo.Group.class);
    }

    @Test
    public void paramValidation() throws NoSuchMethodException {
        // 验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        UserInfoService userInfoService = new UserInfoService();
        Method setUserInfo = userInfoService.getClass().getMethod("setUserInfo", UserInfo.class);
        Object[] paramObjects = new Object[]{new UserInfo()};
        // 对方法的输入参数进行校验
        otherSet = executableValidator
                .validateParameters(userInfoService, setUserInfo, paramObjects);
    }

    /**
     * 对方法返回值进行约束校验
     */
    @Test
    public void returnValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 构造要验证的方法对象
        UserInfoService userInfoService = new UserInfoService();
        Method getUserInfo = userInfoService.getClass().getMethod("getUserInfo");

        // 调用方法得到返回值
        Object returnVal = getUserInfo.invoke(userInfoService, null);

        // 校验
        otherSet = executableValidator.validateReturnValue(userInfoService, getUserInfo, returnVal);
    }

    @Test
    public void constructorValidation() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 构造要验证的方法对象
        Constructor<? extends UserInfoService> constructor =
                UserInfoService.class.getConstructor(UserInfo.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(28);
        userInfo.setUserId("issac");
        userInfo.setUserName("young");
        userInfo.setPassword("12312354");
        Object[] paramObjects = new Object[]{userInfo};
        // 校验
        otherSet = executableValidator.validateConstructorParameters(constructor,paramObjects);
    }
}
