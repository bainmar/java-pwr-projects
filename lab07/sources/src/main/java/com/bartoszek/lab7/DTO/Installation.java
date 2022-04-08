package com.bartoszek.lab7.DTO;

public class Installation {
    private Integer ID;
    private String address;
    private Integer routerNumber;
    private String serviceType;
    private Client clientID;

    public Installation() {
    }

    public Installation(String address, Integer routerNumber, String serviceType, Client clientID) {
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
        this.clientID = clientID;
    }

    public Installation(Integer ID, String address, Integer routerNumber, String serviceType, Client clientID) {
        this.ID = ID;
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
        this.clientID = clientID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(Integer routerNumber) {
        this.routerNumber = routerNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Client getClientID() {
        return clientID;
    }

    public void setClientID(Client clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "ID=" + ID +
                ", address='" + address + '\'' +
                ", routerNumber=" + routerNumber +
                ", serviceType='" + serviceType + '\'' +
                ", clientID=" + clientID +
                '}';
    }
}
