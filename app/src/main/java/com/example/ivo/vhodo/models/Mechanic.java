package com.example.ivo.vhodo.models;

/**
 * Created by Ivo on 13.10.2016 г..
 */
public class Mechanic {
    private String name;
    private String phone;
    private String type;



    public Mechanic(String name, String phone, String type) {

        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }
}
