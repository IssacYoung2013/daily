package com.issac.interview.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",65000);
        OutputStream os  = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        os.write(new String("Hello World").getBytes());
        int ch =0;
        byte[] buf = new byte[1024];
        ch = is.read(buf);
        String content = new String(buf,0,ch);
        System.out.println(content);
        is.close();
        os.close();
        socket.close();
    }
}
