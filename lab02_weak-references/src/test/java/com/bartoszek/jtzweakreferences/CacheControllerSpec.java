package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CacheControllerSpec {

    private Path folderOnePath= Paths.get("src/test/resources/personal-data/00000000001");
    private PersonalData personalDataEntry;
    private Cache cache;

    @BeforeEach
    void init() throws IOException {
        personalDataEntry = PersonalData.createEntry(folderOnePath);
        cache=new Cache();
    }

}