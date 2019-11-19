package com.dialog.base.bio;

import com.dialog.base.Const;
import com.dialog.base.InputUtil;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static com.dialog.base.Const.NEW_LINE;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class BIODialogClient {
    public static void main(String[] args) throws IOException {
        // 1.Connect Server
        Socket client = new Socket();
        client.connect(new InetSocketAddress(Const.HOST_NAME, Const.PORT));

        // 2. Send Msg
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.useDelimiter(NEW_LINE);
        PrintStream out = new PrintStream(client.getOutputStream());

        boolean flag = true;
        while (flag) {
            String sendMsg = InputUtil.getString("Pls send msg:");
            out.println(sendMsg);
            if(scanner.hasNext()) {
                String receiveMsg = scanner.next().trim();
                System.err.println("[wencai]:" + receiveMsg);
                if("quit".equals(sendMsg)) {
                    flag = false;
                }
            }
        }

        scanner.close();
        out.close();
        client.close();

    }
}
