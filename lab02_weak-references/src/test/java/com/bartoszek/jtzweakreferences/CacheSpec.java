package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheSpec {

    private Path folderOnePath = Paths.get("src/test/resources/personal-data/00000000001");
    private Path folderTwoPath = Paths.get("src/test/resources/personal-data/00000000002");
    private PersonalData personalDataEntry;
    private PersonalData personalDataEntry2;
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
    void cacheShouldRemoveOneEntryFromWeakHashMap() throws InterruptedException {
        cache.saveFolder(folderOnePath,personalDataEntry);
        assertTrue(cache.containsPersonalDataEntry(folderOnePath));
        folderOnePath=null;
        System.gc();
        await().atMost(10, TimeUnit.SECONDS).until(cache::isEmpty);
    }

    @Test
    void cacheShouldRemoveOneEntryFromTwoEntriesInWeakHashMap(){
        cache.saveFolder(folderOnePath,personalDataEntry);
        cache.saveFolder(folderTwoPath,personalDataEntry2);

        assertTrue(cache.containsPersonalDataEntry(folderOnePath));
        assertTrue(cache.containsPersonalDataEntry(folderTwoPath));
        folderOnePath = null;
        System.gc();
        await().atMost(10,TimeUnit.SECONDS)
                .until(()->cache.numberOfElements()==1);
        await().atMost(10,TimeUnit.SECONDS)
                .until(()->cache.containsPersonalDataEntry(folderTwoPath));
    }
}