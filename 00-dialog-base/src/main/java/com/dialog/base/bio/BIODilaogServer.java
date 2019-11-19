package com.dialog.base.bio;

import com.dialog.base.Const;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dialog.base.Const.NEW_LINE;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class BIODilaogServer {
    public static void main(String[] args) throws Exception {
        new BIODilaogServer().run();
    }

    public void run() throws Exception {

        // 1. Create Executors
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 2. Start Server
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(Const.PORT));
        System.out.println("Start Server");

        // 3. Handle Message
        while (true) {
            Socket client = serverSocket.accept();
            executorService.submit(new DialogHandler(client));
        }
    }

    private static class DialogHandler implements Runnable {

        private Socket client;

        public DialogHandler(Socket client) {
            this.client = client;
        }

        public void run() {

            boolean flag = true;
            Scanner scanner = null;
            PrintStream out = null;
            try {
                scanner = new Scanner(this.client.getInputStream());
                scanner.useDelimiter(NEW_LINE);
                out = new PrintStream(this.client.getOutputStream());

                while (flag) {
                    if (scanner.hasNext()) {
                        String receiveMsg = scanner.next().trim();
                        String sendMsg = receiveMsg.toUpperCase();
                        if ("quit".equals(receiveMsg)) {
                            flag = false;
                            sendMsg = "Ready to exit";
                        }
                        out.println(sendMsg);
                    }
                }

            } catch (IOException e) {

            } finally {
                scanner.close();
                out.close();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
