package com.bartoszek.md5library;

public class PathContentPair {
    private String path;
    private String content;

    public PathContentPair(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
}