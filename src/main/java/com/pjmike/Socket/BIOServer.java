package com.pjmike.Socket;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/03/21 21:22
 */
public class BIOServer {
    ServerSocket serverSocket = null;
    Socket socket = null;
    BufferedReader reader = null;

    public BIOServer() throws IOException {
        serverSocket = new ServerSocket(8888);
    }

    public void accept() {
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("接收到客户端连接");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    System.out.println("开始收数据");
                    System.out.println("服务端收到的数据：" + readLine);
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
    }

    public static void main(String[] args) throws IOException {
        BIOServer bioServer = new BIOServer();
        bioServer.accept();
    }
}
