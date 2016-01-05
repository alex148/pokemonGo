package com.pokemongo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pokemongo.model.Inventaire;
import com.pokemongo.model.Pokedex;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Stockage;

/**
 * Created by Alex on 18/12/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDao.TABLE_CREATE);
        db.execSQL(UserDao.INSERT_USERS);

        db.execSQL(ObjetDao.TABLE_CREATE);
        db.execSQL(ObjetDao.INSERT_OBJECT_POTION);
        db.execSQL(ObjetDao.INSERT_OBJECT_POKEBALL);

        db.execSQL(InventaireDao.TABLE_CREATE);
        db.execSQL(InventaireDao.INSERT_INVENTAIRE_TEST_POTION);
        db.execSQL(InventaireDao.INSERT_INVENTAIRE_TEST_POKEBALL);

        db.execSQL(RaceDao.TABLE_CREATE);
        db.execSQL(RaceDao.INSERT_RACE);

        db.execSQL(PokemonDao.TABLE_CREATE);
        db.execSQL(PokemonDao.INSERT_POKEMON);

        db.execSQL(PokedexDao.TABLE_CREATE);
        db.execSQL(PokedexDao.INSERT_POKEDEX);

        db.execSQL(StockageDao.TABLE_CREATE);
        db.execSQL(StockageDao.INSERT_STOCKAGE_TEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(InventaireDao.DROP_TABLE);
        db.execSQL(ObjetDao.DROP_TABLE);
        db.execSQL(StockageDao.DROP_TABLE);
        db.execSQL(UserDao.DROP_TABLE);
        db.execSQL(PokemonDao.DROP_TABLE);
        db.execSQL(PokedexDao.DROP_TABLE);
        db.execSQL(RaceDao.DROP_TABLE);
        onCreate(db);
    }
}
