package com.bartoszek.bilboard;

import javax.swing.*;
import java.awt.*;

public class TableView {
    private JFrame myFrame;
    private JLabel tableAddLabel;
    private JTextArea tableAdJTextArea;
    private JButton unbindButton;



    public TableView(String title){
        myFrame=new JFrame(title);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        myFrame.setBounds(screenWidth*2/3, 0, screenWidth / 3, screenHeight/3);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //UI elements
        tableAddLabel=new JLabel("Ogłoszenie");
        tableAdJTextArea =new JTextArea();
        tableAdJTextArea.setFont(new Font( "Times New Roman",  Font.BOLD+Font.ITALIC, 16 ) );
        tableAdJTextArea.setEditable(false);
        JScrollPane jScrollTableArea= new JScrollPane(tableAdJTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        unbindButton=new JButton("wyrejestruj");
        //add UI to frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        SpringLayout layout=new SpringLayout();
        mainPanel.setLayout(layout);
        mainPanel.add(tableAddLabel);
        mainPanel.add(jScrollTableArea);
        mainPanel.add(unbindButton);

        layout.putConstraint(SpringLayout.NORTH,tableAddLabel,5,SpringLayout.NORTH,mainPanel);
        layout.putConstraint(SpringLayout.WEST,tableAddLabel,5,SpringLayout.WEST,mainPanel);
        layout.putConstraint(SpringLayout.NORTH,jScrollTableArea
                ,5,SpringLayout.SOUTH,tableAddLabel);
        layout.putConstraint(SpringLayout.WEST,jScrollTableArea
                ,10,SpringLayout.WEST,mainPanel);
        layout.putConstraint(SpringLayout.EAST,jScrollTableArea
                ,-10,SpringLayout.EAST,mainPanel);
        layout.putConstraint(SpringLayout.SOUTH,jScrollTableArea
                ,-5,SpringLayout.NORTH,unbindButton);
        layout.putConstraint(SpringLayout.WEST,unbindButton,10,SpringLayout.WEST,mainPanel);
        layout.putConstraint(SpringLayout.SOUTH,unbindButton,-5,SpringLayout.SOUTH,mainPanel);
        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);

    }
    public JTextArea getTableAddJTextArea() {
        return tableAdJTextArea;
    }

    public JButton getUnbindButton() {
        return unbindButton;
    }



}
