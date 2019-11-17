package com.pjmike.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/09 23:21
 */
public class Server5 {
    private int port;

    public Server5(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        try {
            Integer port = Integer.valueOf(args[0]);
            new Server5(port).start();
        } catch (Exception e) {
            System.err.println("illegal arguments");
        }
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        String line;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        BufferedReader input = null;
        String read;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("等待客户端连接....");
            while (true) {
                socket = serverSocket.accept();
                System.out.printf("客户端已连接，IP为 %s，端口为 %s",socket.getInetAddress().getHostAddress(),socket.getPort());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(System.in));
                boolean flag = true;
                while ((line = reader.readLine()) != null) {
                    System.out.println("收到客户端消息: " + line);
                    if (flag) {
                        String[] s = line.split(" ");
                        read = s[0]+"，收到你的请求\n";
                        flag = false;
                    } else {
                        read = input.readLine() + "\n";
                    }
                    writer.write(read);
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                socket.close();
                reader.close();
                writer.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
