package com.issac.spring.boot.demo.except;

import java.net.ServerSocket;

/**
 * @author: ywy
 * @date: 2020-03-16
 * @desc:
 */
public class MySocket {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090);
        serverSocket.accept();
    }
}
