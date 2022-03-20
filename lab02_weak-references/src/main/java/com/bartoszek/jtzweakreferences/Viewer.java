package com.bartoszek.jtzweakreferences;

import java.nio.file.Path;

public interface Viewer {
    boolean load(Path resourcePath);
    void render();
  }