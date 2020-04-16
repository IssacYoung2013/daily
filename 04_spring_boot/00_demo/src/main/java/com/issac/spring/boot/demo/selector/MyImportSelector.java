package com.issac.spring.boot.demo.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: ywy
 * @date: 2020-03-28
 * @desc:
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("MyImportSelector");
        return new String[]{"com.issac.spring.boot.demo.ioc.xml.Cat","com.issac.spring.boot.demo.ioc.xml.Dog"};
    }
}
