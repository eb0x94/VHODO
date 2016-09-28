package com.example.ivo.vhodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vilimir on 28.09.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "vhodo_db";
    public static final String MECHANIC_CONTACTS_TABLE_NAME = "mechanics";
    public static final String NEIGHBOURS_CONTACTS_TABLE_NAME = "neighbours";
    public static final String MESSAGES_BOARD_TABLE_NAME = "msgboard";
    public static final String USERS_TABLE_NAME = "users";
    public static final String PROBLEMS_TABLE_NAME = "problems";
    public static final String ID_COLUMN_NAME = "id";
    public static final String PASS_COLUMN_NAME = "pass";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String PHONE_COLUMN_NAME = "phone";
    public static final String PROBLEM_COLUMN_NAME = "problem";
    public static final String USERNAME_COLUMN_NAME = "username";
    public static final String MESSAGE_COLUMN_NAME = "message";
    public DBHelper (Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text,%s text,%s text)",
                NEIGHBOURS_CONTACTS_TABLE_NAME,
                ID_COLUMN_NAME,NAME_COLUMN_NAME,PHONE_COLUMN_NAME,EMAIL_COLUMN_NAME,USERS_TABLE_NAME));
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text)",
                USERS_TABLE_NAME,
                ID_COLUMN_NAME,USERNAME_COLUMN_NAME,PASS_COLUMN_NAME));
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text)",
                MECHANIC_CONTACTS_TABLE_NAME,
                ID_COLUMN_NAME,NAME_COLUMN_NAME,PHONE_COLUMN_NAME));
        //TODO need to find a way to get to problem images so it's not going to have images for now.
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text)",
                PROBLEMS_TABLE_NAME,
                ID_COLUMN_NAME,NAME_COLUMN_NAME,PROBLEM_COLUMN_NAME));
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text)",
                MESSAGES_BOARD_TABLE_NAME,
                ID_COLUMN_NAME,USERNAME_COLUMN_NAME,MESSAGE_COLUMN_NAME));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addNewUser(int id,String name, String pass){
        // TODO: 28.09.16
        return true;
    }

    public boolean addNewNeighbour(int id, String name, String phone, String email, String username){
        // TODO: 28.09.16
        return true;
    }

    public boolean addNewProblem(int id, String user, String message){
        // TODO: 28.09.16
        return true;
    }
    public boolean addNewMechanic(int id, String name, String phone){
        // TODO: 28.09.16
        return true;
    }
}
