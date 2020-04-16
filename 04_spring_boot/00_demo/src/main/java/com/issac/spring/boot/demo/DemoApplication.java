package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.except.Solid;
import com.issac.spring.boot.demo.initializer.SecondInitializer;
import com.issac.spring.boot.demo.ioc.ann.SuperConfiguration;
import com.issac.spring.boot.demo.ioc.xml.Cat;
import com.issac.spring.boot.demo.listener.SecondListener;
import com.issac.spring.boot.demo.selector.MyDeferredImportSelector;
import com.issac.spring.boot.demo.selector.MyImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StopWatch;

import java.util.Properties;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@SpringBootApplication
//@MapperScan("com.issac.spring.boot.demo.mapper")
//@PropertySource({"demo.properties"})
@Import({MyImportSelector.class, MyDeferredImportSelector.class})
//@ImportResource("ioc/demo.xml")
public class DemoApplication implements SuperConfiguration {
    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(DemoApplication.class, args);
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addInitializers(new SecondInitializer());
        application.addListeners(new SecondListener());
        application.setBanner(new ResourceBanner(new ClassPathResource(
                "favourite.txt"
        )));
        Properties properties = new Properties();
        properties.put("issac.website.url", "issacyoung.cn");
        application.setDefaultProperties(properties);
        application.run(args);
//        StopWatch stopWatch = new StopWatch("myWatch");
//        stopWatch.start("myTask1");
//        Thread.sleep(2000);
//        stopWatch.stop();
//        stopWatch.start("myTask2");
//        Thread.sleep(3000);
//        stopWatch.stop();
//        stopWatch.start("myTask3");
//        Thread.sleep(4000);
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
    }

//    @Autowired
//    private Solid solid;

    @Configuration
    class TestA {

    }

    @Configuration
    class TestB {

    }

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
