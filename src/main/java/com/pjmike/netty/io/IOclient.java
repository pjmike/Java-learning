package com.pjmike.netty.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author pjmike
 * @create 2018-09-15 23:18
 */
public class IOclient {
    public void receive(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
    }

    public static void main(String[] args) throws IOException {
        new IOclient().receive("127.0.0.1",8888);
    }
}
