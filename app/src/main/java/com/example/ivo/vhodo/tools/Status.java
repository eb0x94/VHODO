package com.example.ivo.vhodo.tools;

/**
 * Created by Ivo on 11.10.2016 г..
 */
public class Status {

    private boolean isShallPass;
    private String Msg;

    public Status(String msg, boolean isShallPass) {
        this.Msg = msg;
        this.isShallPass = isShallPass;
    }

    public boolean isShallPass() {
        return isShallPass;
    }

    public String getMsg() {
        return Msg;
    }
}
