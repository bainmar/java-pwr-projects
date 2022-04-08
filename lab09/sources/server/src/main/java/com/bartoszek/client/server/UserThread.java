package com.bartoszek.client.server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class UserThread extends Thread {
    private Socket socket;
    private ChatServerController server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServerController server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();) {

            writer = new PrintWriter(output,true);

            String userData = reader.readLine();
            String[] split = userData.split("\\|");

            String userName = split[0];
            String userE = split[1];
            String userN = split[2];
            System.out.println("New user public key= "+ Arrays.asList(split).toString());

            User user = new User(userE, userN, userName);
            int id = user.getID();
            userName = userName + "_id_" + id;
            server.addUserName(userName);
            server.addUserData(userName, user);

            String serverMessage = "com.bartoszek.new|" + userName;
            server.broadcastExcludeUser(serverMessage, this);
            server.broadcastWithUser("com.bartoszek.users|"+server.getUsersNames().size()+"|"+server.getUsersNamesHeader());
            String keyMessage="com.bartoszek.server|"+server.getRsaEncoderAndDecoder().getE().toString()+"|"+
                    server.getRsaEncoderAndDecoder().getN().toString();
            server.unicastSendKey(keyMessage,this);

            String clientMessage;
            do {
                clientMessage = reader.readLine();
                if(clientMessage!=null) {

                    serverMessage = "com.bartoszek.message|" + userName + "|" + clientMessage;

                    server.broadcastWithUser(serverMessage);
                    System.out.println("wiadomosć w pętli servera: " + serverMessage);
                }else{
                    System.out.println("Użytkownik opuśćił niespodziewanie czat");
                    break;
                }
            } while (!serverMessage.equals("com.bartoszek.message|" + userName + "|quit" ));

            server.removeUser(userName, this);
            server.removeUserData(userName);
            serverMessage = "com.bartoszek.quit|" + userName;
            server.broadcastExcludeUser(serverMessage, this);
            server.broadcastExcludeUser("com.bartoszek.users|"+server.getUsersNames().size()+"|"+server.getUsersNamesHeader(),this);

        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if(writer!=null){
                writer.close();
            }
        }
    }
    void sendMessage(String message) {
        writer.println(message);
    }
}

