package com.example.harjoitustyo;

import java.util.ArrayList;

public class User {
    private String name;
    private String passcode;
    private String email;
    private ArrayList<String> favouriteLakes = new ArrayList<>();

    public User(){

    }

    public void addLakeToFavorite (String lake) {
        favouriteLakes.add(lake);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
