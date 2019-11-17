package com.pjmike.Socket.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/07 17:39
 */
public class Client1 {
    public void start(String host, int port) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Socket socket = null;
        String content;
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while ((content = reader.readLine()) != null) {
                System.out.println("客户端发送的内容：" + content+"\n");
                writer.write(content+"\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client1 client1 = new Client1();
        client1.start("127.0.0.1",8888);
    }
}
