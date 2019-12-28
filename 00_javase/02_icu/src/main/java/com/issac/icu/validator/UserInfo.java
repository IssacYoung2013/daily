package com.issac.icu.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * @author: ywy
 * @date: 2019-12-27
 * @desc:
 */
@Data
public class UserInfo {
    /**
     * 登录场景
     */
    public interface LoginGroup {
    }

    /**
     * 注册场景
     */
    public interface RegisterGroup {
    }

    /**
     * 组排序
     */
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group {
    }

    @NotNull(message = "用户ID不能为空", groups = LoginGroup.class)
    private String userId;

    @NotEmpty(message = "用户名称不能为空")
    private String userName;

    /**
     * 自动去掉字符串前后空格验证
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码不能少于6位，多于20位")
    private String password;

    @Max(value = 55, message = "年龄不能大于60岁")
    @Min(value = 18, message = "年龄不能小于18岁")
    private int age;

    @NotNull(message = "邮箱不能为空", groups = RegisterGroup.class)
    @Email(message = "邮箱必须为有效邮箱")
    private String email;

    @Past(message = "生日不能为未来或当前时间点")
    private Date birthday;

    @Phone(message = "手机号必须178开头的11位数字", groups = RegisterGroup.class)
    private String phone;

    @Size(min = 1, message = "不能少于1个好友")
    private List<@Valid UserInfo> friends;
}
