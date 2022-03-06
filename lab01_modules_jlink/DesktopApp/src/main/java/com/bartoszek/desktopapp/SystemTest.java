package com.bartoszek.desktopapp;

import javax.swing.*;

public class SystemTest {
    public static void main(String[] args) {
        Model model= new Model();
        SwingUtilities.invokeLater(()->{
            ModuleJlinkJFrame moduleJlinkJFrame = new ModuleJlinkJFrame("Modules && Jlink");
            Controller controller=new Controller(model,moduleJlinkJFrame);
            controller.initController();
            // display frame
            moduleJlinkJFrame.pack();
            moduleJlinkJFrame.setVisible(true);
        });




    }
}