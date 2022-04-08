package com.bartoszek.dto;
public class PriceList {
    private Integer ID;
    private String priceType;
    private String serviceName;
    private Client clientID;

    public PriceList() {
    }

    public PriceList(String type, String serviceName, Client clientID) {
        this.priceType = type;
        this.serviceName = serviceName;
        this.clientID = clientID;
    }
    public PriceList(Integer ID, String type, String serviceName, Client clientID) {
        this.ID = ID;
        this.priceType = type;
        this.serviceName = serviceName;
        this.clientID = clientID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getType() {
        return priceType;
    }

    public void setType(String type) {
        this.priceType = type;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Client getClientID() {
        return clientID;
    }

    public void setClientID(Client clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "ID=" + ID +
                ", priceType='" + priceType + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", clientID=" + clientID +
                '}';
    }
}
