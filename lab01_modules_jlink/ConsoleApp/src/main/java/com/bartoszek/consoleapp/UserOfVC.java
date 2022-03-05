package com.bartoszek.consoleapp;

import com.bartoszek.md5library.FileData;
import com.bartoszek.md5library.FileStatus;
import com.bartoszek.md5library.VersionController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public class UserOfVC {

    private VersionController versionController;

    public UserOfVC(Path path) throws IOException {
        this.versionController = new VersionController(path);
    }

    public void checkChanges() throws IOException {
        Map<FileStatus, List<FileData>> changesInRepo = versionController.getChangesInRepo();

        System.out.println(String.format("%30s%n","Changes").replace(" ", "*"));

        if (!changesInRepo.get(FileStatus.NEW).isEmpty()) {
            System.out.println(String.format("%-25s","New files").replace(" ","*"));
            changesInRepo.get(FileStatus.NEW).stream()
                    .forEach(System.out::println);
        }
        if (!changesInRepo.get(FileStatus.EDITED).isEmpty()) {
            System.out.println(String.format("%-25s","Edited").replace(" ","*"));
            changesInRepo.get(FileStatus.EDITED).stream()
                    .forEach(System.out::println);
        }
        if(!changesInRepo.get(FileStatus.REMOVED).isEmpty()) {
            System.out.println(String.format("%-25s","Removed").replace(" ","*"));
            changesInRepo.get(FileStatus.REMOVED).stream()
                    .forEach(System.out::println);
        }
        System.out.println(String.format("%30s","End of changes").replace(" ","*"));

    }

    public void showFilesInRepo() {

        System.out.println(String.format("%30s%n","Files in repo").replace(" ","*"));
        versionController.showFilesInRepo();
        System.out.println(String.format("%30s","End of files in repo").replace(" ","*"));

    }

    public void changePathToTrack(Path path) throws IOException {
        versionController.changePathToTrack(path);
        System.out.println("Ścieżka do śledzenia ");
    }

}