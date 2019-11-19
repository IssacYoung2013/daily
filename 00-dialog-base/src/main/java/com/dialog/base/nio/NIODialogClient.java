package com.dialog.base.nio;

import com.dialog.base.Const;
import com.dialog.base.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 *
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class NIODialogClient {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(Const.PORT));

        boolean flag = true;
        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (flag) {
            buffer.clear();
            String inputData = InputUtil.getString("Pls send msg:");
            buffer.put(inputData.getBytes());
            buffer.flip();
            client.write(buffer);
            buffer.clear();
            int read = client.read(buffer);
            buffer.flip();
            String receiveMsg = new String(buffer.array(),0,read);
            System.err.println("[wencai]:"+receiveMsg);
            if("quit".equals(inputData)) {
                flag = false;
            }

        }
    }
}
