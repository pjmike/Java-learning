package com.pjmike.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author pjmike
 * @create 2018-09-20 10:22
 */
public class Server2 {
    private ServerSocket serverSocket = null;

    public Server2(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("连接端口..");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }

    public void start()  {
        new Thread(() -> doStart());
    }

    public void doStart() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler {
        private Socket socket = null;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void start() {
            try {
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("received: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server2 server2 = new Server2(8888);
        server2.doStart();
    }
}
