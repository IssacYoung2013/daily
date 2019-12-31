package com.issac.interview.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class LengthCalculator extends Thread {

    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            int ch = 0;
            byte[] buf = new byte[1024];
            ch = is.read(buf);
            String content = new String(buf,0,ch);
            System.out.println(content);
            os.write(String.valueOf(content.length()).getBytes());
            is.close();
            os.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
