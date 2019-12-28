package com.issac.icu.validator;

import org.checkerframework.checker.units.qual.C;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: ywy
 * @date: 2019-12-28
 * @desc:
 */
@Documented
@Target(ElementType.FIELD)
// 定义注解的保留策略
@Retention(RetentionPolicy.RUNTIME)
// 约束注解关联的验证器
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    String message() default "手机号校验错误";

    /**
     * 所属的组别
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 严重程度
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
