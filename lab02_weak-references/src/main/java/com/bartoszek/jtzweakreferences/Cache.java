package com.bartoszek.jtzweakreferences;

import java.nio.file.Path;
import java.util.WeakHashMap;

public class Cache {

    private WeakHashMap<Path,PersonalData> cacheEntries = new WeakHashMap<>();
    public void saveFolder(Path folderPath, PersonalData entry) {
        cacheEntries.put(folderPath,entry);
    }
    public int numberOfElements() {
        return cacheEntries.size();
    }

    public void clearCache() {
        cacheEntries.clear();
    }
}