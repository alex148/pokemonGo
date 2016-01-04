package com.pokemongo.dao;

import android.content.ContentValues;

import com.pokemongo.model.Stockage;
import com.pokemongo.model.StockageLiaison;


/**
 * Created by Alex on 04/01/2016.
 */
public class StockageDao extends Dao implements Crud<StockageLiaison> {

    public static final String TABLE_NAME = "stockage";
    public static final String USER_KEY = "ID_User";
    public static final String POKEMON_KEY = "ID_Pokemon";
    public static final String TYPE_STOCKAGE_KEY ="ID_Type_Stockage";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+USER_KEY+" INTEGER NOT NULL , "+
            POKEMON_KEY+" INTEGER NOT NULL, "+
            TYPE_STOCKAGE_KEY+" INTEGER NOT NULL," +
            "PRIMARY_KEY("+USER_KEY+","+POKEMON_KEY+")," +
            "FOREIGN KEY("+TYPE_STOCKAGE_KEY+") REFERENCES "+ TypeStockageDao.TABLE_NAME+"("+TypeStockageDao.KEY+");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    @Override
    public long insert(StockageLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getUser().getId());
            value.put(POKEMON_KEY, object.getPokemon().getId());
            value.put(TYPE_STOCKAGE_KEY, object.getType().getId());
            return database.insert(TABLE_NAME, null, value);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(StockageLiaison stockage) {
        try {
            database.delete(TABLE_NAME, USER_KEY + " = ? AND "+POKEMON_KEY+" = ?" , new String[]{String.valueOf(stockage.getUser().getId()),
                    String.valueOf(stockage.getPokemon().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(StockageLiaison object) {

    }

    @Override
    public StockageLiaison get(StockageLiaison stockage) {
        return null;
    }
}
