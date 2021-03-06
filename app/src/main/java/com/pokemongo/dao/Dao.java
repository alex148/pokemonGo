package com.pokemongo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alex on 18/12/2015.
 */
public class Dao{

    protected  final static int VERSION = 51;

    protected  final static String NOM ="pokemon.db";

    protected SQLiteDatabase database = null;

    protected DatabaseHandler handler = null;


    public Dao(Context pContext){
        this.handler = new DatabaseHandler(pContext,NOM,null,VERSION);
    }

    public Dao() {
    }

    public SQLiteDatabase open(){
        try{
            database = handler.getWritableDatabase();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return database;
    }

    public void close(){
        database.close();
    }

    public SQLiteDatabase getDb(){
        return database;
    }


}
