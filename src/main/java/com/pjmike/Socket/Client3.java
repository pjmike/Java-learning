package com.pjmike.Socket;

import java.io.*;
import java.net.Socket;

/**
 * @author pjmike
 * @create 2018-09-20 10:36
 */
public class Client3 {
    private Socket socket = null;

    public Client3(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            System.out.println("连接服务器成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.write("hi");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Client3 client3 = new Client3("127.0.0.1",8888);
        client3.start();
        Thread.sleep(1000);
        client3.close();
    }
}
