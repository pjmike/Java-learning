package com.pjmike.Socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 *
 * @author pjmike
 * @create 2018-08-12 17:43
 */
public class Server {
    private int port;
    public Server(int port) {
        this.port = port;
    }
    public void start() {
        try {
            //绑定端口
            System.out.println("bind port ...");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started and waiting a client ..");
            //调用accept()方法，提取连接请求
            Socket socket = serverSocket.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String line = "";
            boolean flag = true;
            while (!line.equals("exit")) {
                try {
                    //readUTF()方法需要读取writeUTF()写过来的数据
                    line = input.readUTF();
                    if (flag) {
                        System.out.println("服务端收到消息: " + line);
                        String[] s = line.split(" ");
                        String reply = s[0] + "，我已经收到你的申请";
                        output.writeUTF(reply);
                        output.flush();
                        flag = false;
                    } else {
                        System.out.println("服务端收到消息: " + line);
                    }
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
            //关闭连接
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server(8000);
        server.start();
    }
}
