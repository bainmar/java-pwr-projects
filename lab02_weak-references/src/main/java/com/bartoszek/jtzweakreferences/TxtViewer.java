package com.bartoszek.jtzweakreferences;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TxtViewer extends JPanel implements Viewer {

    private StringBuilder builder;
    @Override
    public boolean load(Path resourcePath) {
        try {
            List<String> strings = Files.readAllLines(resourcePath);
            builder = new StringBuilder();
            for(String entry:strings){
                builder.append(entry);
                builder.append("\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void render() {
        JTextArea txtArea = new JTextArea();
        txtArea.append(builder.toString());
        add(txtArea);
        setPreferredSize(new Dimension(200,300));
    }
}