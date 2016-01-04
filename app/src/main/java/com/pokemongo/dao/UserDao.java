package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.User;

import java.util.List;

/**
 * Created by Alex on 18/12/2015.
 */
public class UserDao extends Dao implements Crud<User> {

    public UserDao(Context context){
        super(context);
    }

    public static final String TABLE_NAME = "user";
    public static final String KEY = "ID_User";
    public static final String LOGIN = "login";
    public static final String PASSWORD ="password";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            LOGIN+" STRING NOT NULL, "+
            PASSWORD+" STRING NOT NULL);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";


    @Override
    public long insert(User object) {
        try {
            if(!userExist(object.getLogin())) {
                ContentValues value = new ContentValues();
                value.put(LOGIN, object.getLogin());
                value.put(PASSWORD, object.getPassword());
                return database.insert(TABLE_NAME, null, value);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(User user) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(user.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User object) {
        try{
            ContentValues value = new ContentValues();
            value.put(LOGIN, object.getLogin());
            value.put(PASSWORD, object.getPassword());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public User get(User user) {
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+KEY+" = ?", new String[]{String.valueOf(user.getId())});
            if(cursor.getCount() != 1)
                return null;
            cursor.moveToFirst();
            User u = new User(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
            return u;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User getUser(String login, String password){
        User u;
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ LOGIN +" = ? AND "+PASSWORD+" = ?",new String[]{login,password});
        if(cursor.getCount() != 1)
            return null;
        cursor.moveToFirst();
        u = new User(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
        return u;
    }

    public boolean userExist(String login){
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ LOGIN +" = ?",new String[]{login});
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

}
