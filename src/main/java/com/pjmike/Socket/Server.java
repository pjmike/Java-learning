package com.pjmike.Socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

/**
 * 服务端
 *
 * @author pjmike
 * @create 2018-08-12 17:43
 */
public class Server {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream input = null;

    public Server(int port) {
        try {
            //绑定端口
            System.out.println("bind port ...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started and waiting a client ..");
            //调用accept()方法，提取连接请求
            socket = serverSocket.accept();
            //一般都是以字节传输
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            while (!line.equals("exit")) {
                try {
                    //readUTF()方法需要读取writeUTF()写过来的数据
                    line = input.readUTF();
                    System.out.println("recd: " + line);
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
            //关闭连接
            System.out.println("connection closed ...");
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
