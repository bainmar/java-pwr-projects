package com.bartoszek.client.server;

import javax.swing.*;
import java.awt.*;

public class ChatServerView {
    private JFrame myFrame;
    private JTextField portJTextField;
    private JButton startJButton;

    public ChatServerView(){

        //set frame
        myFrame = new JFrame();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        myFrame.setBounds(screenWidth*2/3, 0, screenWidth / 3, screenHeight/5);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ui elements header
        JPanel headerJPanel=new JPanel();
        headerJPanel.setBorder(BorderFactory.createTitledBorder("Dane serwera"));
        JLabel portJLabel = new JLabel("port");
        portJTextField=new JTextField(10);

        startJButton=new JButton("start");
        headerJPanel.add(portJLabel);
        headerJPanel.add(portJTextField);
        headerJPanel.add(startJButton);

        //ui elements users

        //ui mainPanel
        JPanel mainPanel = new JPanel();
        SpringLayout layout=new SpringLayout();
        mainPanel.setLayout(layout);
        mainPanel.add(headerJPanel);


        layout.putConstraint(SpringLayout.NORTH,headerJPanel,5,SpringLayout.NORTH,mainPanel);
        layout.putConstraint(SpringLayout.WEST,headerJPanel,5,SpringLayout.WEST,mainPanel);
        layout.putConstraint(SpringLayout.EAST,headerJPanel,-5,SpringLayout.EAST,mainPanel);
        layout.putConstraint(SpringLayout.SOUTH,headerJPanel,-5,SpringLayout.SOUTH,mainPanel);


        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);
    }

    public JTextField getPortJTextField() {
        return portJTextField;
    }

    public JButton getStartJButton() {
        return startJButton;
    }

}
