package com.bartoszek.jtzrestapi;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppView extends JFrame {

    private JMenu infoJMenu;
    private JMenuItem authorJMenuItem;
    private ResourceBundle resourceBundle;
    private JPanel allQuestionsJPanel;
    private JPanel questionOneJPanel;
    private JLabel qOneQuestionJLabel;
    private JComboBox<String> qOneJComboBox;
    private JLabel qOneQuestionTwoJLabel;
    private JTextField qOneQuestionJTextField;
    private JLabel qOneAnswerJLabel;
    private JTextField qOneAnswerJTextField;
    private JButton qOneJButton;
    private JPanel questionTwoJPanel;
    private JLabel qTwoQuestionJLabel;
    private JComboBox<String> qTwoJComboBox;
    private JLabel qTwoAnswerJLabel;
    private JComboBox<String> qTwoAnswerJComboBox;
    private JButton qTwoJButton;
    private JLabel languageJLabel;
    private JComboBox<String> languageJComboBox;
    public AppView(String title) {
        super(title);
        initFrame();
    }

    public JButton getqOneJButton() {
        return qOneJButton;
    }

    public JComboBox<String> getqOneJComboBox() {
        return qOneJComboBox;
    }

    public JTextField getqOneQuestionJTextField() {
        return qOneQuestionJTextField;
    }

    public JTextField getqOneAnswerJTextField() {
        return qOneAnswerJTextField;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public JComboBox<String> getqTwoJComboBox() {
        return qTwoJComboBox;
    }

    public JComboBox<String> getqTwoAnswerJComboBox() {
        return qTwoAnswerJComboBox;
    }

    public JButton getqTwoJButton() {
        return qTwoJButton;
    }

    private void initFrame() {
        resourceBundle = ResourceBundle.getBundle("AppView");
        //menu bar
        JMenuBar jMenuBar = createJMenuBar();
        setJMenuBar(jMenuBar);

        //mainPanel
        allQuestionsJPanel = new JPanel();
        allQuestionsJPanel.setLayout(new BoxLayout(allQuestionsJPanel,BoxLayout.Y_AXIS));
        //questions panels
        questionOneJPanel = configureQuestionOneJPanel();
        questionTwoJPanel = configureQuestionTwoJPanel();
        allQuestionsJPanel.add(questionOneJPanel);
        allQuestionsJPanel.add(questionTwoJPanel);
        //locale box
        JPanel languageJPanel = configureLanguageJPanel();
        allQuestionsJPanel.add(languageJPanel);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(allQuestionsJPanel);
        //JFrame properties
        updateLanguage(new Locale("en","US"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel configureLanguageJPanel() {
        JPanel  languageJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languageJLabel = new JLabel();
        languageJComboBox = new JComboBox<>(Utils.LANGUAGES);
        languageJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox<String> ev = (JComboBox) e.getSource();
                int selectedIndex = ev.getSelectedIndex();
                switch (selectedIndex) {
                    case 1:
                        updateLanguage(new Locale("pl","PL"));
                        break;
                    default:
                        updateLanguage(new Locale("en","US"));
                }
            }
        });

        languageJPanel.add(languageJLabel);
        languageJPanel.add(languageJComboBox);
        return languageJPanel;
    }

    private JPanel configureQuestionTwoJPanel() {
        JPanel questionTwoJPanel = new JPanel();
        questionTwoJPanel.setLayout(new BorderLayout());

        qTwoQuestionJLabel = new JLabel();
        qTwoJComboBox = new JComboBox<>(Utils.COUNTRIES_EN);
        //set center
        JPanel horizontalJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        horizontalJPanel.add(qTwoQuestionJLabel);
        horizontalJPanel.add(qTwoJComboBox);
        questionTwoJPanel.add(horizontalJPanel,BorderLayout.CENTER);
        //set bottom
        JPanel horizontalBottomJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qTwoAnswerJLabel = new JLabel();
        qTwoAnswerJComboBox = new JComboBox<>(Utils.CURRENCIES);
        qTwoJButton = new JButton();
        horizontalBottomJPanel.add(qTwoAnswerJLabel);
        horizontalBottomJPanel.add(qTwoAnswerJComboBox);
        horizontalBottomJPanel.add(qTwoJButton);
        questionTwoJPanel.add(horizontalBottomJPanel,BorderLayout.SOUTH);
        return questionTwoJPanel;
    }

    public void updateLanguage(Locale locale){
        resourceBundle = ResourceBundle.getBundle("AppView",locale);
        infoJMenu.setText(resourceBundle.getString("infoJMenu"));
        authorJMenuItem.setText(resourceBundle.getString("authorJMenuItem"));
        allQuestionsJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("allQuestionsJPanel")));
        questionOneJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("questionOneJPanel")));
        qOneQuestionJLabel.setText(resourceBundle.getString("qOneQuestionJLabel"));
        qOneQuestionTwoJLabel.setText(resourceBundle.getString("qOneQuestionTwoJLabel"));
        qOneAnswerJLabel.setText(resourceBundle.getString("qOneAnswerJLabel"));
        qOneJButton.setText(resourceBundle.getString("qOneJButton"));
        questionTwoJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("questionTwoJPanel")));
        qTwoQuestionJLabel.setText(resourceBundle.getString("qTwoQuestionJLabel"));
        qTwoAnswerJLabel.setText(resourceBundle.getString("qTwoAnswerJLabel"));
        qTwoJButton.setText(resourceBundle.getString("qTwoJButton"));
        languageJLabel.setText(resourceBundle.getString("languageJLabel"));
        pack();
    }

    private JPanel configureQuestionOneJPanel() {
        //question one panel
        JPanel questionOneJPanel = new JPanel();
        questionOneJPanel.setLayout(new BorderLayout());

        //set question view
        qOneQuestionJLabel = new JLabel();
        qOneJComboBox = new JComboBox<>(Utils.COUNTRIES_EN);
        qOneQuestionTwoJLabel = new JLabel();
        qOneQuestionJTextField = new JTextField();
        qOneQuestionJTextField.setPreferredSize(new Dimension(100,20));

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(qOneQuestionJLabel);
        horizontalBox.add(Box.createHorizontalStrut(5));
        horizontalBox.add(qOneJComboBox);
        horizontalBox.add(Box.createHorizontalStrut(5));
        horizontalBox.add(qOneQuestionTwoJLabel);
        horizontalBox.add(Box.createHorizontalStrut(5));
        horizontalBox.add(qOneQuestionJTextField);
        questionOneJPanel.add(horizontalBox,BorderLayout.CENTER);

        //set answer view
        qOneAnswerJLabel = new JLabel();
        qOneAnswerJTextField = new JTextField();
        qOneAnswerJTextField.setPreferredSize(new Dimension(100,20));
        qOneJButton = new JButton();

        JPanel bottomJPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomJPanel.add(qOneAnswerJLabel);
        bottomJPanel.add(qOneAnswerJTextField);
        bottomJPanel.add(qOneJButton);
        questionOneJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        return questionOneJPanel;
    }

    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        //option
        infoJMenu = new JMenu();
        infoJMenu.setMnemonic(KeyEvent.VK_I);
        jMenuBar.add(infoJMenu);

        authorJMenuItem = new JMenuItem();
        authorJMenuItem.setMnemonic(KeyEvent.VK_A);
        authorJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        authorJMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AppView.this,"@bainmar",resourceBundle.getString("authorJMenuItem"),JOptionPane.INFORMATION_MESSAGE);
            }
        });
        infoJMenu.add(authorJMenuItem);
        return jMenuBar;
    }


}