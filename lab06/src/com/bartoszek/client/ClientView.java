package com.bartoszek.client;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ClientView implements Serializable {
    //elements
    private JFrame myFrame;
    private JLabel newAdvertisementTitleLabel;
    private JTextArea newAdvertisementTextField;
    private JLabel displayTimeLabel;
    private JTextField displayTimeTextField;
    private JButton sendOrderButton;
    private JLabel ordersLabel;
    private JComboBox<String> ordersIdComboBox;
    private JButton withdrawOrderButton;
    private JButton removeOrderButton;
    private JLabel myAdvertisementsLabel;
    private JTextArea myAdvertisementsDetailsTextField;

    public ClientView(String title) {
        myFrame = new JFrame(title);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        myFrame.setBounds(0, 0, screenWidth / 3, screenHeight);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create UI elements
        newAdvertisementTitleLabel = new JLabel("Nowe ogłoszenie");
        newAdvertisementTextField = new JTextArea();
        newAdvertisementTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JScrollPane jScrollPaneNewAd = new JScrollPane(newAdvertisementTextField,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        displayTimeLabel = new JLabel("Czas: (l. godzin)");
        displayTimeTextField = new JTextField();
        sendOrderButton = new JButton("Wyślij");
        ordersLabel = new JLabel("Zamówienia ID");
        ordersIdComboBox = new JComboBox<>();
        removeOrderButton = new JButton("Usuń");
        withdrawOrderButton = new JButton("Wycofaj");
        myAdvertisementsDetailsTextField = new JTextArea();
        myAdvertisementsDetailsTextField.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 16));
        myAdvertisementsDetailsTextField.setEditable(false);
        JScrollPane jScrollPaneAdDetails = new JScrollPane(myAdvertisementsDetailsTextField,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myAdvertisementsLabel = new JLabel("Moje ogłoszenia");
        //add UI to frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        mainPanel.setLayout(new GridLayout(2, 1, 20, 10));
        SpringLayout springLayoutNorth = new SpringLayout();
        northPanel.setLayout(springLayoutNorth);
        SpringLayout springLayoutSouth = new SpringLayout();
        southPanel.setLayout(springLayoutSouth);
        northPanel.add(newAdvertisementTitleLabel);
        northPanel.add(jScrollPaneNewAd);
        northPanel.add(displayTimeLabel);
        northPanel.add(displayTimeTextField);
        northPanel.add(sendOrderButton);
        northPanel.add(ordersLabel);
        northPanel.add(ordersIdComboBox);
        northPanel.add(removeOrderButton);
        northPanel.add(withdrawOrderButton);
        southPanel.add(myAdvertisementsLabel);
        southPanel.add(jScrollPaneAdDetails);
        //constraints
        springLayoutNorth.putConstraint(SpringLayout.NORTH, newAdvertisementTitleLabel, 5, SpringLayout.NORTH, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.WEST, newAdvertisementTitleLabel, 5, SpringLayout.WEST, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.NORTH, jScrollPaneNewAd, 5, SpringLayout.SOUTH, newAdvertisementTitleLabel);
        springLayoutNorth.putConstraint(SpringLayout.SOUTH, jScrollPaneNewAd, -5, SpringLayout.SOUTH, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.WEST, jScrollPaneNewAd, 5, SpringLayout.WEST, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.EAST, jScrollPaneNewAd, -5, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.EAST, displayTimeLabel, -5, SpringLayout.EAST, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.NORTH, displayTimeLabel, 5, SpringLayout.SOUTH, newAdvertisementTitleLabel);
        springLayoutNorth.putConstraint(SpringLayout.NORTH, displayTimeTextField, 5, SpringLayout.SOUTH, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.EAST, displayTimeTextField, -5, SpringLayout.EAST, northPanel);
        springLayoutNorth.putConstraint(SpringLayout.WEST, displayTimeTextField, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.NORTH, sendOrderButton, 5, SpringLayout.SOUTH, displayTimeTextField);
        springLayoutNorth.putConstraint(SpringLayout.WEST, sendOrderButton, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.SOUTH, ordersLabel, -5, SpringLayout.NORTH, ordersIdComboBox);
        springLayoutNorth.putConstraint(SpringLayout.WEST, ordersLabel, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.SOUTH, ordersIdComboBox, -5, SpringLayout.NORTH, removeOrderButton);
        springLayoutNorth.putConstraint(SpringLayout.WEST, ordersIdComboBox, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.SOUTH, removeOrderButton, -5, SpringLayout.NORTH, withdrawOrderButton);
        springLayoutNorth.putConstraint(SpringLayout.WEST, removeOrderButton, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.WEST, withdrawOrderButton, 0, SpringLayout.WEST, displayTimeLabel);
        springLayoutNorth.putConstraint(SpringLayout.SOUTH, withdrawOrderButton, -15, SpringLayout.SOUTH, northPanel);

        springLayoutSouth.putConstraint(SpringLayout.WEST, myAdvertisementsLabel, 5, SpringLayout.WEST, southPanel);
        springLayoutSouth.putConstraint(SpringLayout.NORTH, myAdvertisementsLabel, 5, SpringLayout.NORTH, southPanel);
        springLayoutSouth.putConstraint(SpringLayout.NORTH, jScrollPaneAdDetails, 10, SpringLayout.SOUTH, myAdvertisementsLabel);
        springLayoutSouth.putConstraint(SpringLayout.WEST, jScrollPaneAdDetails, 10, SpringLayout.WEST, southPanel);
        springLayoutSouth.putConstraint(SpringLayout.EAST, jScrollPaneAdDetails, -10, SpringLayout.EAST, southPanel);
        springLayoutSouth.putConstraint(SpringLayout.SOUTH, jScrollPaneAdDetails, -10, SpringLayout.SOUTH, southPanel);

        mainPanel.add(northPanel);
        mainPanel.add(southPanel);
        myFrame.setContentPane(mainPanel);
        myFrame.setVisible(true);
    }
    public JTextArea getNewAdvertisementTextField() {
        return newAdvertisementTextField;
    }

    public JTextField getDisplayTimeTextField() {
        return displayTimeTextField;
    }

    public JButton getSendOrderButton() {
        return sendOrderButton;
    }

    public JComboBox<String> getOrdersIdComboBox() {
        return ordersIdComboBox;
    }

    public JButton getWithdrawOrderButton() {
        return withdrawOrderButton;
    }

    public JButton getRemoveOrderButton() {
        return removeOrderButton;
    }

    public JTextArea getMyAdvertisementsDetailsTextField() {
        return myAdvertisementsDetailsTextField;
    }

}
