package com.bartoszek.desktopapp;

import com.bartoszek.md5library.VersionController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Model {
    private VersionController versionController;
    public void createVersionController(Path path) throws IOException {
        if(Files.exists(path)){
            versionController=new VersionController(path);
        }
    }

    public VersionController getVersionController() {
        return versionController;
    }
}