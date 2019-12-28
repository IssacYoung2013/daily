package com.issac.icu.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author: ywy
 * @date: 2019-12-28
 * @desc:
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    /**
     * 自定义校验
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String check = "178\\d{8}";
        Pattern regex = Pattern.compile(check);
        String phone = Optional.ofNullable(value).orElse("");
        return regex.matcher(phone).matches();
    }
}
