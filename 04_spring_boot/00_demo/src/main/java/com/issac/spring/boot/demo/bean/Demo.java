package com.issac.spring.boot.demo.bean;

import lombok.ToString;

import javax.persistence.Id;

@ToString
public class Demo {

    public Demo() {
    }

    public Demo(Integer id) {
        this.id = id;
    }

    @Id
    private Integer id;

    private String name;

    private String job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }
}