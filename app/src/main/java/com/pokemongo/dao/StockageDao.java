package com.pokemongo.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Stockage;
import com.pokemongo.model.StockageLiaison;
import com.pokemongo.model.TypeStockage;
import com.pokemongo.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alex on 04/01/2016.
 */
public class StockageDao extends Dao implements Crud<StockageLiaison> {

    public static final String TABLE_NAME = "stockage";
    public static final String USER_KEY = "ID_User";
    public static final String POKEMON_KEY = "ID_Pokemon";
    public static final String TYPE_STOCKAGE ="ID_Type_Stockage";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+USER_KEY+" INTEGER NOT NULL , "+
            POKEMON_KEY+" INTEGER NOT NULL, "+
            TYPE_STOCKAGE+" VARCHAR(50) NOT NULL," +
            "PRIMARY_KEY("+USER_KEY+","+POKEMON_KEY+");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    @Override
    public long insert(StockageLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getUser().getId());
            value.put(POKEMON_KEY, object.getPokemon().getId());
            value.put(TYPE_STOCKAGE, object.getType());
            return database.insert(TABLE_NAME, null, value);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(StockageLiaison object) {
        try {
            database.delete(TABLE_NAME, USER_KEY + " = ? AND "+POKEMON_KEY+" = ?" , new String[]{String.valueOf(object.getUser().getId()),
                    String.valueOf(object.getPokemon().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(StockageLiaison object) {
        try{
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getUser().getId());
            value.put(POKEMON_KEY, object.getPokemon().getId());
            value.put(TYPE_STOCKAGE, object.getType());
            database.update(TABLE_NAME, value, USER_KEY + " = ? AND "+POKEMON_KEY+" = ?", new String[]{String.valueOf(object.getUser().getId()),
                    String.valueOf(object.getPokemon().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<StockageLiaison> getAll() {
        return null;
    }

    public List<Pokemon> getPokemons(User user, String type){
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        Cursor cursor;
        if(type == null){
             cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USER_KEY +" = ?"
                     ,new String[]{String.valueOf(user.getId())});

        }else{
            cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USER_KEY +" = ? AND "+TYPE_STOCKAGE+" = ?"
                    ,new String[]{String.valueOf(user.getId()),type});
        }

        while(cursor.moveToNext()){
            Pokemon p = new Pokemon();

            //todo
            pokemons.add(p);
        }
        return pokemons;
    }
}
