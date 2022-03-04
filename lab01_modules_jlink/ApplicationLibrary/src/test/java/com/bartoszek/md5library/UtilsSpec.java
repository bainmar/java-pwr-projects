package com.bartoszek.md5library;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsSpec {

    private Path pathOfFileOneTxt = Paths.get("target/testDirectory/one.txt");
    private Path pathOfFileTwoTxt = Paths.get("target/testDirectory/two.txt");

    @BeforeAll
    static void shouldCreateFolderStructure() {
        try {
            FileStructureCreator.createFileStructure();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void comparingSnapshotOfTheSameFileShouldReturnTrue() throws NoSuchAlgorithmException, IOException {

        String md5Hash = Utils.createMD5Hash(pathOfFileOneTxt);
        String md5HashTwo = Utils.createMD5Hash(pathOfFileOneTxt);
        assertEquals(md5Hash, md5HashTwo);

    }

    @Test
    void comparingSnapshotOfDifferentFilesShouldReturnFalse() throws NoSuchAlgorithmException, IOException {

        String md5HashOne = Utils.createMD5Hash(pathOfFileOneTxt);
        String md5HashTwo = Utils.createMD5Hash(pathOfFileTwoTxt);
        assertNotEquals(md5HashOne, md5HashTwo);

    }


    @AfterAll
    static void shouldDeleteFolderStructure() throws IOException {

        FileStructureCreator.deleteFileStructure();

    }
}