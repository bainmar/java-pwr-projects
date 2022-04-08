package com.bartoszek.client;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class ChatClientController {
    private String hostname;
    private int port;
    private String userName;
    private ChatClientView chatClientView;
    PrintStream printStream;
    RSAEncoderAndDecoder rsaEncoderAndDecoder;
    private java.util.List<String> connectedUsers=new ArrayList<>();
    private String serverName;
    private String serverE;
    private String serverN;



    public ChatClientController(ChatClientView chatClientView) {
        this.chatClientView = chatClientView;
        rsaEncoderAndDecoder=new RSAEncoderAndDecoder();
    }

    public void init() {

        chatClientView.getConnectButton().addActionListener((e) -> {
            connectWithServer();
        });
        chatClientView.getMessageJButton().addActionListener((e) -> {
            sendMessageToUsers();
        });
        chatClientView.getSaveJButton().addActionListener((e) -> {
            saveMessageToFileSystem();
        });
        chatClientView.getLoadJButton().addActionListener((e) -> {
            lodMessagesFromFileSystem();
        });
        chatClientView.getClearJButton().addActionListener((e) -> {
            clearMessagesFromWindow();
        });
        chatClientView.getHostJTextField().setText("localhost");

    }

    public ChatClientView getChatClientView() {
        return chatClientView;
    }

    private void clearMessagesFromWindow() {
    }

    private void lodMessagesFromFileSystem() {

    }

    private void saveMessageToFileSystem() {
    }

    private void sendMessageToUsers() {
        String messageTest = chatClientView.getMessageJTextField().getText();
        printStream.println(messageTest);
        chatClientView.getMessageJTextField().setText("");
        chatClientView.getMessageJTextField().requestFocus();
    }

    private void connectWithServer() {
        hostname = chatClientView.getHostJTextField().getText();
        String portText = chatClientView.getPortJTextField().getText();
        port = Integer.parseInt(portText);
        userName = chatClientView.getSenderJTextField().getText();

        if (port > 1023 && port < 65536 && userName != null) {
            Socket socket = null;
            try {
                socket = new Socket(hostname, port);
                new ReadThread(socket, this).start();
                printStream=new PrintStream(socket.getOutputStream(),true);
                chatClientView.getConnectLabel().setForeground(Color.GREEN);
                chatClientView.getHostJTextField().setEnabled(false);
                chatClientView.getPortJTextField().setEnabled(false);
                chatClientView.getSenderJTextField().setEnabled(false);
                chatClientView.getConnectButton().setEnabled(false);
                printStream.println(
                       userName+"|"+
                        rsaEncoderAndDecoder.getE().toString()+"|"+
                        rsaEncoderAndDecoder.getN().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nieprawidłowe dane wejściowe");
        }

    }

    String getUserName() {
        return this.userName;
    }

    ///////////////////////
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerE() {
        return serverE;
    }

    public void setServerE(String serverE) {
        this.serverE = serverE;
    }

    public String getServerN() {
        return serverN;
    }

    public void setServerN(String serverN) {
        this.serverN = serverN;
    }


}