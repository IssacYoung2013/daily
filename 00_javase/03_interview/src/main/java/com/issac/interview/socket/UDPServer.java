package com.issac.interview.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(65001);
        byte[] buf = new byte[100];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        byte[] data = packet.getData();
        String content = new String(data, 0, packet.getLength());
        byte[] sendContent = String.valueOf(content.length()).getBytes();
        DatagramPacket packetToClient = new DatagramPacket(sendContent, sendContent.length,
                packet.getAddress(), packet.getPort());
        socket.send(packetToClient);
    }
}
