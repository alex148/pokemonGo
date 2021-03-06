package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class PokemonDao extends Dao implements Crud<Pokemon> {

    private AttaquePokemonDao attaquePokemonDao;
    private RaceDao raceDao;
    public PokemonDao(Context context){
        super(context);
        this.open();
        attaquePokemonDao=new AttaquePokemonDao(context);
        raceDao=new RaceDao(context);
    }

    public static final String TABLE_NAME = "pokemon";
    public static final String KEY = "ID_Pokemon";
    public static final String PV = "pv";
    public static final String ATTAQUE = "attaque";
    public static final String ATTAQUESPE = "attaqueSpe";
    public static final String DEFENSE= "defense";
    public static final String DEFENSESPE= "defenseSpe";
    public static final String VITESSE= "vitesse";
    public static final String NIVEAU= "niveau";
    public static final String EXPERIENCE= "experience";
    public static final String RACE_KEY = "ID_Race";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PV+" INTEGER NOT NULL,"
            +ATTAQUE +" INTEGER NOT NULL,"
            +ATTAQUESPE+" INTEGER NOT NULL,"
            +DEFENSE+" INTEGER NOT NULL,"
            +DEFENSESPE+" INTEGER NOT NULL,"
            +VITESSE+" INTEGER NOT NULL,"
            +NIVEAU+" INTEGER NOT NULL,"
            +EXPERIENCE+" INTEGER NOT NULL,"
            +RACE_KEY+" INTEGER NOT NULL,"
            +"FOREIGN KEY("+RACE_KEY+") REFERENCES "+ RaceDao.TABLE_NAME+"("+RaceDao.KEY+")"
            + ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_POKEMON = "INSERT INTO "+TABLE_NAME+" VALUES (0,50,10,10,10,10,20,1,0,0);";
    public static final String INSERT_POKEMON_2 = "INSERT INTO "+TABLE_NAME+" VALUES (1,60,10,10,10,10,50,2,0,2);";
    public static final String INSERT_POKEMON_3 = "INSERT INTO "+TABLE_NAME+" VALUES (2,40,15,15,15,15,20,1,0,3);";
    public static final String INSERT_POKEMON_4 = "INSERT INTO "+TABLE_NAME+" VALUES (3,30,10,10,5,30,20,1,0,4);";

    @Override
    public long insert(Pokemon object) {
        try {
            ContentValues value = new ContentValues();
            value.put(PV, object.getPv());
            value.put(ATTAQUE, object.getAttaque());
            value.put(ATTAQUESPE, object.getAttaqueSpe());
            value.put(DEFENSE, object.getDefense());
            value.put(DEFENSESPE,object.getDefenseSpe());
            value.put(VITESSE,object.getVitesse());
            value.put(NIVEAU,object.getNiveau());
            value.put(EXPERIENCE,object.getExperience());
            value.put(RACE_KEY,object.getRace().getId());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Pokemon object) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Pokemon object) {
        try{
            ContentValues value = new ContentValues();
            value.put(PV, object.getPv());
            value.put(ATTAQUE, object.getAttaque());
            value.put(ATTAQUESPE, object.getAttaqueSpe());
            value.put(DEFENSE, object.getDefense());
            value.put(DEFENSESPE,object.getDefenseSpe());
            value.put(VITESSE,object.getVitesse());
            value.put(NIVEAU,object.getVitesse());
            value.put(EXPERIENCE,object.getVitesse());
            value.put(RACE_KEY,object.getRace().getId());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Pokemon getById(long id){
        try{
            Pokemon p = new Pokemon();
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ KEY +" = ?"
                    ,new String[]{String.valueOf(id)});
            if(cursor == null || cursor.getCount() != 1){
                return null;
            }
            cursor.moveToFirst();
            p.setId(cursor.getLong(0));
            p.setPv(cursor.getInt(1));
            p.setAttaque(cursor.getInt(2));
            p.setAttaqueSpe(cursor.getInt(3));
            p.setDefense(cursor.getInt(4));
            p.setDefenseSpe(cursor.getInt(5));
            p.setVitesse(cursor.getInt(6));
            p.setNiveau(cursor.getInt(7));
            p.setExperience(cursor.getInt(8));
            p.setRace(raceDao.getRaceByID(cursor.getInt(9)));
            p.setAttaques(attaquePokemonDao.getAttaquesPokemon(p));
            return p;
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
       return null;
    }

    @Override
    public List<Pokemon> getAll() {
        List<Pokemon> ListPokemon=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                Pokemon p = new Pokemon(cursor.getLong(0),cursor.getInt(1),cursor.getInt(2),
                        cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7), cursor.getInt(8),raceDao.getRaceByID(cursor.getInt(9)));
                p.setAttaques(attaquePokemonDao.getAttaquesPokemon(p));
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListPokemon;
    }
}
