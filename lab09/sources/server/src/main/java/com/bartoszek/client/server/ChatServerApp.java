package com.bartoszek.client.server;

import java.awt.*;

public class ChatServerApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ChatServerView chatServerView=new ChatServerView();
            ChatServerController chatServerController=new ChatServerController(chatServerView);
            chatServerController.init();
        });
    }
}
