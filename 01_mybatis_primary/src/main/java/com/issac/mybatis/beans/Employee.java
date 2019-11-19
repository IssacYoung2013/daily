package com.issac.mybatis.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
public class Employee {
    private Integer empNo;
    private String ename;
    private String job;
    private Double sal;
    private Date hireDate;

    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * 工作年限
     */
    private int workAge;

    public Employee() {
    }

    public Employee(Date hireDate) {
        this.hireDate = hireDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        this.workAge = -Integer.valueOf(simpleDateFormat.format(this.hireDate))+
                Integer.valueOf(simpleDateFormat.format(new Date()));
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", sal=" + sal +
                ", hireDate=" + hireDate +
                ", workAge=" + workAge +
                '}';
    }
}
