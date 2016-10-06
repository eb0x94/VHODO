package com.example.ivo.vhodo;

import android.app.Application;

import com.example.ivo.vhodo.models.User;

import java.util.List;

/**
 * Created by vilimir on 06.10.16.
 */
public class GlobalData extends Application {
    private List<User> users;
    private User currentUser;

}
