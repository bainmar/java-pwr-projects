package com.bartoszek.jtzweakreferences;

import java.awt.*;

public class PersonDataViewer implements Viewer {
    @Override
    public Font getFont() {
        return new Font("Serif", Font.ITALIC, 20);
    }

    @Override
    public Dimension getImageSize() {
        return new Dimension(200, 200);
    }
}