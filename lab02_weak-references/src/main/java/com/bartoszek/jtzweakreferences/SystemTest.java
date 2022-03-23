package com.bartoszek.jtzweakreferences;

import javax.swing.*;

public class SystemTest {
    public static void main(String[] args) {
        Cache cache = new Cache();
        SwingUtilities.invokeLater(()->{
            CacheAppView cacheAppView = new CacheAppView("weak references");
            CacheController cacheController = new CacheController(cacheAppView,cache);
            cacheController.initController();
            cacheAppView.pack();
            cacheAppView.setVisible(true);
        });
    }
}