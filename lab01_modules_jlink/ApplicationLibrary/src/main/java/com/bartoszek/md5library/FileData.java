package com.bartoszek.md5library;
import java.nio.file.Path;
import java.util.Objects;

public class FileData {

    private String hash;
    private String fileName;
    private Path path;

    public FileData(String hash, String fileName, Path path) {
        this.hash = hash;
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

    public Path getPath(){
        return path;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash,fileName,path);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj ==null) return false;
        if(this.getClass()!=obj.getClass())return false;
        FileData otherObject=(FileData) obj;

        boolean isSameFileData=
                hash.equals(otherObject.hash)
                && fileName.equals(otherObject.fileName)
                && path.equals(otherObject.path);

       return isSameFileData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("------- File Info ------");
        builder.append("\nfilename: " + fileName);
        builder.append("\nhash: " + hash);
        builder.append("\npath: " +path);
        builder.append("\n--------- End --------- ");
        return builder.toString();
    }



}