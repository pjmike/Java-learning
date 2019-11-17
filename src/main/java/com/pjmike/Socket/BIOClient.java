package com.pjmike.Socket;

import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/03/21 21:31
 */
public class BIOClient {
    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    public BIOClient() throws IOException {
        socket = new Socket("127.0.0.1", 8888);
    }
    public void send() {
            try {
                reader = new BufferedReader(new InputStreamReader(System.in));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    System.out.println("client开始写数据：" + readLine);
                    writer.write(readLine);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BIOClient client = new BIOClient();
        client.send();
    }
}
