package com.bartoszek.client;

import javax.swing.*;
import java.awt.*;

public class ClientView {
    private JTextField categoriesJTextField;
    private JTextField prioritiesJTextField;
    private JTextArea notificationJTextArea;
    private JButton dataJButton;
    private JFrame myFrame;
    public ClientView(){
        myFrame=new JFrame();
        myFrame.setTitle("Client, Author: Marcin Bartoszek");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        myFrame.setBounds(screenWidth * 2/ 3, screenHeight / 4,screenWidth/3,screenHeight/2);

        //MainPane
        JPanel mainPanel=new JPanel();
        GridLayout gridLayout=new GridLayout(2,1,10,10);
        mainPanel.setLayout(gridLayout);

        JPanel modPanel=new JPanel();
        modPanel.setBorder(BorderFactory.createTitledBorder("własności"));
        modPanel.setLayout(new GridLayout(5,1,5,5));
        modPanel.add(new JLabel("lista kategorii spraw"));
        categoriesJTextField=new JTextField();
        modPanel.add(categoriesJTextField);
        modPanel.add(new JLabel("lista priorytetów kategorii"));
        prioritiesJTextField=new JTextField();
        modPanel.add(prioritiesJTextField);
        dataJButton=new JButton("Potwierdź");
        modPanel.add(dataJButton);
        mainPanel.add(modPanel);

        JPanel notPanel = new JPanel();
        notPanel.setBorder(BorderFactory.createTitledBorder("powiadomienia"));
        notPanel.setLayout(new GridLayout(1,1));
        notificationJTextArea=new JTextArea();
        notificationJTextArea.setFont(new Font( "Times New Roman",  Font.BOLD, 16 ) );
        notificationJTextArea.setEditable(false);
        JScrollPane jScrollnot= new JScrollPane(notificationJTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notPanel.add(jScrollnot);
        mainPanel.add(notPanel);

        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);
    }

    public JTextField getCategoriesJTextField() {
        return categoriesJTextField;
    }

    public JTextField getPrioritiesJTextField() {
        return prioritiesJTextField;
    }

    public JTextArea getNotificationJTextArea() {
        return notificationJTextArea;
    }

    public JButton getDataJButton() {
        return dataJButton;
    }

    public JFrame getMyFrame() {
        return myFrame;
    }
}
