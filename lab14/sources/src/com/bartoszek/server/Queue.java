package com.bartoszek.server;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.swing.*;

public class Queue extends NotificationBroadcasterSupport implements QueueMBean{

    private String categories;
    private String priorities;
    private long sequenceNumber = 1;
    private QueueController controller;


    public Queue(String categories, String priorities) {
        this.categories = categories;
        this.priorities = priorities;
    }
    public void addController(QueueController controller){
        this.controller=controller;
    }

    @Override
    public String getCategories() {
        return categories;
    }

    @Override
    public String getPriorities() {
        return priorities;
    }

    @Override
    public boolean setPriorities(String priorities) {
        String oldPriorities=this.priorities;
        this.priorities=priorities;
        Notification n = new AttributeChangeNotification(this,
                sequenceNumber++,System.currentTimeMillis(),
                "priorities changed", "priorities","String",
                oldPriorities,this.priorities);
        sendNotification(n);
        if(controller!=null){
            controller.reloadPrioritiesInput(priorities);
        }
        return true;
    }

    @Override
    public boolean setCategories(String categories) {
        String oldCategories=this.categories;
        this.categories=categories;
        Notification n=new AttributeChangeNotification(this,
                sequenceNumber++,System.currentTimeMillis(),
                "categories changed","categories","String",
                oldCategories,this.categories);
       this.categories=categories;
       sendNotification(n);
        if(controller!=null){
            controller.reloadCategoriesInput(categories);
            String[] splitArray = categories.split(",");
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            model.removeAllElements();
            for(String element:splitArray){
                model.addElement(element);
            }
            controller.getQueueView().getCategoriesJComboBox().setModel(model);        }
       return true;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
