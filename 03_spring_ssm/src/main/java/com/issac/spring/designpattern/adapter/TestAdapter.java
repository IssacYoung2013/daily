package com.issac.spring.designpattern.adapter;

import java.net.SocketImpl;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-31
 * @desc:
 */
public class TestAdapter {
    public static void main(String[] args) {
        DBSocket dbSocket = new DBSocketImpl();
        dbSocket.charge();

        SocketAdapter dbSocket1 = new SocketAdapter(new GBSocketImpl());
        dbSocket1.charge();
    }
}
