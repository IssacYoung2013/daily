package com.issac.mybatis.bean;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-21
 * @desc:
 */
public class Dept {
    private Integer deptno;
    private String detpname;
    private String loc;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDetpname() {
        return detpname;
    }

    public void setDetpname(String detpname) {
        this.detpname = detpname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
