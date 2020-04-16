package com.issac.spring.boot.demo.condi;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @author: ywy
 * @date: 2020-04-05
 * @desc:
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String[] props = (String[]) metadata.getAnnotationAttributes("com.issac.spring.boot.demo.condi.MyConditionAnnotation")
                .get("value");
        for (String prop : props) {
             if (StringUtils.isEmpty(context.getEnvironment().getProperty(prop))) {
                return false;
            }
        }

        return true;
    }
}
