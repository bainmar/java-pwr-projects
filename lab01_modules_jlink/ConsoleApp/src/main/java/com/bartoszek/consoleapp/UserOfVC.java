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

        System.out.println("-------- Changes --------");

        if (!changesInRepo.get(FileStatus.NEW).isEmpty()) {
            System.out.println("--- new files ---");
            changesInRepo.get(FileStatus.NEW).stream()
                    .forEach(System.out::println);
        }
        if (!changesInRepo.get(FileStatus.EDITED).isEmpty()) {
            System.out.println("--- edited ---");
            changesInRepo.get(FileStatus.EDITED).stream()
                    .forEach(System.out::println);
        }

        if(!changesInRepo.get(FileStatus.REMOVED).isEmpty()) {
            System.out.println("--- removed ---");
            changesInRepo.get(FileStatus.REMOVED).stream()
                    .forEach(System.out::println);
        }
        System.out.println("------ End of changes ------");

    }

    public void showFilesInRepo() {

        System.out.println("-------- Files in Repo --------");
        versionController.showFilesInRepo();
        System.out.println("------ End of Files in Repo ------");

    }

    public void changePathToTrack(Path path) throws IOException {
        versionController.changePathToTrack(path);
        System.out.println("Ścieżka do śledzenia ");
    }

}