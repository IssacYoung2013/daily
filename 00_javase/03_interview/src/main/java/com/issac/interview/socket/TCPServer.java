package com.issac.interview.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(65000);
        while (true) {
            Socket socket = serverSocket.accept();
            new LengthCalculator(socket).start();
        }
    }
}
