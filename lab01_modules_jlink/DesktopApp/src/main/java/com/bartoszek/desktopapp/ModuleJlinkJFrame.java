package com.bartoszek.desktopapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ModuleJlinkJFrame extends JFrame {

    private Box trackInformationBox;
    private JLabel trackJLabel;
    private JTextField trackJTextField;
    private JButton trackJButton;
    private JFileChooser jFileChooser;
    private JPanel changesInformationJPanel;
    private JPanel newJPanel;
    private JPanel modifiedJPanel;
    private JPanel removedJPanel;
    private JTextArea newJTextArea;
    private JTextArea modifiedJTextArea;
    private JTextArea removedJTextArea;
    private JPanel southJPanel;
    private JButton changesJButton;
    public ModuleJlinkJFrame(String title) {
        super(title);
        initFrame();
    }

    private void initFrame() {
        //add menu
        JMenuBar jMenuBar = createJMenuBar();
        setJMenuBar(jMenuBar);

        //change ContentPanel to set border
        changeContentPanelToJPanel();

        //north panel
        trackInformationBox = getTrackJPanel();
        getContentPane().add(trackInformationBox, BorderLayout.NORTH);

        //center panel
        changesInformationJPanel=getChangesJPanel();
        getContentPane().add(changesInformationJPanel,BorderLayout.CENTER);

        //south panel
        southJPanel=getSouthJPanel();
        getContentPane().add(southJPanel,BorderLayout.SOUTH);

        //frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600,400));
        setLocationRelativeTo(null);
    }

    private void changeContentPanelToJPanel() {
        JPanel applicationJPanel = new JPanel(new BorderLayout());
        applicationJPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        setContentPane(applicationJPanel);
    }

    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        //option
        JMenu infoJMenu = new JMenu("Info");
        infoJMenu.setMnemonic(KeyEvent.VK_I);
        jMenuBar.add(infoJMenu);

        //items
        JMenuItem authorJMenuItem = new JMenuItem("autor");
        authorJMenuItem.setMnemonic(KeyEvent.VK_A);
        authorJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //adding options
        infoJMenu.add(authorJMenuItem);
        return jMenuBar;
    }

    private JPanel getSouthJPanel() {
        JPanel southJPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
        southJPanel.setBorder(BorderFactory.createTitledBorder("akcje"));

        changesJButton=new JButton("wyświetl");
        southJPanel.add(changesJButton);
        return southJPanel;
    }

    private JPanel getChangesJPanel() {
        JPanel changesJPanel= new JPanel();
        BoxLayout boxLayout= new BoxLayout(changesJPanel,BoxLayout.X_AXIS);
        changesJPanel.setLayout(boxLayout);
        changesJPanel.setBorder(BorderFactory.createTitledBorder("zmiany w folderze"));

        newJPanel=new JPanel(new BorderLayout());
        newJPanel.setBorder(BorderFactory.createTitledBorder("nowe"));
        newJTextArea=new JTextArea();
        newJPanel.add(newJTextArea);

        modifiedJPanel = new JPanel(new BorderLayout());
        modifiedJPanel.setBorder(BorderFactory.createTitledBorder("edytowane"));
        modifiedJTextArea= new JTextArea();
        modifiedJPanel.add(modifiedJTextArea);

        removedJPanel= new JPanel(new BorderLayout());
        removedJPanel.setBorder(BorderFactory.createTitledBorder("usunięte"));
        removedJTextArea=new JTextArea();
        removedJPanel.add(removedJTextArea);

        changesJPanel.add(newJPanel);
        changesJPanel.add(modifiedJPanel);
        changesJPanel.add(removedJPanel);

        return changesJPanel;
    }

    private Box getTrackJPanel() {
        Box trackBox= Box.createHorizontalBox();
        trackBox.setBorder(BorderFactory.createTitledBorder("lokalizacja do śledzenia"));

        trackJLabel= new JLabel("folder");
        trackJTextField=new JTextField();
        trackJButton=new JButton("wybierz");

        trackBox.add(trackJLabel);
        trackBox.add(Box.createHorizontalStrut(10));
        trackBox.add(trackJTextField);
        trackBox.add(trackJButton);
//        jFileChooser=new JFileChooser();
//        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        trackJButton.addActionListener(e->{
//            int returnValue = jFileChooser.showOpenDialog(this);
//            if(returnValue==JFileChooser.APPROVE_OPTION){
//                File selectedFile = jFileChooser.getSelectedFile();
//            }
//
//        });
        return trackBox;
    }


}