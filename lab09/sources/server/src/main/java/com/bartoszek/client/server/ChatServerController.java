package com.bartoszek.client.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerController {
    private int port;
    private Map<String,User> usersPublicKeys=new HashMap<>();
    private Set<String> usersNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    private ChatServerView chatServerView;
    private RSAEncoderAndDecoder rsaEncoderAndDecoder;

    public ChatServerController(ChatServerView chatServerView) {
        this.chatServerView = chatServerView;
        this.rsaEncoderAndDecoder=new RSAEncoderAndDecoder();
    }


    public void init() {

        chatServerView.getStartJButton().addActionListener((e) -> {
            try {
                startServer();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    private void startServer() throws IOException {
        String portNumberString = chatServerView.getPortJTextField().getText();
        port = Integer.parseInt(portNumberString);
        chatServerView.getStartJButton().setEnabled(false);
        chatServerView.getPortJTextField().setEnabled(false);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void broadcastExcludeUser(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
    void broadcastWithUser(String message) {
        for (UserThread aUser : userThreads) {
                aUser.sendMessage(message);
        }
    }
    void unicastSendKey(String message,UserThread user){
        user.sendMessage(message);
    }

    boolean hasUsers() {
        return !usersNames.isEmpty();
    }
    public Set<String> getUsersNames() {
        return usersNames;
    }
    String getUsersNamesHeader() {
        StringBuilder builder = new StringBuilder();
        for(String user:usersNames){
            builder.append(user);
            builder.append("|");
        }
        return builder.toString();
    }

    public void addUserData(String userName,User userData){
        usersPublicKeys.put(userName,userData);
    }
    public void removeUserData(String userName){
        usersPublicKeys.remove(userName);
    }

    void addUserName(String userName) {
        usersNames.add(userName);
    }

    void removeUser(String userName, UserThread aUser) {
        boolean removed = usersNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
        }
    }

//////////////////


    public RSAEncoderAndDecoder getRsaEncoderAndDecoder() {
        return rsaEncoderAndDecoder;
    }
}