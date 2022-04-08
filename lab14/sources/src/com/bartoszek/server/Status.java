package com.bartoszek.server;

public enum Status {
    NEW("nowa"),IN_PROGRESS("w trakcie"),EXECUTED("zrealizowana");

    private String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
