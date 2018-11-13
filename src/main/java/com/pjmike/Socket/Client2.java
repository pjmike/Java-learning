package com.pjmike.Socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端
 *
 * @author pjmike
 * @create 2018-08-12 17:52
 */
public class Client2 {
    private Socket socket = null;
    private DataOutputStream output = null;
    private BufferedReader input = null;

    public Client2(String address, int port) {
        try {
            //建立连接
            socket = new Socket(address, port);
            System.out.println("Connected ...");
            //从控制台输入信息
            input = new BufferedReader(new InputStreamReader(System.in));
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        while (!line.equals("exit")) {
            try {
                line = input.readLine();
                System.out.println("客户端输入的是: "+line);
                output.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            socket.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client2 client = new Client2("localhost", 5000);
    }
}
