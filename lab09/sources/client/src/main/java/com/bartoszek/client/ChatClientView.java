package com.bartoszek.client;

import javax.swing.*;
import java.awt.*;

public class ChatClientView {
    private JTextField hostJTextField;
    private JTextField portJTextField;
    private JButton connectButton;
    private JLabel connectLabel;
    private JTextField senderJTextField;
    private JTextArea receiversJTextArea;
    private JTextField messageJTextField;
    private JButton messageJButton;
    private JButton saveJButton;
    private JButton loadJButton;
    private JButton clearJButton;
    private JTextArea conversationJTextArea;

    public ChatClientView(){
        JFrame myFrame = new JFrame("Client, Autor: Bartoszek Marcin");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        myFrame.setBounds(0, 0, screenWidth / 3, screenHeight);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel=new JPanel();
        SpringLayout springLayout=new SpringLayout();
        mainPanel.setLayout(springLayout);

        JPanel serverDataJPanel = new JPanel();
        serverDataJPanel.setLayout(new GridLayout(3,2,10,10));
        JLabel hostJLabel = new JLabel("host");
        hostJTextField=new JTextField(5);
        JLabel portJLabel = new JLabel("port");
        portJTextField=new JTextField(5);
        connectLabel=new JLabel("OK");
        connectLabel.setForeground(Color.RED);
        connectButton = new JButton("połącz");
        serverDataJPanel.setBorder(BorderFactory.createTitledBorder("Dane servera"));
        serverDataJPanel.add(hostJLabel);
        serverDataJPanel.add(hostJTextField);
        serverDataJPanel.add(portJLabel);
        serverDataJPanel.add(portJTextField);
        serverDataJPanel.add(connectButton);
        serverDataJPanel.add(connectLabel);
        mainPanel.add(serverDataJPanel);

        JPanel senderJPanel =new JPanel();
        senderJPanel.setBorder(BorderFactory.createTitledBorder("Nadawca"));
        senderJTextField=new JTextField(10);
        senderJPanel.add(senderJTextField);
        mainPanel.add(senderJPanel);

        JPanel receiversJPanel=new JPanel();
        receiversJPanel.setBorder(BorderFactory.createTitledBorder("Odbiorcy"));
        receiversJTextArea =new JTextArea(4,8);
        receiversJTextArea.setFont(new Font( "Times New Roman",  Font.BOLD+Font.ITALIC, 16 ) );
        receiversJTextArea.setEditable(false);
        JScrollPane conversationJScrollTableArea= new JScrollPane(receiversJTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        receiversJPanel.add(conversationJScrollTableArea);
        mainPanel.add(receiversJPanel);

        JPanel messageJPanel=new JPanel();
        messageJPanel.setBorder(BorderFactory.createTitledBorder("Wiadomość do wysłania"));
        messageJTextField=new JTextField(30);
        messageJButton=new JButton("wyślij");
        messageJPanel.add(messageJTextField);
        messageJPanel.add(messageJButton);
        mainPanel.add(messageJPanel);

        conversationJTextArea=new JTextArea();
        conversationJTextArea.setFont(new Font( "Times New Roman",  Font.BOLD+Font.ITALIC, 16 ) );
        conversationJTextArea.setEditable(false);
        JScrollPane jScrollTableArea= new JScrollPane(conversationJTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(jScrollTableArea);


        JPanel actionPanel=new JPanel();
        actionPanel.setBorder(BorderFactory.createTitledBorder("akcje"));
        saveJButton = new JButton("zapisz");
        loadJButton=new JButton("wczytaj");
        clearJButton=new JButton("wyczyść");
        actionPanel.add(saveJButton);
        actionPanel.add(loadJButton);
        actionPanel.add(clearJButton);
        mainPanel.add(actionPanel);

        springLayout.putConstraint(SpringLayout.NORTH,serverDataJPanel,5,SpringLayout.NORTH,mainPanel);
        springLayout.putConstraint(SpringLayout.WEST,serverDataJPanel,5,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.WEST,senderJPanel,-5,SpringLayout.EAST,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.NORTH,senderJPanel,0,springLayout.NORTH,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,senderJPanel,0,SpringLayout.SOUTH,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.WEST,receiversJPanel,-5,SpringLayout.EAST,senderJPanel);
        springLayout.putConstraint(SpringLayout.NORTH,receiversJPanel,0,SpringLayout.NORTH,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,receiversJPanel,0,SpringLayout.SOUTH,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.EAST,receiversJPanel,-5,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,messageJPanel,10,SpringLayout.SOUTH,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.WEST,messageJPanel,0,SpringLayout.WEST,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.EAST,messageJPanel,-5,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.WEST,actionPanel,0,SpringLayout.WEST,serverDataJPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,actionPanel,-5,SpringLayout.SOUTH,mainPanel);
        springLayout.putConstraint(SpringLayout.EAST,actionPanel,-5,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.WEST,jScrollTableArea,10,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.EAST,jScrollTableArea,-10,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,jScrollTableArea,10,SpringLayout.SOUTH,messageJPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,jScrollTableArea,-10,SpringLayout.NORTH,actionPanel);


        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);
    }


    public JTextField getHostJTextField() {
        return hostJTextField;
    }

    public JTextField getPortJTextField() {
        return portJTextField;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JTextField getSenderJTextField() {
        return senderJTextField;
    }

    public JTextArea getReceiversJTextArea() {
        return receiversJTextArea;
    }

    public JTextField getMessageJTextField() {
        return messageJTextField;
    }

    public JButton getMessageJButton() {
        return messageJButton;
    }

    public JButton getSaveJButton() {
        return saveJButton;
    }

    public JButton getLoadJButton() {
        return loadJButton;
    }

    public JButton getClearJButton() {
        return clearJButton;
    }

    public JTextArea getConversationJTextArea() {
        return conversationJTextArea;
    }

    public JLabel getConnectLabel() {
        return connectLabel;
    }
}
