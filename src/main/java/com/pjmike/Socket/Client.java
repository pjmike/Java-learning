package com.pjmike.Socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 客户端
 *
 * @author pjmike
 * @create 2018-08-12 17:52
 */
public class Client {

    private String address;
    private int port;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;

    }

    public void start() {
        try {
            //建立连接
            Socket socket = new Socket(address, port);
            System.out.println("请输入你的姓名和学号...");
            //从控制台输入信息
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream replyInput = new DataInputStream(socket.getInputStream());
            String line = "";
            while (!line.equals("exit")) {
                line = input.readLine();
                System.out.println("客户端输入的是: " + line);
                output.writeUTF(line);
                output.flush();
                String readLine = replyInput.readUTF();
                System.out.println("服务端的应答: " + readLine);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8000);
        client.start();
    }

    public static void run(Socket socket) {

    }
}
