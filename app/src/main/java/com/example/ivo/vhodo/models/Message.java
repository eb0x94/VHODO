package com.example.ivo.vhodo.models;

import android.graphics.Color;

import com.example.ivo.vhodo.R;

/**
 * Created by Ivo on 12.10.2016 г..
 */
public class Message {
    private String msgTxt;
    private int msgType;
    private String username;
    private String dateAndTime;


    public Message(String msgTxt, int msgType, String username, String dateAndTime) {

        this.msgTxt = msgTxt;
        this.msgType = msgType;
        this.username = username;
        this.dateAndTime = dateAndTime;
    }


    public String getMsgTxt() {
        return msgTxt;
    }

    public int getMsgType() {
        return msgType;
    }

    public String getUsername() {
        return username;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public int getMsgColor() {
        switch (this.msgType) {
            case 0:
                return R.drawable.custom_message_color;
            case 1:
                return R.drawable.custom_alert_color;
            case 2:
                return R.drawable.custom_problem_color;
        }
        return -1;
    }

}
