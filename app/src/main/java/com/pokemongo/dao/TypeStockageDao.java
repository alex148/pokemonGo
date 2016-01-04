package com.pokemongo.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.pokemongo.model.TypeStockage;

/**
 * Created by Alex on 04/01/2016.
 */
public class TypeStockageDao extends Dao implements Crud<TypeStockage> {

    public static final String TABLE_NAME = "typeStockage";
    public static final String KEY = "ID_TypeStockage";
    public static final String LIBELLE = "libelle";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            LIBELLE+" STRING NOT NULL);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    @Override
    public long insert(TypeStockage object) {
        try {
                ContentValues value = new ContentValues();
                value.put(LIBELLE, object.getLibelle());
                return database.insert(TABLE_NAME, null, value);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(TypeStockage type) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(type.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(TypeStockage object) {
        try{
            ContentValues value = new ContentValues();
            value.put(LIBELLE, object.getLibelle());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public TypeStockage get(TypeStockage type) {
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+KEY+" = ?", new String[]{String.valueOf(type.getId())});
            if(cursor.getCount() != 1)
                return null;
            cursor.moveToFirst();
            TypeStockage t = new TypeStockage(cursor.getLong(0),cursor.getString(1));
            return t;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
