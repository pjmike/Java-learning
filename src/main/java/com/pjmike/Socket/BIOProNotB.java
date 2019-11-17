package com.pjmike.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/03/22 16:03
 */
public class BIOProNotB {
    public void initBIOServer(int port) {
        ServerSocket serverSocket = null;//服务端Socket
        Socket socket = null;//客户端socket
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ClientSocketThread thread = null;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            System.out.println("ServerSocket started");
            while (true) {
                try {
                    socket = serverSocket.accept();
                } catch (SocketTimeoutException e) {
                    //运行到这里表示本次accept是没有收到任何数据的，服务端的主线程在这里可以做一些事情
                    System.out.println("noe time is: "+new Date());
                    continue;
                }
                System.out.println("id 为：" + socket.hashCode() + "的ClientSocket Connected");
                thread = new ClientSocketThread(socket);
                threadPool.execute(thread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BIOProNotB server = new BIOProNotB();
        server.initBIOServer(8888);
    }
    class ClientSocketThread extends Thread {
        public Socket socket;

        public ClientSocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String inputContent;
            int count = 0;
            try {
                socket.setSoTimeout(1000);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while ((inputContent = reader.readLine()) != null) {
                    System.out.println("收到id为: " + socket.hashCode() + " " + inputContent);
                    count++;
                }
                System.out.println("id为： "+socket.hashCode()+"的Clientsocket读取结束");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
