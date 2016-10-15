package com.example.ivo.vhodo.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;

import com.example.ivo.vhodo.GlobalData;
import com.example.ivo.vhodo.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vilimir on 28.09.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    //column numbers for different tables
    

    //user types
    public static final int USER_NORMAL = 0;
    public static final int USER_LANDLORD = 1;

    //problem fixed state
    public static final int PROBLEM_FIXED = 0;
    public static final int PROBLEM_IN_PROCESS = 1;
    public static final int PROBLEM_NOT_FIXED = 2;
    public static final int PROBLEM_UNFIXABLE = 3;

    //database name
    public static final String DB_NAME = "vhodo_db";

    //database tables
    public static final String MECHANIC_CONTACTS_TABLE_NAME = "mechanics";
    public static final String NEIGHBOURS_CONTACTS_TABLE_NAME = "neighbours";
    public static final String MESSAGES_BOARD_TABLE_NAME = "msgboard";
    public static final String USERS_TABLE_NAME = "users";
    public static final String PROBLEMS_TABLE_NAME = "problems";

    //database columns
    public static final String ID_COLUMN_NAME = "id";
    public static final String PASS_COLUMN_NAME = "pass";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String PHONE_COLUMN_NAME = "phone";
    public static final String PROBLEM_COLUMN_NAME = "problem";
    public static final String USERNAME_COLUMN_NAME = "username";
    public static final String MESSAGE_COLUMN_NAME = "message";
    public static final String ISFIXED_COLUMN_NAME = "isfixed";
    public static final String TYPE_COLUMN_NAME = "type";
    public static final String DATETIME_COLUMN_NAME = "dt";

    //Constructor
    public DBHelper (Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (GlobalData.getDBExists()) return;
        db.execSQL(String.format("create table %s (%s integer primary key,%s text,%s text,%s text,",
                NEIGHBOURS_CONTACTS_TABLE_NAME,
                ID_COLUMN_NAME,PHONE_COLUMN_NAME,EMAIL_COLUMN_NAME,USERNAME_COLUMN_NAME) +
                String.format("foreign key(%s) references %s(%s))",
                        USERNAME_COLUMN_NAME,USERS_TABLE_NAME,USERNAME_COLUMN_NAME)
        );
        db.execSQL(String.format("create table %s (%s integer primary key,%s text, %s text,%s text,%s integer)",
                USERS_TABLE_NAME,
                ID_COLUMN_NAME,USERNAME_COLUMN_NAME,PASS_COLUMN_NAME,NAME_COLUMN_NAME,TYPE_COLUMN_NAME));
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text,%s text)",
                MECHANIC_CONTACTS_TABLE_NAME,
                ID_COLUMN_NAME,NAME_COLUMN_NAME,PHONE_COLUMN_NAME, TYPE_COLUMN_NAME));
        //TODO need to find a way to get to problem images so it's not going to have images for now.
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text,%s integer,",
                PROBLEMS_TABLE_NAME,
                ID_COLUMN_NAME,USERNAME_COLUMN_NAME,PROBLEM_COLUMN_NAME,ISFIXED_COLUMN_NAME) +
                        String.format("foreign key(%s) references %s(%s))",
                                USERNAME_COLUMN_NAME,USERS_TABLE_NAME,USERNAME_COLUMN_NAME));
        db.execSQL(String.format("create table %s (%s integer primary key, %s text,%s text, %s integer, %s text,",
                MESSAGES_BOARD_TABLE_NAME,
                ID_COLUMN_NAME,USERNAME_COLUMN_NAME,MESSAGE_COLUMN_NAME,TYPE_COLUMN_NAME,DATETIME_COLUMN_NAME)+
                String.format("foreign key(%s) references %s(%s))",
                        USERNAME_COLUMN_NAME,USERS_TABLE_NAME,USERNAME_COLUMN_NAME));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //// TODO: 8.10.2016 г. Implemet logic for DB Upgrading
    }

    //User table methods
    public boolean addNewUser(int id,String username, String pass,String name,int type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID_COLUMN_NAME,id);
        cv.put(USERNAME_COLUMN_NAME,username);
        cv.put(PASS_COLUMN_NAME,pass);
        cv.put(NAME_COLUMN_NAME,name);
        cv.put(TYPE_COLUMN_NAME,type);
        db.insert(USERS_TABLE_NAME,null,cv);
        return true;
    }

    public boolean updateUser(int id, String username,String pass,String name, int type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME_COLUMN_NAME,username);
        cv.put(PASS_COLUMN_NAME,pass);
        cv.put(NAME_COLUMN_NAME,name);
        cv.put(TYPE_COLUMN_NAME,type);
        db.update(USERS_TABLE_NAME,cv,ID_COLUMN_NAME + " = ?",new String[]{Integer.toString(id)});
        return true;
    }
    public boolean deleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(USERS_TABLE_NAME,ID_COLUMN_NAME + " = ?",new String[]{Integer.toString(id)});
        return true;
    }

    //Neighbour table methods
    public boolean addNewNeighbour(int id, String phone, String email, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID_COLUMN_NAME,id);
        cv.put(PHONE_COLUMN_NAME,phone);
        cv.put(EMAIL_COLUMN_NAME,email);
        cv.put(USERNAME_COLUMN_NAME,username);
        db.insert(NEIGHBOURS_CONTACTS_TABLE_NAME,null,cv);
        return true;
    }

    public boolean updateNeigbour(int id, String phone, String email, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PHONE_COLUMN_NAME,phone);
        cv.put(EMAIL_COLUMN_NAME,email);
        cv.put(USERNAME_COLUMN_NAME,username);
        db.update(NEIGHBOURS_CONTACTS_TABLE_NAME,cv,ID_COLUMN_NAME + " = ?",new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteNeighbour(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NEIGHBOURS_CONTACTS_TABLE_NAME,ID_COLUMN_NAME + " = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteNeighbour(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NEIGHBOURS_CONTACTS_TABLE_NAME,USERNAME_COLUMN_NAME + " ?",new String[]{username});
        return true;
    }

    //Problem table methods
    public boolean addNewProblem(int id, String user, String problem, int state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID_COLUMN_NAME,id);
        cv.put(USERNAME_COLUMN_NAME,user);
        cv.put(PROBLEM_COLUMN_NAME,problem);
        cv.put(ISFIXED_COLUMN_NAME,state);
        db.insert(PROBLEMS_TABLE_NAME,null,cv);
            return true;
    }

    public boolean updateProblem(int id, String user, String problem, int state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME_COLUMN_NAME,user);
        cv.put(PROBLEM_COLUMN_NAME,problem);
        cv.put(ISFIXED_COLUMN_NAME,state);
        db.update(PROBLEMS_TABLE_NAME,cv,ID_COLUMN_NAME + " = ?", new String[]{Integer.toString(id)});
        return true;
    }


    //Mechanics table methods
    public boolean addNewMechanic(int id, String name, String phone, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(ID_COLUMN_NAME,id);
        cv.put(NAME_COLUMN_NAME,name);
        cv.put(PHONE_COLUMN_NAME,phone);
        cv.put(TYPE_COLUMN_NAME,type);
        db.insert(MECHANIC_CONTACTS_TABLE_NAME,null,cv);
        return true;
    }

    public boolean updateMechanic(int id, String name, String phone, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN_NAME,name);
        cv.put(PHONE_COLUMN_NAME,phone);
        cv.put(TYPE_COLUMN_NAME,type);
        db.update(MECHANIC_CONTACTS_TABLE_NAME,cv,ID_COLUMN_NAME + " = ?" ,new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteMechanic(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MECHANIC_CONTACTS_TABLE_NAME,ID_COLUMN_NAME + " = ?" , new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteMechanic(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MECHANIC_CONTACTS_TABLE_NAME,ID_COLUMN_NAME + " = ?", new String[]{name});
        return true;
    }

    //additional public methods
    public Cursor getDataWithId(String table,int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( String.format("select * from %s where id = %d",table,id), null );
        return res;
    }

    public String getUserPass(String username){
        Cursor res = getCursorFromUsersWithUsername(username);
        res.moveToFirst();
        String pass = res.getString(2);
        return pass;
    }

    private Cursor getCursorFromUsersWithUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery
                (String.format("select * from %s where %s = \'%s\'"
                        ,USERS_TABLE_NAME,USERNAME_COLUMN_NAME,username)
                        ,null);
    }

    public boolean userExists(String username){
        Cursor res = getCursorFromUsersWithUsername(username);
        res.moveToFirst();
        try{
        res.isNull(1);
        return true;
        }
        catch (CursorIndexOutOfBoundsException e){
            return false;
        }
    }

    public int numberOfRows(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        return numRows;
    }

    //messages
    public void addMessage(int id, String user, String message, String datetime, int type ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(ID_COLUMN_NAME,id);
        cv.put(MESSAGE_COLUMN_NAME,message);
        cv.put(DATETIME_COLUMN_NAME, String.valueOf(datetime));
        cv.put(USERNAME_COLUMN_NAME,user);
        cv.put(TYPE_COLUMN_NAME,type);
        db.insert(MECHANIC_CONTACTS_TABLE_NAME,null,cv);
    }

     public List<Message> getAllMessages(){
         List<Message> msgs = new ArrayList<>();
         SQLiteDatabase db = getReadableDatabase();
         Cursor res =  db.rawQuery("select * from " + MESSAGES_BOARD_TABLE_NAME , null);
         if (res.getCount() == 0){
             return new ArrayList<>();
         }
         res.moveToLast();
         while(res.isFirst()){
             collectDataFromMessageTable(msgs, res);
             res.moveToPrevious();
         }
         collectDataFromMessageTable(msgs,res);
         return msgs;
     }

    private void collectDataFromMessageTable(List<Message> msgs, Cursor res) {
        String username = res.getString(1);
        String message = res.getString(2);
        int type = res.getInt(3);
        String datetime =res.getString(4);
        msgs.add(new Message(message,type,username,datetime));
    }

    //private methods

}