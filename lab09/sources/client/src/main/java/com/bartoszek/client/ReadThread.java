package com.bartoszek.client;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;


public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClientController client;


    public ReadThread(Socket socket, ChatClientController client) throws IOException {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void run() {

        while (true) {
            try {
                String response = reader.readLine();
                if(response==null){
                    System.exit(1);
                    break;
                }

                String[] split = response.split("\\|");
                System.out.println("wiadomość w pętli clienta: "+ Arrays.toString(split));
                ChatClientView chatClientView = client.getChatClientView();
                if(split[0].equals("com.bartoszek.new")){
                    JTextArea aMessage = chatClientView.getConversationJTextArea();
                    String text = aMessage.getText();
                    text=text+"\n----->Dołączył użytkownik: "+split[1];
                    aMessage.setText(text);

                }else if(split[0].equals("com.bartoszek.message")){
                    JTextArea aMessage = chatClientView.getConversationJTextArea();
                    String user=split[1];

                    String message="["+user+"] "+split[2];
                    String text =aMessage.getText();
                    text=text+"\n"+message;
                    aMessage.setText(text);

                }else if(split[0].equals("com.bartoszek.quit")){
                    JTextArea aMessage = chatClientView.getConversationJTextArea();
                    String text = aMessage.getText();
                    text=text+"\n----->Użytkownik "+split[1]+" opuścił pokój rozmów.";
                    aMessage.setText(text);

                }else if(split[0].equals("com.bartoszek.users")){

                    int numberOfConnectedUsers= Integer.parseInt(split[1]);
                    StringBuilder builder=new StringBuilder();
                    String newList=null;
                    for(int i=0;i<numberOfConnectedUsers;i++) {
                        builder.append(split[2 + i]);
                        builder.append("\n");
                        newList = builder.toString();
                    }
                    chatClientView.getReceiversJTextArea().setText(newList);
                }else if(split[0].equals("com.bartoszek.server")){
                    String serverName=split[0];
                    String e=split[1];
                    String n=split[2];
                    client.setServerName(serverName);
                    client.setServerE(e);
                    client.setServerN(n);
                }
                else{
                    JTextArea aMessage = chatClientView.getConversationJTextArea();
                    String text =aMessage.getText();
                    text=text+"\n----->Nieznany nagłówek wiadomośći.";
                    aMessage.setText(text);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}