package com.bartoszek.jtzweakreferences;

import java.io.IOException;
import java.nio.file.Path;

public class CacheController {

    private Cache cache = new Cache();
    public void saveFolder(Path folderPath) throws IOException {
        cache.saveFolder(folderPath,PersonalData.createEntry(folderPath));
    }
    public int numberOfElements() {
        return cache.numberOfElements();
    }
}