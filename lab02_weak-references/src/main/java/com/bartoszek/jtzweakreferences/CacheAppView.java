package com.bartoszek.jtzweakreferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CacheAppView extends JFrame {
    private JMenuItem authorJMenuItem;
    private JPanel folderToChooseJPanel;
    private Box personalDataBox;
    private JTextArea personalDatJTextArea;
    private JLabel personalDataJLabel;
    private JPanel cachedFilesJPanel;
    private JTextArea cachedFilesJTextArea;

    public CacheAppView(String title){
        super(title);
        initFrame();
    }

    public JLabel getPersonalDataJLabel() {
        return personalDataJLabel;
    }

    public JMenuItem getAuthorJMenuItem() {
        return authorJMenuItem;
    }

    public JTextArea getPersonalDatJTextArea() {
        return personalDatJTextArea;
    }

    public JTextArea getCachedFilesJTextArea() {
        return cachedFilesJTextArea;
    }

    public JPanel getFolderToChooseJPanel() {
        return folderToChooseJPanel;
    }

    private void initFrame() {
        //add menu
        JMenuBar jMenuBar = createJMenuBar();
        setJMenuBar(jMenuBar);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //left panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.35;
        gbc.weighty = 0.75;

        folderToChooseJPanel = getFolderToChoosePanel();
        getContentPane().add(folderToChooseJPanel,gbc);

        //right panel
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weightx = 0.65;
        personalDataBox = getPersonalDataBox();
        getContentPane().add(personalDataBox,gbc);

        //south panel
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0.25;
        cachedFilesJPanel = getCachedFilesPanel();
        getContentPane().add(cachedFilesJPanel,gbc);

        //frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600,500));
        setLocationRelativeTo(null);
    }

    private JPanel getCachedFilesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("cache folderów"));
        cachedFilesJTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(cachedFilesJTextArea
                ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(jScrollPane);
        return panel;
    }

    private Box getPersonalDataBox() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createTitledBorder("dane użytkownika"));
        personalDataJLabel = new JLabel();
        personalDatJTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(personalDatJTextArea
                ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        box.add(personalDataJLabel);
        box.add(Box.createVerticalStrut(10));
        box.add(jScrollPane);
        return box;
    }

    private JPanel getFolderToChoosePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("wybierz folder"));
        return panel;
    }

    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        //option
        JMenu infoJMenu = new JMenu("Info");
        infoJMenu.setMnemonic(KeyEvent.VK_I);
        jMenuBar.add(infoJMenu);

        authorJMenuItem = new JMenuItem("autor");
        infoJMenu.add(authorJMenuItem);
        return jMenuBar;
    }

    public static void main(String[] args) {
        CacheAppView lab2 = new CacheAppView("lab2");
        lab2.pack();
        lab2.setVisible(true);
    }

}