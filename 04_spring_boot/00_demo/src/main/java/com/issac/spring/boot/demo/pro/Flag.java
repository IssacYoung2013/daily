package com.issac.spring.boot.demo.pro;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-08
 * @desc:
 */
@Data
@Component
public class Flag {
    private boolean canOperate = true;
}
