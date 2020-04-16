package com.issac.spring.boot.demo.selector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: ywy
 * @date: 2020-03-28
 * @desc:
 */
public class MyDeferredImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("MyDeferredImportSelector");
        return new String[]{"com.issac.spring.boot.demo.ioc.xml.Bird",
        "com.issac.spring.boot.demo.ioc.xml.Monkey"};
    }
}
