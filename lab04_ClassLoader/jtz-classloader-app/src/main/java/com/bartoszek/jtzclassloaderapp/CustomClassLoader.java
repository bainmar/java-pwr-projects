package com.bartoszek.jtzclassloaderapp;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {
    private Path searchPath;

    public CustomClassLoader(Path searchPath) {
        if (!Files.isDirectory(searchPath)) throw new IllegalArgumentException("Path must be a directory");
        this.searchPath = searchPath;
    }
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(searchPath);
        System.out.println(name);
        Path classfile = Paths.get(searchPath + FileSystems.getDefault().getSeparator()
                + name.replace(".", FileSystems.getDefault().getSeparator()) + ".class");
        System.out.println(classfile);
        byte[] buf;
        try {
            buf = Files.readAllBytes(classfile);
            System.out.println(buf.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Error in defining " + name + " in " + searchPath,e);
        }
        return defineClass(name, buf, 0, buf.length);
    }

}