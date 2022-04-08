package com.bartoszek.client;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import java.awt.*;
import java.io.IOException;

public class ClientTest {
    public ClientTest(){

    }
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ClientModel clientModel = new ClientModel();
            ClientView clientView=new ClientView();
            ClientController clientController=new ClientController(clientModel,clientView);
            try {
                clientController.init();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
