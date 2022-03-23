package com.bartoszek.jtzweakreferences;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class Cache {

    private WeakHashMap<Path,PersonalData> cacheEntries = new WeakHashMap<>();
    public void saveFolder(Path folderPath, PersonalData entry) {
        cacheEntries.putIfAbsent(folderPath,entry);
    }
    public int numberOfElements() {
        return cacheEntries.size();
    }

    public void clearCache() {
        cacheEntries.clear();
    }

    public boolean containsPersonalDataEntry(Path folderPath) {
        return cacheEntries.containsKey(folderPath);
    }

    public boolean isEmpty() {
        return cacheEntries.isEmpty();
    }

    public List<String> getCachedPaths() {
        return cacheEntries.keySet().stream().map(Path::toString).collect(Collectors.toList());
    }

    public PersonalData getEntry(Path folderPath) {
        return cacheEntries.get(folderPath);
    }
}