package com.pjmike.Socket;

import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/09 23:08
 */
public class Client5 {
    private String host;
    private int port;

    public Client5(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        try {
            String host = args[0];
            int port = Integer.valueOf(args[1]);
            Client5 client5 = new Client5(host, port);
            client5.start();
        } catch (Exception e) {
            System.err.println("illegal arguments");
        }

    }

    public void start() {
        try {
            Socket socket = new Socket(host, port);
            new SendThread(socket).start();
            new ReadThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ReadThread extends Thread {
        private Socket socket;

        public ReadThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("接收线程启动...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line = "";
                while (!line.equals("exit")) {
                    line = reader.readLine();
                    System.out.print("收到服务端消息： " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ReadAndSendThread extends Thread {
        private Socket socket;

        public ReadAndSendThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line = "";
                String read;
                while (!line.equals("exit")) {
                    line = input.readLine() + "\n";
                    System.out.print("客户端发送消息： "+line);
                    writer.write(line);
                    writer.flush();
                    read = reader.readLine();
                    System.out.println("收到服务端消息： " + read);
                }
                input.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SendThread extends Thread {
        private Socket socket;

        public SendThread(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            System.out.println("发送线程启动...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line = "";
                while (!line.equals("exit")) {
                    line = reader.readLine() + "\n";
                    System.out.println("客户端发送消息： "+line);
                    writer.write(line);
                    writer.flush();
                }
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
