package com.pjmike.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/25 23:15
 */
public class SwingTest {
    private int MAXNUM=1;
    Queue<String> queue = new LinkedList<String>();

    private void  UpdateReceivingArea(){
        while(queue.size()>MAXNUM){
            queue.remove();
        }
        ta_recieve.setText("");
        for (String q :queue){
            ta_recieve.append(q);
        }
    }

    public SwingTest() {
        btn_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queue.add(ep_send.getText()+"\n");
                UpdateReceivingArea();
                ep_send.setText("");
            }
        });

        cb_History.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()){
                    case ItemEvent.SELECTED:
                        MAXNUM=Integer.parseInt(e.getItem().toString());
                        System.out.println("×î´óÏÔÊ¾"+MAXNUM);
                        UpdateReceivingArea();
                        break;
                    default:break;
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("16010130038ÁÎ³ÐêÍ");
        frame.setContentPane(new SwingTest().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JButton btn_send;
    private JTextArea ta_recieve;
    private JEditorPane ep_send;
    private JComboBox cb_History;
}
