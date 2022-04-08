package com.bartoszek.server;

import javax.swing.*;
import java.awt.*;

public class QueueView {
    private JComboBox<String> categoriesJComboBox;
    private JButton genJButton;
    private JTextField generatedNumberJTextField;
    private JTextArea currentCases;
    private JTextField listOfCasesJTextField;
    private JTextField listOfPrioritiesJTextField;
    private JButton changeDataJButton;
    private JFrame myFrame;


    public QueueView(){
        myFrame=new JFrame();
        myFrame.setTitle("Server, Author: Marcin Bartoszek");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        //setSize(screenWidth/2,screenHeight/2);
        myFrame.setBounds(screenWidth /4, screenHeight / 4,screenWidth/3,screenHeight*4/5);

        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        JPanel automatPanel=new JPanel();
        automatPanel.setBorder(BorderFactory.createTitledBorder("Automat biletowy"));
        SpringLayout springLayout=new SpringLayout();
        automatPanel.setLayout(springLayout);
        JLabel categoryJLabel = new JLabel("kategoria");
        categoriesJComboBox=new JComboBox<>(new String[]{"gonzo","test"});
        automatPanel.add(categoryJLabel);
        automatPanel.add(categoriesJComboBox);
        genJButton = new JButton("generuj");
        genJButton.setEnabled(false);
        automatPanel.add(genJButton);
        JLabel generatedNumberJLabel = new JLabel("numer:");
        generatedNumberJTextField=new JTextField(5);
        generatedNumberJTextField.setEditable(false);
        automatPanel.add(generatedNumberJLabel);
        automatPanel.add(generatedNumberJTextField);

        JLabel listCases=new JLabel("lista kategorii spraw");
        automatPanel.add(listCases);
        listOfCasesJTextField=new JTextField();
        automatPanel.add(listOfCasesJTextField);
        JLabel listPriorities=new JLabel("lista priorytetów kategorii");
        listOfPrioritiesJTextField=new JTextField();
        automatPanel.add(listPriorities);
        automatPanel.add(listOfPrioritiesJTextField);
        changeDataJButton=new JButton("Edytuj");
        automatPanel.add(changeDataJButton);
        mainPanel.add(automatPanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder("rozpatrywane sprawy"));
        tablePanel.setLayout(new GridLayout(1,1));
        currentCases=new JTextArea();
        currentCases.setFont(new Font( "Times New Roman",  Font.BOLD+Font.ITALIC, 16 ) );
        currentCases.setEditable(false);
        JScrollPane jScrollCases= new JScrollPane(currentCases,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePanel.add(jScrollCases);
        mainPanel.add(tablePanel);

        //spring layout
        springLayout.putConstraint(SpringLayout.NORTH,categoryJLabel,5,SpringLayout.NORTH,automatPanel);
        springLayout.putConstraint(SpringLayout.WEST,categoryJLabel,5,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,categoriesJComboBox,5,SpringLayout.SOUTH,categoryJLabel);
        springLayout.putConstraint(SpringLayout.WEST,categoriesJComboBox,5,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,genJButton,5,SpringLayout.SOUTH,generatedNumberJTextField);
        springLayout.putConstraint(SpringLayout.WEST,generatedNumberJLabel,5,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,generatedNumberJLabel,5,SpringLayout.SOUTH,categoriesJComboBox);
        springLayout.putConstraint(SpringLayout.NORTH,generatedNumberJTextField,5,SpringLayout.SOUTH,categoriesJComboBox);
        springLayout.putConstraint(SpringLayout.WEST,generatedNumberJTextField,5,SpringLayout.EAST,generatedNumberJLabel);
        springLayout.putConstraint(SpringLayout.NORTH,listCases,30,SpringLayout.SOUTH,genJButton);
        springLayout.putConstraint(SpringLayout.WEST,listCases,5,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,listOfCasesJTextField,5,SpringLayout.SOUTH,listCases);
        springLayout.putConstraint(SpringLayout.WEST,listOfCasesJTextField,5,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.EAST,listOfCasesJTextField,-5,SpringLayout.EAST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,listPriorities,5,SpringLayout.SOUTH,listOfCasesJTextField);
        springLayout.putConstraint(SpringLayout.WEST,listPriorities,5,springLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,listOfPrioritiesJTextField,5,springLayout.SOUTH,listPriorities);
        springLayout.putConstraint(SpringLayout.WEST,listOfPrioritiesJTextField,5,springLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.EAST,listOfPrioritiesJTextField,-5,SpringLayout.EAST,automatPanel);
        springLayout.putConstraint(SpringLayout.WEST,changeDataJButton,10,SpringLayout.WEST,automatPanel);
        springLayout.putConstraint(SpringLayout.NORTH,changeDataJButton,5,SpringLayout.SOUTH,listOfPrioritiesJTextField);
        springLayout.putConstraint(SpringLayout.WEST,changeDataJButton,5,SpringLayout.WEST,automatPanel);

        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);
    }

    public JComboBox<String> getCategoriesJComboBox() {
        return categoriesJComboBox;
    }

    public JButton getGenJButton() {
        return genJButton;
    }

    public JTextField getGeneratedNumberJTextField() {
        return generatedNumberJTextField;
    }

    public JTextArea getCurrentCases() {
        return currentCases;
    }

    public JTextField getListOfCasesJTextField() {
        return listOfCasesJTextField;
    }

    public JTextField getListOfPrioritiesJTextField() {
        return listOfPrioritiesJTextField;
    }

    public JButton getChangeDataJButton() {
        return changeDataJButton;
    }

    public JFrame getMyFrame() {
        return myFrame;
    }
}
