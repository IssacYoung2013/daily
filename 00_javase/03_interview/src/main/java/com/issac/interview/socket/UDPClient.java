package com.issac.interview.socket;

import java.io.IOException;
import java.net.*;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        byte[] buf = String.valueOf("Hello World").getBytes();

        DatagramPacket packet = new DatagramPacket(buf,buf.length,address,65001);
        socket.send(packet);

        byte[] data = new byte[100];
        DatagramPacket rcvPacket = new DatagramPacket(data,data.length);
        socket.receive(rcvPacket);
        String content = new String(rcvPacket.getData(),0,rcvPacket.getLength());
        System.out.println(content);
    }
}
