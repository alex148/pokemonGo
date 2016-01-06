package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Zone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 06/01/2016.
 */
public class ZoneDao extends Dao implements Crud<Zone> {

    public ZoneDao(Context context){
        super(context);
        this.open();
    }

    public static final String TABLE_NAME = "zone";
    public static final String KEY = "ID_Zone";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE ="longitude";
    public static final String RACE_KEY ="ID_Race";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LATITUDE+" FLOAT NOT NULL,"
            +LONGITUDE +" FLOAT NOT NULL,"
            +RACE_KEY+" INTEGER NOT NULL,"
            +"FOREIGN KEY("+RACE_KEY+") REFERENCES "+RaceDao.TABLE_NAME+"("+RaceDao.KEY+")"
            + ");";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_ZONE= "INSERT INTO "+TABLE_NAME+" VALUES (0,'45.783713','4.868944','0');";

    @Override
    public long insert(Zone object) {
        try {
            ContentValues value = new ContentValues();
            value.put(LATITUDE, object.getLatitude());
            value.put(LONGITUDE, object.getLongitude());
            value.put(RACE_KEY, object.getID_Race());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Zone object) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(object.getID_Zone())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Zone object) {
        try {
            ContentValues value = new ContentValues();
            value.put(LATITUDE, object.getLatitude());
            value.put(LONGITUDE, object.getLongitude());
            value.put(RACE_KEY, object.getID_Race());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getID_Zone())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Zone> getAll(){
        List<Zone> ListZone=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                Zone z = new Zone(cursor.getLong(0),cursor.getFloat(1),cursor.getFloat(2),cursor.getLong(0));
                ListZone.add(z);
            }while (cursor.moveToNext());
            return ListZone;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListZone;
    }

    public List<Zone> getZonesByPokemon(long id){
        List<Zone> ListZone=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID_Race="+id,null);
            cursor.moveToFirst();
            do{
                Zone z = new Zone(cursor.getLong(0),cursor.getFloat(1),cursor.getFloat(2),cursor.getLong(0));
                ListZone.add(z);
            }while (cursor.moveToNext());
            return ListZone;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListZone;
    }

}
