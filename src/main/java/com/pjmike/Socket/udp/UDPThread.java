package com.pjmike.Socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/02 19:48
 */
public class UDPThread implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket packet;

    public UDPThread(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }
    @Override
    public void run() {
        String info;
        InetAddress address;
        int port = 8800;
        DatagramPacket packet2;
        boolean flag = true;
        try {
            info = new String(packet.getData(), 0, packet.getLength());
            System.out.println("收到客户端消息：" + info);

            address = packet.getAddress();
            port = packet.getPort();
            if (flag) {
                String[] s = info.split(" ");
                String reply = s[0] + ",我已经接收到你的申请";
                packet2 = new DatagramPacket(reply.getBytes(), reply.getBytes().length, address, port);
                socket.send(packet2);
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
