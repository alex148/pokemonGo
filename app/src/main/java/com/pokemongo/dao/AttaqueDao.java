package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Attaque;
import com.pokemongo.model.CategorieAttaque;
import com.pokemongo.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class AttaqueDao extends Dao implements Crud<Attaque> {


    public AttaqueDao(Context context){
        super(context);
    }

    public static final String TABLE_NAME = "attaque";
    public static final String KEY = "ID_Attaque";
    public static final String NOM= "nom";
    public static final String DEGAT= "degat";
    public static final String PRECISION= "precision";
    public static final String DESCRIPTION= "description";
    public static final String CATEGORIE= "categorie";
    public static final String TYPE= "type";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +KEY+"  INTEGER PRIMARY KEY AUTOINCREMENT , "
            +NOM+" STRING NOT NULL , "
            +DEGAT+" INTEGER NOT NULL,"
            +PRECISION+" INTEGER NOT NULL,"
            +DESCRIPTION+" STRING NOT NULL , "
            +CATEGORIE+" STRING NOT NULL , "
            +TYPE+" STRING NOT NULL"
            + ");";



    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    @Override
    public long insert(Attaque object) {
        try {
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNom());
            value.put(DEGAT, object.getDegats());
            value.put(PRECISION, object.getPrecision());
            value.put(DESCRIPTION, object.getDescription());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(TYPE, object.getType().toString());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Attaque object) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Attaque object) {
        try {
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNom());
            value.put(DEGAT, object.getDegats());
            value.put(PRECISION, object.getPrecision());
            value.put(DESCRIPTION, object.getDescription());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(TYPE, object.getType().toString());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Attaque> getAll() {
        List<Attaque> ListAttaque=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                Attaque p = new Attaque(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)
                        ,cursor.getString(4), CategorieAttaque.valueOf(cursor.getString(5)), Type.valueOf(cursor.getString(6)));
                ListAttaque.add(p);
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListAttaque;
    }
    public Attaque getAttaqueById(long id) {
        Attaque a=new Attaque();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+"WHERE ID_Attaque="+id,null);
            cursor.moveToFirst();
            a = new Attaque(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)
                    ,cursor.getString(4), CategorieAttaque.valueOf(cursor.getString(5)), Type.valueOf(cursor.getString(6)));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }
}
