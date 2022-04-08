package com.bartoszek.manager;

import javax.swing.*;
import java.awt.*;

public class ManagerView {
    private JFrame managerFrame;
    private JLabel tablesLabel;
    private JTextArea tablesDataJTextArea;
    private JLabel registeredTablesJLabel;
    private JComboBox<String> registeredTableIdJComboBox;
    private JButton startThreadBtn;
    private JButton stopThreadBtn;

    public ManagerView(String title) {
        managerFrame = new JFrame();
        managerFrame.setTitle(title);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        managerFrame.setBounds(screenWidth / 3, 0, screenWidth / 3, screenHeight / 2);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //UI elements
        tablesLabel = new JLabel("Tablice");
        tablesDataJTextArea = new JTextArea();
        tablesDataJTextArea.setEditable(false);
        tablesDataJTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JScrollPane jScrollPaneManager = new JScrollPane(tablesDataJTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        registeredTablesJLabel = new JLabel("Tablica ID");
        registeredTableIdJComboBox = new JComboBox<>();
        startThreadBtn = new JButton("Start");
        stopThreadBtn = new JButton("Stop");

        //add UI to frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);
        mainPanel.add(tablesLabel);
        mainPanel.add(jScrollPaneManager);
        mainPanel.add(registeredTablesJLabel);
        mainPanel.add(registeredTableIdJComboBox);
        mainPanel.add(startThreadBtn);
        mainPanel.add(stopThreadBtn);

        //set coordinates
        layout.putConstraint(SpringLayout.NORTH, tablesLabel, 5, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, tablesLabel, 5, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneManager
                , 5, SpringLayout.SOUTH, tablesLabel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneManager
                , 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.EAST, jScrollPaneManager
                , -10, SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, jScrollPaneManager
                , -15, SpringLayout.NORTH, registeredTablesJLabel);
        layout.putConstraint(SpringLayout.WEST, registeredTablesJLabel, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, registeredTablesJLabel, -5, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, registeredTableIdJComboBox, 10, SpringLayout.EAST, registeredTablesJLabel);
        layout.putConstraint(SpringLayout.SOUTH, registeredTableIdJComboBox, -5, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, startThreadBtn, 10, SpringLayout.EAST, registeredTableIdJComboBox);
        layout.putConstraint(SpringLayout.SOUTH, startThreadBtn, -5, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, stopThreadBtn, 5, SpringLayout.EAST, startThreadBtn);
        layout.putConstraint(SpringLayout.SOUTH, stopThreadBtn, -5, SpringLayout.SOUTH, mainPanel);
        managerFrame.setContentPane(mainPanel);
        managerFrame.setVisible(true);
    }

    public JTextArea getTablesDataTextField() {
        return tablesDataJTextArea;
    }

    public JComboBox<String> getRegisteredTableIdJComboBox() {
        return registeredTableIdJComboBox;
    }

    public JButton getStartThreadBtn() {
        return startThreadBtn;
    }

    public JButton getStopThreadBtn() {
        return stopThreadBtn;
    }
}
