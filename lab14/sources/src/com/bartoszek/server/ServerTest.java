package com.bartoszek.server;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import java.awt.*;

public class ServerTest {

    public static void main(String[] args) {

        EventQueue.invokeLater(()->{
            QueueView queueView=new QueueView();
            QueueModel queueModel=new QueueModel();

            QueueController queueController = new QueueController(queueModel,queueView);
            try {
                queueController.init();
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
            } catch (NotCompliantMBeanException e) {
                e.printStackTrace();
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            } catch (MBeanRegistrationException e) {
                e.printStackTrace();
            }
        });

    }
}
