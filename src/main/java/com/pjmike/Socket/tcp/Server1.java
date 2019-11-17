package com.pjmike.Socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/07 17:34
 */
public class Server1 {
    public void start(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader reader = null;
        String content;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                socket = serverSocket.accept();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((content = reader.readLine()) != null) {
                    System.out.println("服务端收到内容: " + content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server1 server1 = new Server1();
        server1.start(8888);
    }
}
