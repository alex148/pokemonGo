package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Race;
import com.pokemongo.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 04/01/2016.
 */
public class RaceDao extends Dao implements Crud<Race> {

    public RaceDao(Context context){
        super(context);
        this.open();
    }

    public static final String TABLE_NAME = "race";
    public static final String KEY = "ID_Race";
    public static final String NOM = "nom";
    public static final String DESCRIPTION = "description";
    public static final String POIDS = "poids";
    public static final String TAILLE= "taille";
    public static final String TYPE1= "type1";
    public static final String TYPE2= "type2";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NOM+" STRING NOT NULL,"
            +DESCRIPTION +" STRING NOT NULL,"
            +POIDS+" INTEGER NOT NULL,"
            +TAILLE+" INTEGER NOT NULL,"
            +TYPE1+" STRING NOT NULL,"
            +TYPE2+" STRING NOT NULL "
            + ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_RACE = "INSERT INTO "+TABLE_NAME+" VALUES (0,'Pikachu','Une souris qui fait des éclairs',14,90,'Electrik','Eau');";

    @Override
    public long insert(Race object) {
        try {
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNomRace());
            value.put(DESCRIPTION, object.getNomRace());
            value.put(POIDS, object.getNomRace());
            value.put(TAILLE, object.getNomRace());
            value.put(TYPE1,object.getType1().toString());
            value.put(TYPE2,object.getType2().toString());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Race object) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Race object) {
        try{
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNomRace());
            value.put(DESCRIPTION, object.getDescription());
            value.put(POIDS, object.getPoids());
            value.put(TAILLE, object.getTaille());
            value.put(TYPE1,object.getType1().toString());
            value.put(TYPE2,object.getType2().toString());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Race> getAll(){
        List<Race> ListRace=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                Race r = new Race(cursor.getLong(0),cursor.getString(1),cursor.getString(2),
                        cursor.getInt(3),cursor.getInt(4),Type.valueOf(cursor.getString(5)),Type.valueOf(cursor.getString(6)),false);
                ListRace.add(r);
            }while (cursor.moveToNext());
            return ListRace;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListRace;
    }
    public Race getRaceByID(int id){
        Race race=new Race();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID_Race="+id,null);
            cursor.moveToFirst();
            race = new Race(cursor.getLong(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getInt(4),Type.valueOf(cursor.getString(5)),Type.valueOf(cursor.getString(6)),false);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return race;
    }
}
