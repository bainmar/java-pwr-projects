package com.bartoszek.server;

public interface QueueMBean {
    String getCategories();
    String getPriorities();
    boolean setPriorities(String priorities);
    boolean setCategories(String categories);
}
