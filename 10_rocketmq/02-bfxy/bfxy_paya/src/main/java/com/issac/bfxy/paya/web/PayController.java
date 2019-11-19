package com.issac.bfxy.paya.web;

import com.issac.bfxy.paya.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public String pay(@RequestParam("userId") String userId,
                      @RequestParam("orderId") String orderId,
                      @RequestParam("account") String account,
                      @RequestParam("money") Double money) throws Exception {
        return payService.payment(userId,orderId,account,money);
    }
}
