package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class CacheControllerSpec {

    private Path folderOnePath = Paths.get("src/test/resources/personal-data/00000000001");
    private Path folderTwoPath = Paths.get("src/test/resources/personal-data/00000000002");
    private Path folderThreePath = Paths.get("src/test/resources/personal-data/00000000003");
    private Path folderFourPath = Paths.get("src/test/resources/personal-data/00000000004");
    private Path folderFifePath = Paths.get("src/test/resources/personal-data/00000000005");
    private Path folderSixPath = Paths.get("src/test/resources/personal-data/00000000006");
    private Path folderSevenPath = Paths.get("src/test/resources/personal-data/00000000007");

    private CacheController cacheController;

    @BeforeEach
    void init() throws IOException {
        cacheController= new CacheController();
        cacheController.saveFolder(folderOnePath);
    }
    @Test
    void ifCacheContainsPersonalDataEntryCacheControllerShouldReturnTrue(){
        boolean contains = cacheController.containsPersonalDataEntry(folderOnePath);
        assertThat(contains).isEqualTo(true);
    }

    @Test
    void ifCacheContainsMoreThanThreeElementsLastAddedEntryShouldBeEligableForGC() throws IOException {

        cacheController.saveFolder(folderOnePath);
        cacheController.saveFolder(folderTwoPath);
        cacheController.saveFolder(folderThreePath);
        cacheController.saveFolder(folderFourPath);

        Set<Path> folderToCache = new HashSet<>();
        folderToCache.add(folderOnePath);
        folderToCache.add(folderTwoPath);
        folderToCache.add(folderThreePath);
        folderToCache.add(folderFourPath);

        Set<Path> lastViewedPaths = cacheController.getLastViewedPaths();
        folderToCache.removeAll(lastViewedPaths);

        folderToCache.forEach(System.out::println);

        //do celów testowych
        folderToCache.clear();
        folderOnePath=null;
        System.gc();
        await().atMost(10, TimeUnit.SECONDS)
                .until(()-> cacheController.numberOfElements()==3);
    }

    @Test
    void cacheControllerShouldDisplayCachedPaths() throws IOException {

        cacheController.saveFolder(folderOnePath);
        cacheController.saveFolder(folderTwoPath);
        List<String> cachedPaths = cacheController.getCachedPaths();
        cachedPaths.stream().forEach(System.out::println);
    }






}