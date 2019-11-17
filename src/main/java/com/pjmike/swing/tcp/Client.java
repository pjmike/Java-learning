package com.pjmike.swing.tcp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description:
 * @author: 13572
 */
public class Client extends WindowAdapter implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JButton button;
    private JButton lout;
    private JLabel label;
    Socket s, s1, s2;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private DataOutputStream dlout;
    private String name;
    public Client(String name) throws IOException {
        frame = new JFrame("聊天窗口");
        textField = new JTextField();
        DefaultListModel model = new DefaultListModel();
        DefaultListModel model1 = new DefaultListModel();
        label = new JLabel("消息");
        JList list = new JList(model);
        JList list1 = new JList(model1);
        button = new JButton("发送");
        lout = new JButton("退出");
        JScrollPane scrollpane = new JScrollPane(list);
        JScrollPane scrollpane1 = new JScrollPane(list1);
        JPanel panel = new JPanel();
        button.addActionListener(this);
        lout.addActionListener(this);
        panel.add(textField);
        panel.add(button);
        panel.add(scrollpane);
        panel.add(label);
        panel.add(lout);
        panel.add(scrollpane1);
        scrollpane.setBounds(10, 20, 180, 150);
        scrollpane1.setBounds(250, 20, 100, 150);
        label.setBounds(20, 180, 80, 30);
        textField.setBounds(100, 180, 140, 30);
        button.setBounds(260, 180, 90, 30);
        lout.setBounds(260, 230, 90, 30);
        frame.add(panel);
        panel.setLayout(null);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.name = name;
        frame.addWindowListener(this);
        s = new Socket("localhost", 1004);
        s1 = new Socket("localhost", 1004);
        s2 = new Socket("localhost", 1004);
        //输入流
        inputStream = new DataInputStream(s.getInputStream());
        //输出流
        outputStream = new DataOutputStream(s.getOutputStream());
        //发送消息
        outputStream.writeUTF(name + " 已经加入聊天室");
        dlout = new DataOutputStream(s1.getOutputStream());
        DataOutputStream dout1 = new DataOutputStream(s2.getOutputStream());
        DataInputStream din1 = new DataInputStream(s2.getInputStream());
        //开启一个线程显示当前在线的群人员
        new Thread(new ShowUsersThread(dout1, model1, name, din1)).start();
        //开启一个线程接收群消息
        new Thread(new HandleThread(inputStream, model)).start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // 发送群消息
        if (actionEvent.getSource() == button) {
            String str = textField.getText();
            textField.setText("");
            str = name + ": > " + str;
            try {
                outputStream.writeUTF(str);
                System.out.println(str);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 退出操作
        if (actionEvent.getSource() == lout) {
            frame.dispose();
            try {
                //向聊天室发送退出群聊的消息
                outputStream.writeUTF(name + " 已经退出聊天室");
                dlout.writeUTF(name);
                dlout.flush();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            dlout.writeUTF(name);
            dlout.flush();
            Thread.sleep(1000);
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ShowUsersThread implements Runnable {
    DataOutputStream dout1;
    DefaultListModel model1;
    DataInputStream din1;
    String name, lname;
    ArrayList alname = new ArrayList();
    ObjectInputStream obj;
    int i = 0;

    ShowUsersThread(DataOutputStream dout1, DefaultListModel model1, String name, DataInputStream din1) {
        this.dout1 = dout1;
        this.model1 = model1;
        this.name = name;
        this.din1 = din1;
    }

    @Override
    public void run() {
        try {
            dout1.writeUTF(name);
            while (true) {
                obj = new ObjectInputStream(din1);
                //read the list of user names
                alname = (ArrayList) obj.readObject();
                if (i > 0) {
                    model1.clear();
                }
                Iterator i1 = alname.iterator();
                System.out.println(alname);
                while (i1.hasNext()) {
                    lname = (String) i1.next();
                    i++;
                    //add the user names in list box
                    model1.addElement(lname);
                }
            }
        } catch (Exception oe) {
        }
    }
}

/**
 * 处理消息的线程
 */
class HandleThread implements Runnable {
    DataInputStream din;
    DefaultListModel model;

    HandleThread(DataInputStream din, DefaultListModel model) {
        this.din = din;
        this.model = model;
    }

    @Override
    public void run() {
        String str1;
        while (true) {
            try {
                //读取消息
                str1 = din.readUTF();
                model.addElement(str1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
