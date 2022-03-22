package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class CacheSpec {

    private Path folderOnePath= Paths.get("src/test/resources/personal-data/00000000001");
    private PersonalData personalDataEntry;
    private Cache cache;

    @BeforeEach
    void init() throws IOException {
        personalDataEntry = PersonalData.createEntry(folderOnePath);
        cache=new Cache();
    }

    @Test
    void cacheShouldContainOneEntryAfterReadPathWithPersonalData() throws IOException {
        cache.saveFolder(folderOnePath,personalDataEntry);
        int size = cache.numberOfElements();
        assertThat(size).isEqualTo(1);
    }

    @Test
    void cacheShouldRemoveOneEntryAfterFewLoopOfInvokingGarbageCollector() throws InterruptedException {
        cache.saveFolder(folderOnePath,personalDataEntry);
        folderOnePath=null;
        //personalDataEntry=null;
        cache.clearCache();
        int size = cache.numberOfElements();
        assertThat(size).isEqualTo(0);
    }
}