package com.bartoszek.jtzrestapi;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppView extends JFrame {

    private ResourceBundle resourceBundle;

    private JPanel allQuestionsJPanel;
    private JPanel questionOneJPanel;
    private JLabel qOneQuestionJLabel;
    private JComboBox<String> qOneJComboBox;
    private JLabel qOneQuestionTwoJLabel;
    private JLabel qOneAnswerJLabel;
    private JTextField qOneAnswerJTextField;
    private JPanel questionTwoJPanel;
    private JLabel qTwoQuestionJLabel;
    private JComboBox<String> qTwoJComboBox;
    private JLabel qTwoAnswerJLabel;
    private JTextField qTwoAnswerJTextField;
    private JLabel languageJLabel;
    private JComboBox<String> languageJComboBox;
    public AppView(String title) {
        super(title);
        initFrame();
    }

    private void initFrame() {
        resourceBundle = ResourceBundle.getBundle("AppView");

        //mainPanel
        allQuestionsJPanel = new JPanel();
        allQuestionsJPanel.setLayout(new GridLayout(2,1));
        //questions panels
        questionOneJPanel = configureQuestionOneJPanel();
        questionTwoJPanel = configureQuestionTwoJPanel();
        allQuestionsJPanel.add(questionOneJPanel);
        allQuestionsJPanel.add(questionTwoJPanel);
        //locale box
        JPanel languageJPanel = configureLanguageJPanel();

        getContentPane().add(allQuestionsJPanel,BorderLayout.CENTER);
        getContentPane().add(languageJPanel,BorderLayout.SOUTH);

        //JFrame properties
        updateLanguage(new Locale("pl","PL"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel configureLanguageJPanel() {
        JPanel  languageJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languageJLabel = new JLabel();
        languageJComboBox = new JComboBox<>(Utils.LANGUAGES);
        languageJPanel.add(languageJLabel);
        languageJPanel.add(languageJComboBox);
        return languageJPanel;
    }

    private JPanel configureQuestionTwoJPanel() {
        JPanel questionTwoJPanel = new JPanel();
        questionTwoJPanel.setLayout(new BorderLayout());

        qTwoQuestionJLabel = new JLabel();
        qTwoJComboBox = new JComboBox<>(Utils.CURRENCIES);
        //set center
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(qTwoQuestionJLabel);
        horizontalBox.add(qTwoJComboBox);
        questionTwoJPanel.add(horizontalBox,BorderLayout.CENTER);
        //set bottom
        Box horizontalBoxBottom = Box.createHorizontalBox();
        qTwoAnswerJLabel = new JLabel();
        qTwoAnswerJTextField = new JTextField(20);
        horizontalBoxBottom.add(qTwoAnswerJLabel);
        horizontalBoxBottom.add(qTwoAnswerJTextField);
        questionTwoJPanel.add(horizontalBoxBottom,BorderLayout.SOUTH);
        return questionTwoJPanel;
    }

    public void updateLanguage(Locale locale){
        resourceBundle = ResourceBundle.getBundle("AppView",locale);
        allQuestionsJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("allQuestionsJPanel")));
        questionOneJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("questionOneJPanel")));
        qOneQuestionJLabel.setText(resourceBundle.getString("qOneQuestionJLabel"));
        qOneJComboBox=new JComboBox<>(Utils.COUNTRIES_EN);
        qOneQuestionTwoJLabel.setText(resourceBundle.getString("qOneQuestionTwoJLabel"));
        qOneAnswerJLabel.setText(resourceBundle.getString("qOneAnswerJLabel"));
        questionTwoJPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("questionTwoJPanel")));
        qTwoQuestionJLabel.setText(resourceBundle.getString("qTwoQuestionJLabel"));
        qTwoJComboBox = new JComboBox<>(Utils.CURRENCIES);
        qTwoAnswerJLabel.setText(resourceBundle.getString("qTwoAnswerJLabel"));
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
        qOneAnswerJTextField = new JTextField(20);
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(qOneQuestionJLabel);
        horizontalBox.add(qOneJComboBox);
        horizontalBox.add(qOneQuestionTwoJLabel);
        horizontalBox.add(qOneAnswerJTextField);
        questionOneJPanel.add(horizontalBox,BorderLayout.CENTER);

        //set answer view
        qOneAnswerJLabel = new JLabel();
        qOneAnswerJTextField = new JTextField(20);
        Box horizontalBoxBottom = Box.createHorizontalBox();
        horizontalBoxBottom.add(qOneAnswerJLabel);
        horizontalBoxBottom.add(qOneAnswerJTextField);
        questionOneJPanel.add(horizontalBoxBottom,BorderLayout.SOUTH);
        return questionOneJPanel;
    }


}