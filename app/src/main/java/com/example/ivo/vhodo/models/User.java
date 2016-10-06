package com.example.ivo.vhodo.models;

/**
 * Created by vilimir on 06.10.16.
 */
public class User {
    private int id;
    private String username;
    private String name;
    private String phone;
    private boolean isLandlord;

    public User(int id, String username, String name, String phone, boolean isLandlord) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.isLandlord = isLandlord;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isLandlord() {
        return isLandlord;
    }

}
