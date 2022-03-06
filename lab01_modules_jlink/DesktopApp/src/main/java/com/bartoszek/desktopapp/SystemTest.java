package com.bartoszek.desktopapp;

import javax.swing.*;

public class SystemTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            ModuleJlinkJFrame moduleJlinkJFrame = new ModuleJlinkJFrame("Modules && Jlink");
            moduleJlinkJFrame.pack();
            moduleJlinkJFrame.setVisible(true);
        });
    }
}