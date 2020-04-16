package com.issac.discruptor.ablitity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: ywy
 * @date: 2020-02-25
 * @desc:
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {
    private static final long serialVersionUID = 4770190011055027330L;
    private Long id;
    private String name;
}
