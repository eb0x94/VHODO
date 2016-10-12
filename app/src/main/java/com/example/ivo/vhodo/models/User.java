package com.example.ivo.vhodo.models;

/**
 * Created by vilimir on 06.10.16.
 */
public class User {
    private int id;
    private String username;
    private String name;
    private String phone;
    private String email;
    private boolean isLandlord;

    public User(int id, String username, String name, String phone, String email, boolean isLandlord) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() { return email; }

    public boolean isLandlord() {
        return isLandlord;
    }

}
