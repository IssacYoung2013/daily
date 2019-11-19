package com.dialog.base.nio;

import com.dialog.base.Const;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class NIODialogServer {
    public static void main(String[] args) throws Exception {
        new NIODialogServer().run();
    }

    public void run() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(Const.PORT));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int keySelector = 0;
        while ((keySelector = selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                while (selectionKey.isAcceptable()) {
                    SocketChannel client = serverSocketChannel.accept();
                    executorService.submit(new DialogHandler(client) );
                }
            }
        }
    }

    private static class DialogHandler implements Runnable {

        private SocketChannel clientChannel;

        public DialogHandler(SocketChannel clientChannel) {
            this.clientChannel = clientChannel;
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            boolean flag = true;
            while (flag) {
                buffer.clear();
                try {
                    int read = this.clientChannel.read(buffer);
                    String receiveMsg = new String(buffer.array(),0,read);
                    String sendMsg = receiveMsg.toUpperCase();
                    if("quit".equals(receiveMsg)) {
                        sendMsg = "Ready to exit";
                    }
                    buffer.clear();
                    buffer.put(sendMsg.getBytes());
                    buffer.flip();
                    this.clientChannel.write(buffer);
                } catch (IOException e) {

                }
            }

            try {
                clientChannel.close();
            } catch (IOException e) {

            }

        }
    }
}
