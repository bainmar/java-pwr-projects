package com.bartoszek.jtzweakreferences;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PngViewer extends JPanel implements Viewer {

    private BufferedImage image;
    @Override
    public boolean load(Path resourcePath) {
        try {
            image = ImageIO.read(Files.newInputStream(resourcePath));
            return true;
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void render() {
        setPreferredSize(new Dimension(200,200));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image resultingImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        g2d.drawImage(resultingImage, 0, 0, this);
    }
}