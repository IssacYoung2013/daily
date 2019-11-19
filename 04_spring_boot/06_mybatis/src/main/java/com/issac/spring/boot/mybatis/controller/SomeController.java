package com.issac.spring.boot.mybatis.controller;

import com.issac.spring.boot.mybatis.po.EmployeePo;
import com.issac.spring.boot.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-01
 * @desc:
 */
@Controller
@RequestMapping("/test")
public class SomeController {

    @Autowired
    EmployeeService service;

    @PostMapping("/register")
    public String someHandle(EmployeePo employee) {
        service.addEmployee(employee);
        return "page/welcome";
    }

    @RequestMapping("/find")
    @ResponseBody
    public EmployeePo someHandle1(Integer id) {
        return service.findEmployeeById(id);
    }

    @RequestMapping("/count")
    @ResponseBody
    public Integer someHandle2() {
        return service.findEmployeeCount();
    }

}
