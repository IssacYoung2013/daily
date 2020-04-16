package com.issac.spring.boot.demo.pro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-08
 * @desc:
 */
@Component
public class ResultCommandLineRunner implements CommandLineRunner, EnvironmentAware,MyAware {

    private Environment env;

    private Flag flag;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(flag.isCanOperate());
        System.out.println(env.getProperty("issac.website.url"));
        System.out.println(env.getProperty("issac.avg.age"));
        System.out.println(env.getProperty("issac.website.path"));
        System.out.println(env.getProperty("issac.vm.name"));
        System.out.println(env.getProperty("issac.default.name"));
        System.out.println(env.getProperty("issac.active.name"));
        System.out.println(env.getProperty("issac.active2.name"));

    }
}
