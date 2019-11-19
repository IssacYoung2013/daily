package com.issac.spring.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 异常处理逻辑
        String msg;
        if (ex instanceof CustomException) {
            msg = ((CustomException) ex).getMsg();
        } else {
            msg = "未知错误";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("msg",msg);
        return mv;
    }
}
