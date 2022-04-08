package com.bartoszek.client;

import java.awt.*;

public class ChatClientApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ChatClientView chatClientView=new ChatClientView();
            ChatClientController chatClientController=new ChatClientController(chatClientView);
            chatClientController.init();
        });
    }

}
