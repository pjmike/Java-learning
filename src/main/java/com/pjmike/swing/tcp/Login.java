package com.pjmike.swing.tcp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/09/30 20:22
 */
public class Login implements ActionListener {
    private final JFrame jFrame;
    private final JTextField textField;

    public static void main(String[] args){
        new Login();
    }
    public Login(){
        this.jFrame = new JFrame("登录页面");
        this.textField =new JTextField();
        JButton button = new JButton("登录");
        JLabel heading = new JLabel("QQ");
        heading.setFont(new Font("Impact", Font.BOLD,40));
        JLabel label = new JLabel("请输入你的登录名");
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        JPanel panel = new JPanel();
        button.addActionListener(this);
        panel.add(heading);panel.add(textField);panel.add(label);
        panel.add(button);
        heading.setBounds(100,20,280,80);
        label.setBounds(20,100,250,60);
        textField.setBounds(50,150,150,30);
        button.setBounds(70,200,90,30);
        jFrame.add(panel);
        panel.setLayout(null);
        jFrame.setSize(300, 300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        try{
            String name = textField.getText();
            jFrame.dispose();
            Client client=new Client(name);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
