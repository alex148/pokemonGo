package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Attaque;
import com.pokemongo.model.AttaqueLiaison;
import com.pokemongo.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class AttaquePokemonDao extends Dao implements Crud<AttaqueLiaison> {

    private AttaqueDao attaqueDao;
    public AttaquePokemonDao(Context context){
        super(context);
        this.open();
        attaqueDao=new AttaqueDao(context);
    }

    public static final String TABLE_NAME = "attaquePokemon";
    public static final String ATTAQUE_KEY = "ID_Attaque";
    public static final String POKEMON_KEY = "ID_Pokemon";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +ATTAQUE_KEY+" INTEGER NOT NULL , "
            +POKEMON_KEY+" INTEGER NOT NULL , "
            +"PRIMARY KEY("+ATTAQUE_KEY+","+POKEMON_KEY+"),"
            +"FOREIGN KEY("+ATTAQUE_KEY+") REFERENCES "+ AttaqueDao.TABLE_NAME+"("+AttaqueDao.KEY+"),"
            +"FOREIGN KEY("+POKEMON_KEY+") REFERENCES "+ PokemonDao.TABLE_NAME+"("+PokemonDao.KEY+")"
            + ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    public static final String INSERT_POKEMON_ATTAQUE="INSERT INTO "+TABLE_NAME+" VALUES(0,0);";
    @Override
    public long insert(AttaqueLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(ATTAQUE_KEY, object.getID_Attaque());
            value.put(POKEMON_KEY, object.getID_Pokemon());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(AttaqueLiaison object) {
        try {
            database.delete(TABLE_NAME, ATTAQUE_KEY + " = ? AND " + POKEMON_KEY + " = ?", new String[]{String.valueOf(object.getID_Attaque()), String.valueOf(object.getID_Pokemon())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(AttaqueLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(ATTAQUE_KEY, object.getID_Attaque());
            value.put(POKEMON_KEY, object.getID_Pokemon());
            database.update(TABLE_NAME, value, ATTAQUE_KEY + " = ? AND " + POKEMON_KEY + " = ?", new String[]{String.valueOf(object.getID_Attaque()), String.valueOf(object.getID_Pokemon())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<AttaqueLiaison> getAll() {
        List<AttaqueLiaison> ListAL=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                AttaqueLiaison p = new AttaqueLiaison(cursor.getLong(0),cursor.getLong(1));
                ListAL.add(p);
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListAL;
    }

    public List<Attaque> getAttaquesPokemon(Pokemon pokemon) {
        List<Attaque> ListAL=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID_Pokemon="+pokemon.getId(),null);
            cursor.moveToFirst();
            do{
                ListAL.add(attaqueDao.getAttaqueById(cursor.getLong(0)));
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListAL;
    }
}
