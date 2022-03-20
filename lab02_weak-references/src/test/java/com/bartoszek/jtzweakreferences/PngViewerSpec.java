package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PngViewerSpec {
    private Path personOneImage = Paths.get("src/test/resources/personal-data/00000000001/image.png");
    private Viewer pngViewer;

    @BeforeEach
    void init() {
        pngViewer = new PngViewer();
    }

    @Test
    void itIsPossibleToLoadImageWithSpecifiedPathFormatToMemory(){
        pngViewer.load(personOneImage);
    }

    @Disabled
    @Test
    void itIsPossibleToDisplayLoadedImageOnJPanel() throws InterruptedException {
        pngViewer.load(personOneImage);
        pngViewer.render();
        JFrame frame = new JFrame("Display image test");
        frame.getContentPane().add((Component) pngViewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);
    }

}