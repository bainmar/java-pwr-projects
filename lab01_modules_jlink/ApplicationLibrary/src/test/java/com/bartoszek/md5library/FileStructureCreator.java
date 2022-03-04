package com.bartoszek.md5library;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
public class FileStructureCreator {

    private static ArrayList<PathContentPair> fileStructure;
    private static Path rootPath;

    static{
        fileStructure = new ArrayList<>();
        rootPath=Paths.get("target/testDirectory");
        fileStructure.add(new PathContentPair("one.txt", "file number one"));
        fileStructure.add(new PathContentPair("two.txt", "file number two"));
        fileStructure.add(new PathContentPair("level1/three.txt", "file number three"));
        fileStructure.add(new PathContentPair("level1/level2/five.txt", "file number five"));
        fileStructure.add(new PathContentPair("level1/level2/six.txt", "file number six"));
    }

    public static void createFileStructure() throws IOException {
        fileStructure.stream().forEach(FileStructureCreator::addFile);
    }

    private static void addFile(PathContentPair pathContentPair) {
        try {
            Path pathComponent = Paths.get(pathContentPair.getPath());
            pathComponent=rootPath.resolve(pathComponent);
            if(pathComponent.getParent() != null){
                Files.createDirectories(pathComponent.getParent());
            }
            String content = pathContentPair.getContent();
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            Files.write(pathComponent,bytes);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void deleteFileStructure() throws IOException {
        Files.walkFileTree(rootPath,getFileVisitor());
    }
    public static FileVisitor<Path> getFileVisitor() {
           class dirVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        }
        return new dirVisitor();
    }
}