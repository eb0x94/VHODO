package com.example.ivo.vhodo;

import android.app.Application;
import android.util.Log;

import com.example.ivo.vhodo.models.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vilimir on 06.10.16.
 */
public class GlobalData extends Application {
    private static List<User> users;
    private static User currentUser;
    private static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(getApplicationContext());
        if (!doesDatabaseExist()) {
            dbHelper.onCreate(openOrCreateDatabase(DBHelper.DB_NAME, MODE_PRIVATE, null));
        }
        //Log.d("DB Created","Success");
    }

    private boolean doesDatabaseExist() {
        File dbFile = this.getApplicationContext().getDatabasePath(DBHelper.DB_NAME);
        return dbFile.exists();
    }

    //// TODO: 8.10.2016 г. Implement logic for set and get the current user
    public static User getCurrentUser() {
        return currentUser;
    }


    public static List<User> getUsers() {
        List<User> listToReturn = new ArrayList<>();
        for (User user : users) {
            listToReturn.add(user);
        }
        return listToReturn;
    }

    // methods for add data in DB
    public static void addUser(int id, String username, String password, String name, int type) {
        dbHelper.addNewUser(id, username, password, name, type);
    }

    public static void addMechanic(int id, String name, String phone, String type){
        dbHelper.addNewMechanic(id, name, phone, type);
    }

    public static void addNeighbour(int id, String phone, String email, String username){
        dbHelper.addNewNeighbour(id, phone, email, username);
    }

    public static void addProblem(int id, String user, String problemDescription, int problemState){
        dbHelper.addNewProblem(id, user, problemDescription, problemState);
    }


    // methods for removing data from DB
    public static void deleteUser(int id){
        dbHelper.deleteUser(id);
    }

    public static void deleteMechanicById(int id){
        dbHelper.deleteMechanic(id);
    }

    public static void deleteMechanicByName(String name){
        dbHelper.deleteMechanic(name);
    }

    public static void deleteNeighbourById(int id){
        dbHelper.deleteNeighbour(id);
    }

    public static void deleteNeighbourByName(String name){
        dbHelper.deleteNeighbour(name);
    }


    // methods for updating data in DB
    public static void updateUser(int id, String username, String password, String name, int userType){
        dbHelper.updateUser(id, username, password, name, userType);
    }

    public static void updateNeighbour(int id, String phone, String email, String username){
        dbHelper.updateNeigbour(id, phone, email, username);
    }

    public static void updateMechanic(int id, String name, String phone, String type){
        dbHelper.updateMechanic(id, name, phone, type);
    }

    public static void updateProblem(int id, String username, String problemDescription, int problemState){
        dbHelper.updateProblem(id, username, problemDescription, problemState);
    }


    //additional
    public static int getNumberOfRowsInTable(String table_name){
        return dbHelper.numberOfRows(table_name);
    }
    public static String getUserPass(String username){
        return dbHelper.getUserPass(username);
    }
 }
