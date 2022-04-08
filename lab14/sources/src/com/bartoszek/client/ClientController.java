package com.bartoszek.client;

import com.bartoszek.server.QueueMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.*;
import java.io.IOException;

public class ClientController {

    private ClientModel clientModel;
    private ClientView clientView;
    private JMXServiceURL url;
    private JMXConnector jmxc;
    private ClientListener listener;
    private MBeanServerConnection mbsc;
    private QueueMBean mbeanProxy;

    public ClientController(ClientModel clientModel, ClientView clientView) {
        this.clientView = clientView;
        this.clientModel = clientModel;
    }

    public void init() throws IOException, MalformedObjectNameException, InstanceNotFoundException {
        clientView.getDataJButton().addActionListener((e) -> {
            changeMBeanData();
        });
        url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        jmxc = JMXConnectorFactory.connect(url, null);
        listener = new ClientListener();
        mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("com.bartoszek.server:type=Queue");
        mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, QueueMBean.class, true);
        mbsc.addNotificationListener(mbeanName, listener, null, null);
    }

    private void changeMBeanData() {
        String categories = clientView.getCategoriesJTextField().getText();
        String priorities = clientView.getPrioritiesJTextField().getText();
        if(categories.length()==priorities.length()){
            mbeanProxy.setCategories(categories);
            mbeanProxy.setPriorities(priorities);
        }else{
            JOptionPane.showMessageDialog(clientView.getMyFrame(),"Nieprawidłowew dane");
        }

    }
    public class ClientListener implements NotificationListener {

        public void handleNotification(Notification notification,
                                       Object handback) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n(\"Nowe powiadomienie:\");");
            builder.append(("\nClassName: " + notification.getClass().getName()));
            builder.append("\nSource: " + notification.getSource());
            builder.append("\nType: " + notification.getType());
            builder.append("\nMessage: " + notification.getMessage());

            if (notification instanceof AttributeChangeNotification) {
                AttributeChangeNotification acn =
                        (AttributeChangeNotification) notification;
                builder.append("\nAttributeName: " + acn.getAttributeName());
                builder.append("\nAttributeType: " + acn.getAttributeType());
                builder.append("\nNewValue: " + acn.getNewValue());
                builder.append("\nOldValue: " + acn.getOldValue());
                builder.append("\n");
                String categories = mbeanProxy.getCategories();
                String priorities = mbeanProxy.getPriorities();
                clientView.getPrioritiesJTextField().setText(priorities);
                clientView.getCategoriesJTextField().setText(categories);
            }
            String text = clientView.getNotificationJTextArea().getText();
            text = text + builder.toString();
            clientView.getNotificationJTextArea().setText(text);

        }
    }

}
