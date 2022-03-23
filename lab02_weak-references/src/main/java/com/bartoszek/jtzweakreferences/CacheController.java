package com.bartoszek.jtzweakreferences;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CacheController {

    private Cache cache = new Cache();
    private LinkedHashSet<Path> bufferedPaths = new LinkedHashSet<>();

    public void saveFolder(Path folderPath) throws IOException {

        bufferedPaths.add(folderPath);
        if(bufferedPaths.size()>3){
            bufferedPaths.remove(bufferedPaths.stream().findFirst().get());
        }
        cache.saveFolder(folderPath,PersonalData.createEntry(folderPath));

    }
    public int numberOfElements() {
        return cache.numberOfElements();
    }

    public boolean containsPersonalDataEntry(Path folderPath) {
        return cache.containsPersonalDataEntry(folderPath);
    }

    public Set<Path> getLastViewedPaths() {
        return bufferedPaths;
    }

    public List<String> getCachedPaths() {
        return cache.getCachedPaths();
    }
}