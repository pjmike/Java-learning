package com.pjmike.swing.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

/**
 * @author pjmike
 */
public class ChatServer extends Thread {
    /**
     * 端口号
     */
    private static final int PORT = 8888;
    /**
     * 消息接收者对象
     */
    private static DatagramSocket server = null;
    /**
     * 保存聊天室的信息
     */
    private static HashMap<String, ArrayList<ChatClient>> groups = new HashMap<>();

    public ChatServer() {
        try {
            // 消息接受套接字对象的构造初始化
            server = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 聊天室用户登录
     *
     * @param groupID
     * @param client
     */
    public static void logInGroup(String groupID, ChatClient client) {
        // 通过聊天室ID，获取该聊天室的所有在线用户
        ArrayList<ChatClient> clients = groups.get(groupID);
        if (clients == null) {
            clients = new ArrayList<>();
        }
        // 将此次进入聊天室的用户登记
        clients.add(client);
        // 更新聊天室信息
        groups.put(groupID, clients);
    }

    /**
     * 接收消息
     */
    @Override
    public void run() {
        while (true) {
            receiveMessage();
        }
    }

    private void receiveMessage() {
        // UDP数据包
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {
            try {
                // 接受数据包
                server.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 解析数据包，获取聊天信息
            String content = new String(packet.getData(), 0, packet.getLength());

            // 通过第三方包解析json数据
            Gson gson = new Gson();
            MessageEntity me = gson.fromJson(content, MessageEntity.class);

            // 解析消息内容，通过聊天室ID，获取该聊天室的所有在线用户
            ArrayList<ChatClient> clients = groups.get(me.getGroupId());

            // 将接收到的消息推送回该聊天室的各个用户
            for (ChatClient client : clients) {
                client.pushBackMessage(me);
            }
        }
    }
}
