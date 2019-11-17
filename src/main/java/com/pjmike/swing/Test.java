package com.pjmike.swing;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/09/02 20:39
 */
public class Test extends JFrame implements ActionListener {
    /**
     * 发送组件
     */
    private static JTextArea send;
    /**
     * 接收组件
     */
    private static JTextArea receive;
    /**
     * 设置最大行数
     */
    private static final Integer SIZE_LINE = 2;

    public static void main(String[] args) {
        //窗口组件
        JFrame jFrame = new JFrame("武思同 16010130030");
        //按钮组件
        JButton jButton = new JButton("发送");
        Test te = new Test();
        //设置监听器
        jButton.addActionListener(te);
        send = new JTextArea(5, 16);
        //设置文本框自动换行
        send.setLineWrap(true);
        receive = new JTextArea(5, 16);
        receive.setLineWrap(true);
        //面板组件
        JPanel p = new JPanel();
        p.add(send);
        p.add(jButton);
        p.add(receive);
        jFrame.add(p);
        jFrame.setSize(250, 300);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("发送")) {
            receive.setText(send.getText());
            int numLinesToTrunk = receive.getLineCount() - SIZE_LINE;
            if (numLinesToTrunk > 0) {
                try {
                    int posOfLastLineToTrunk = receive.getLineEndOffset(numLinesToTrunk - 1);
                    receive.replaceRange("", 0, posOfLastLineToTrunk);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
            send.setText("  ");
        }
    }
}