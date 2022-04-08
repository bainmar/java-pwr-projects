package com.bartoszek.client.server;

public class User {
    private static int counter=0;
    private String E;
    private String N;
    private String nickname;
    private int ID;

    public User(String e, String n, String nickname) {
        this.E = e;
        this.N = n;
        this.nickname = nickname;
        this.ID=counter++;
    }

    public String getE() {
        return E;
    }

    public String getN() {
        return N;
    }

    public String getNickname() {
        return nickname;
    }

    public int getID() {
        return ID;
    }
}
