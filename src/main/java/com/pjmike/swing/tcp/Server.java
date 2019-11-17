package com.pjmike.swing.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description:
 * @author: pjmike
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket s, s1, s2;

    Server() throws IOException {
        serverSocket = new ServerSocket(1004);
        ArrayList al = new ArrayList();
        ArrayList al1 = new ArrayList();
        ArrayList al2 = new ArrayList();
        ArrayList nameList = new ArrayList();
        while (true) {
            s = serverSocket.accept();
            s1 = serverSocket.accept();
            s2 = serverSocket.accept();
            al.add(s);
            al1.add(s1);
            al2.add(s2);
            System.out.println("Client is Connected");

            ShowUserListThread m = new ShowUserListThread(s2, al2, nameList);
            Thread t2 = new Thread(m);
            t2.start();

            SendAndReceiveThread r = new SendAndReceiveThread(s, al);
            Thread t = new Thread(r);
            t.start();

            UpdateUserListThread my = new UpdateUserListThread(s1, al1, s, s2);
            Thread t1 = new Thread(my);
            t1.start();
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
        }
    }
}

/**
 * 更新在线用户
 */
class UpdateUserListThread implements Runnable {
    Socket s1, s, s2;
    static ArrayList al1;
    DataInputStream ddin;
    String sname;

    UpdateUserListThread(Socket s1, ArrayList al1, Socket s, Socket s2) {
        this.s1 = s1;
        UpdateUserListThread.al1 = al1;
        this.s = s;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            ddin = new DataInputStream(s1.getInputStream());
            while (true) {
                sname = ddin.readUTF();
                System.out.println("Exit  :" + sname);
                ShowUserListThread.alname.remove(sname);
                ShowUserListThread.every();
                al1.remove(s1);
                SendAndReceiveThread.al.remove(s);
                ShowUserListThread.al2.remove(s2);
                if (al1.isEmpty()) {
                    System.exit(0);
                }
            }
        } catch (Exception ie) {
        }
    }
}

/**
 * 展示所有在线用户
 */
class ShowUserListThread implements Runnable {
    Socket s2;
    static ArrayList al2;
    static ArrayList alname;
    static DataInputStream din1;
    static DataOutputStream dout1;

    ShowUserListThread(Socket s2, ArrayList al2, ArrayList alname) {
        this.s2 = s2;
        ShowUserListThread.al2 = al2;
        ShowUserListThread.alname = alname;
    }

    @Override
    public void run() {
        try {
            din1 = new DataInputStream(s2.getInputStream());
            alname.add(din1.readUTF());
            every();
        } catch (Exception oe) {
            System.out.println("Main expression" + oe);
        }
    }

    static void every() throws Exception {
        Iterator i1 = al2.iterator();
        Socket st1;

        while (i1.hasNext()) {
            st1 = (Socket) i1.next();
            dout1 = new DataOutputStream(st1.getOutputStream());
            ObjectOutputStream obj = new ObjectOutputStream(dout1);
            obj.writeObject(alname);
            dout1.flush();
            obj.flush();
        }
    }
}

/**
 * 发送和接收数据
 */
class SendAndReceiveThread implements Runnable {
    Socket s;
    static ArrayList al;
    DataInputStream din;
    DataOutputStream dout;

    SendAndReceiveThread(Socket s, ArrayList al) {
        this.s = s;
        SendAndReceiveThread.al = al;
    }

    @Override
    public void run() {
        try {
            String str;
            din = new DataInputStream(s.getInputStream());
            while (true) {
                str = din.readUTF();
                distribute(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void distribute(String str) throws IOException {
        Iterator i = al.iterator();
        Socket st;
        while (i.hasNext()) {
            st = (Socket) i.next();
            dout = new DataOutputStream(st.getOutputStream());
            dout.writeUTF(str);
            dout.flush();
        }
    }
}