package com.bartoszek.dto;

public class Client {
    private Integer ID;
    private String name;
    private String surname;
    private Integer clientNumber;

    public Client() {
    }

    public Client(String name, String surname, Integer clientNumber) {
        this.name = name;
        this.surname = surname;
        this.clientNumber = clientNumber;
    }

    public Client(Integer ID, String name, String surname, Integer clientNumber) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.clientNumber = clientNumber;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Integer clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", clientNumber=" + clientNumber +
                '}';
    }
}
