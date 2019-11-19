package com.issac.spring.boot.thymeleaf.controller;

import com.issac.spring.boot.thymeleaf.vo.StudentVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: ywy
 * @date: 2019-11-01
 * @desc:
 */
@Controller
public class ThymeleafController {

    @GetMapping("/some")
    public String someHandle(Model model) {
        model.addAttribute("hello","Hello Thymeleaf World!");
        model.addAttribute("student",new StudentVO("张三",23));
        return "index";
    }
}
