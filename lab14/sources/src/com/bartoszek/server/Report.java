package com.bartoszek.server;

public class Report {
    private String caseNumber;
    private Status status;

    public Report(String caseNumber, Status status) {
        this.caseNumber = caseNumber;
        this.status = status;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[sprawa: "+caseNumber+ " status: " +status.toString() + "]";
    }
}
