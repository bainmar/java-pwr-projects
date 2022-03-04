package com.bartoszek.md5library;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class VersionControllerSpec {

    private VersionController versionController;
    private Path testFileOne = Paths.get("target/testDirectory/one.txt");
    private Path testFileTwo = Paths.get("target/testDirectory/two.txt");
    private Path pathToTrack = Paths.get("target/testDirectory");


    @BeforeEach
    void shouldCreateFolderStructure() {
        try {
            FileStructureCreator.createFileStructure();
            versionController = new VersionController(pathToTrack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void numberOfFilesInSnapshotShouldEqualFiveAfterVersionControllerIsCreated() throws IOException {
        int numberOfTrackedFiles = versionController.getNumberOfTrackedFiles();
        assertThat(numberOfTrackedFiles).isEqualTo(5);
    }

    @Test
    void numberOfFilesWithStatusNO_CHANGESEqualFiveIfThereWasNoChanges() throws IOException {
        Map<FileStatus, List<FileData>> changesInRepo = versionController.getChangesInRepo();
        int size = changesInRepo.get(FileStatus.NO_CHANGE).size();
        assertThat(size).isEqualTo(5);
    }

    @Test
    void numberOfFilesWithStatusEditedShouldEqualTwoIfTwoFilesWereEdited() throws IOException {
        Files.write(testFileOne,"file one edited".getBytes(StandardCharsets.UTF_8));
        Files.write(testFileTwo,"file two edited".getBytes(StandardCharsets.UTF_8));
        Map<FileStatus, List<FileData>> changesInRepo = versionController.getChangesInRepo();
        int size = changesInRepo.get(FileStatus.EDITED).size();
        assertThat(size).isEqualTo(2);
    }

    @Test
    void numberOfFilesWithStatusNEWShouldEqualOneIfOneFileIsAddedToRepo() throws IOException {
        Path path = Paths.get("target/testDirectory/newFile.txt");
        String sampleText = "sample text";
        Files.write(path,sampleText.getBytes(StandardCharsets.UTF_8));
        Map<FileStatus, List<FileData>> changesInRepo = versionController.getChangesInRepo();
        int size = changesInRepo.get(FileStatus.NEW).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    void numberOfRemovedFilesAfterRemovingTwoFileFromFileStructureEqualTwo() throws IOException {
        Files.delete(testFileOne);
        Files.delete(testFileTwo);
        Map<FileStatus, List<FileData>> changes= versionController.getChangesInRepo();
        int size = changes.get(FileStatus.REMOVED).size();
        assertThat(size).isEqualTo(2);
    }


    @AfterEach
    void shouldDeleteFolderStructure() {
        try {
            FileStructureCreator.deleteFileStructure();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}