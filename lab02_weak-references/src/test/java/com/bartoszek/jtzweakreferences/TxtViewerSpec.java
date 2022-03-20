package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtViewerSpec {

    private Path personOneRecord = Paths.get("src/test/resources/personal-data/00000000001/record.txt");
    private Viewer txtViewer;
    @BeforeEach
    void init() {
        txtViewer = new TxtViewer();
    }
    @Test
    void itIsPossibleToLoadTextWithSpecifiedPathFormatToMemory(){
        txtViewer.load(personOneRecord);
    }

    @Disabled
    @Test
    void itIsPossibleToDisplayLoadedTxtOnJPanel() throws InterruptedException {
        txtViewer.load(personOneRecord);
        txtViewer.render();
        JFrame frame = new JFrame("Display text test");
        frame.getContentPane().add((Component) txtViewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);
    }
}