package com.example.ivo.vhodo.models;

/**
 * Created by vilimir on 13.10.16.
 */
public class Neighbour {
    private String name;
    private String phone;
    private String email;

    public Neighbour(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
