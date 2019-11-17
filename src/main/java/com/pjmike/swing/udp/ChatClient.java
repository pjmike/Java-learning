package com.pjmike.swing.udp;

import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author pjmike
 */
public class ChatClient extends Frame {
    private static final long serialVersionUID = 1L;
    /**
     * 聊天室ID
     */
    private String groupID;
    /**
     * 客户端名字
     */
    private String clientName;
    private DatagramSocket msg_send;
    private final int PORT = 8888;
    private InetAddress ip;

    private TextField textField = new TextField(20);
    private TextArea textArea = new TextArea();
    private Button send = new Button("send");

    public ChatClient(String groupID, String clientName) {
        super("聊天室:" + groupID + "/" + clientName);
        this.clientName = clientName;
        this.groupID = groupID;
        // 设置客户端界面样式
        add("North", textField);
        add("Center", textArea);
        add("South", send);
        setSize(250, 250);
        show();
        // 聊天相关服务器初始化
        init();
        // 监视器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 关闭消息发送服务
                msg_send.close();
                // 关闭客户端程序
                dispose();
                System.exit(0);
            }
        });

    }

    /**
     * 服务器初始化
     */
    private void init() {
        // 注册当前用户及所在聊天室信息注册到服务器
        ChatServer.logInGroup(groupID, this);
        try {
            // 初始化消息发送套接字对象
            msg_send = new DatagramSocket();
            // 指定消息服务器
            try {
                ip = InetAddress.getByName("127.0.0.1");
            } catch (UnknownHostException e) {
                System.out.println("未知的主机异常..");
            }
        } catch (SocketException e) {
            System.out.println("套接字连接异常..");
        }
    }

    /**
     * 消息发送按钮时间监听
     */
    @Override
    public boolean action(Event evt, Object arg) {
        if (evt.target.equals(send)) {
            try {
                // 获取输入内容
                String content = textField.getText();
                // 发送消息
                send_message(content);
                // 清空聊天框
                textField.setText(null);
            } catch (Exception ioe) {
                System.out.print(ioe.getMessage());
            }
        }
        return true;
    }

    /**
     * 发送消息
     */
    private void send_message(String content) {
        // 消息格式化(json格式)
        String message = messageFormat(content);
        // 将消息封装成UDP数据包
        byte[] buf = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, PORT);

        try {
            // 通过UDP协议发送消息
            msg_send.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息格式化
     *
     * @param content
     * @return
     */
    private String messageFormat(String content) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"groupId\":").append("\"").append(groupID).append(
                "\",");
        buffer.append("\"userName\":\"").append(clientName).append("\",");
        buffer.append("\"text\":\"").append(content).append("\"}");

        return buffer.toString();

    }

    /**
     * 获取聊天信息
     *
     * @param me
     */
    public void pushBackMessage(MessageEntity me) {
        textArea.append(me.getUserName() + ":" + me.getText());
        textArea.append("\n");
    }
}
