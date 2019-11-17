package com.pjmike.Socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/09/02 19:50
 */
public class UDPClient {
    private int port;
    private InetAddress inetAddress;

    public UDPClient(int port, String host) throws UnknownHostException {
        this.port = port;
        this.inetAddress = InetAddress.getByName(host);
    }

    public void start() {
        BufferedReader input = null;
        DatagramPacket packet = null;
        DatagramSocket socket = null;
        try {
            //定义服务器的地址，端口号，数据
            input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入你的姓名和学号...");
            while (true) {
                String readLine = input.readLine();
                //创建数据报
                packet = new DatagramPacket(readLine.getBytes(), readLine.getBytes().length, inetAddress, port);
                //创建DatagramSocket，实现数据发送和接收
                socket = new DatagramSocket();
                //向服务器端发送数据报
                System.out.println("客户端输入消息：" + readLine);
                socket.send(packet);
                //接收服务器响应数据
                byte[] data2 = new byte[1024];
                DatagramPacket response = new DatagramPacket(data2, data2.length);
                socket.receive(response);
                String info = new String(data2, 0, response.getLength());
                System.out.println("收到服务端消息：" + info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String host = args[0];
            int port = Integer.valueOf(args[1]);
            UDPClient udpClient = new UDPClient(port,host);
            udpClient.start();
        } catch (Exception e) {
            System.err.println("illegal arguments");
        }
    }
}
