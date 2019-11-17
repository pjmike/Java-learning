package com.pjmike.Socket.udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/09/02 19:49
 */
public class UDPServer {
    private int port;

    public UDPServer(int port) {
        this.port = port;
    }

    public void start() {
        DatagramSocket socket = null;
        DatagramPacket packet;
        DatagramPacket input;
        BufferedReader reader = null;
        byte[] data;
        String content;
        String read;
        boolean flag = true;
        System.out.println("服务端启动....");
        try {
            socket = new DatagramSocket(port);
            while (true) {
                //创建字节数组，指定接收的数据包的大小
                data = new byte[1024];
                packet = new DatagramPacket(data, data.length);
                reader = new BufferedReader(new InputStreamReader(System.in));
                //此方法在接收到数据报之前会一直阻塞
                socket.receive(packet);
                content = new String(packet.getData(), 0, packet.getLength());
                System.out.println("收到客户端消息: " + content);
                if (flag) {
                    String[] s = content.split(" ");
                    read = s[0] + "，收到你的请求";
                    flag = false;
                } else {
                    read = reader.readLine();
                }
                input = new DatagramPacket(read.getBytes(), 0, read.getBytes().length, packet.getAddress(), packet.getPort());
                System.out.println("服务端输入消息：" + read);
                socket.send(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        try {
            Integer port = Integer.valueOf(args[0]);
            UDPServer udpServer = new UDPServer(port);
            udpServer.start();
        } catch (Exception e) {
            System.err.println("illegal arguments");
        }
    }
}
