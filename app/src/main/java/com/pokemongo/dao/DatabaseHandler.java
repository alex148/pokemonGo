package com.pokemongo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pokemongo.model.Attaque;
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
        db.execSQL(RaceDao.INSERT_RACE_2);
        db.execSQL(RaceDao.INSERT_RACE_3);
        db.execSQL(RaceDao.INSERT_RACE_4);
        db.execSQL(RaceDao.INSERT_RACE_5);

        db.execSQL(PokemonDao.TABLE_CREATE);
        db.execSQL(PokemonDao.INSERT_POKEMON);
        db.execSQL(PokemonDao.INSERT_POKEMON_2);
        db.execSQL(PokemonDao.INSERT_POKEMON_3);
        db.execSQL(PokemonDao.INSERT_POKEMON_4);


        db.execSQL(PokedexDao.TABLE_CREATE);
        db.execSQL(PokedexDao.INSERT_POKEDEX);
        db.execSQL(PokedexDao.INSERT_POKEDEX_2);
        db.execSQL(PokedexDao.INSERT_POKEDEX_3);
        db.execSQL(PokedexDao.INSERT_POKEDEX_4);


        db.execSQL(StockageDao.TABLE_CREATE);
        db.execSQL(StockageDao.INSERT_STOCKAGE_TEST);
        db.execSQL(StockageDao.INSERT_STOCKAGE_TEST_3);
        db.execSQL(StockageDao.INSERT_STOCKAGE_TEST_2);
        db.execSQL(StockageDao.INSERT_STOCKAGE_TEST_4);

        db.execSQL(AttaqueDao.TABLE_CREATE);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_2);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_3);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_4);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_5);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_6);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_7);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_8);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_9);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_10);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_11);
        db.execSQL(AttaqueDao.INSERT_ATTAQUE_12);


        db.execSQL(AttaquePokemonDao.TABLE_CREATE);
        db.execSQL(AttaquePokemonDao.INSERT_POKEMON_ATTAQUE);
        db.execSQL(AttaquePokemonDao.INSERT_POKEMON_ATTAQUE_2);
        db.execSQL(AttaquePokemonDao.INSERT_POKEMON_ATTAQUE_3);
        db.execSQL(AttaquePokemonDao.INSERT_POKEMON_ATTAQUE_4);
        db.execSQL(AttaquePokemonDao.INSERT_POKEMON_ATTAQUE_5);



        db.execSQL(ZoneDao.TABLE_CREATE);
        db.execSQL(ZoneDao.INSERT_ZONE);
        db.execSQL(ZoneDao.INSERT_ZONE_2);
        db.execSQL(ZoneDao.INSERT_ZONE_3);
        db.execSQL(ZoneDao.INSERT_ZONE_4);
        db.execSQL(ZoneDao.INSERT_ZONE_5);
        db.execSQL(ZoneDao.INSERT_ZONE_6);
        db.execSQL(ZoneDao.INSERT_ZONE_7);
        db.execSQL(ZoneDao.INSERT_ZONE_8);
        db.execSQL(ZoneDao.INSERT_ZONE_9);
        db.execSQL(ZoneDao.INSERT_ZONE_10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(InventaireDao.DROP_TABLE);
        db.execSQL(ObjetDao.DROP_TABLE);
        db.execSQL(StockageDao.DROP_TABLE);
        db.execSQL(UserDao.DROP_TABLE);
        db.execSQL(PokemonDao.DROP_TABLE);
        db.execSQL(PokedexDao.DROP_TABLE);
        db.execSQL(ZoneDao.DROP_TABLE);
        db.execSQL(RaceDao.DROP_TABLE);
        db.execSQL(AttaquePokemonDao.DROP_TABLE);
        db.execSQL(AttaqueDao.DROP_TABLE);
        onCreate(db);
    }
}
