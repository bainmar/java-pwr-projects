package com.bartoszek.md5library;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class VersionController {
    private Path path;
    private Set<FileData> lastSnapshot;

    public VersionController(Path path) throws IOException {
        this.path = path;
        lastSnapshot = makeSnapshot();
    }

    public void changePathToTrack(Path path) throws IOException {
        this.path = path;
        lastSnapshot = makeSnapshot();
    }

    public int getNumberOfTrackedFiles() {
        return lastSnapshot.size();
    }

    public void showFilesInRepo() {
        lastSnapshot.forEach(System.out::println);
    }

    private Set<FileData> makeSnapshot() throws IOException {
        Set<FileData> setOfFileData = new HashSet<>();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    FileData fileData = new FileData(Utils.createMD5Hash(file), file.getFileName().toString(), file);
                    setOfFileData.add(fileData);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return setOfFileData;
    }

    public Map<FileStatus, List<FileData>> getChangesInRepo() throws IOException {

        Set<FileData> currentSnapshot = makeSnapshot();
        Map<FileStatus, List<FileData>> listOfChanges = new HashMap<>();
        listOfChanges.put(FileStatus.NO_CHANGE, new ArrayList<>());
        listOfChanges.put(FileStatus.NEW, new ArrayList<>());
        listOfChanges.put(FileStatus.EDITED, new ArrayList<>());
        listOfChanges.put(FileStatus.REMOVED, new ArrayList<>());

        //set file status (new, no_change,edited
        for (FileData fileInCurrentSnap : currentSnapshot) {
            if (lastSnapshot.contains(fileInCurrentSnap)) {
                listOfChanges.get(FileStatus.NO_CHANGE).add(fileInCurrentSnap);
            } else {
                boolean fileEdited = lastSnapshot.stream()
                        .map(FileData::getPath).anyMatch(p -> p.equals(fileInCurrentSnap.getPath()));

                if (fileEdited) {
                    listOfChanges.get(FileStatus.EDITED).add(fileInCurrentSnap);
                } else {
                    listOfChanges.get(FileStatus.NEW).add(fileInCurrentSnap);
                }
            }
        }

        //set file status removed
        Set<FileData> removedFiles = new HashSet<>(lastSnapshot);
        Set<FileData> collectSet = listOfChanges.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        removedFiles.removeAll(collectSet);
        removedFiles.forEach(fileData -> {
                    Set<Path> pathsInCurrentSnap = collectSet.stream().map(FileData::getPath)
                            .collect(Collectors.toSet());
                    if (!pathsInCurrentSnap.contains(fileData.getPath())) {
                        listOfChanges.get(FileStatus.REMOVED).add(fileData);
                    }
                }
        );

        //updated state of version controller
        lastSnapshot = currentSnapshot;

        return listOfChanges;
    }
}