package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
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

    private PokemonDao pokemonDao;

    public static final String TABLE_NAME = "stockage";
    public static final String USER_KEY = "ID_User";
    public static final String POKEMON_KEY = "ID_Pokemon";
    public static final String TYPE_STOCKAGE ="type_Stockage";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+USER_KEY+" INTEGER NOT NULL , "+
            POKEMON_KEY+" INTEGER NOT NULL, "+
            TYPE_STOCKAGE+" VARCHAR(50) NOT NULL," +
            "FOREIGN KEY("+USER_KEY+") REFERENCES "+UserDao.TABLE_NAME+"("+UserDao.KEY+"),"+
            "FOREIGN KEY("+POKEMON_KEY+") REFERENCES "+PokemonDao.TABLE_NAME+"("+PokemonDao.KEY+"),"+
            "PRIMARY KEY("+USER_KEY+","+POKEMON_KEY+"));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_STOCKAGE_TEST = "INSERT INTO "+TABLE_NAME+" VALUES(1,0,'"+TypeStockage.PC.toString()+"')";
    public static final String INSERT_STOCKAGE_TEST_2 = "INSERT INTO "+TABLE_NAME+" VALUES(1,1,'"+TypeStockage.EQUIPE.toString()+"')";
    public static final String INSERT_STOCKAGE_TEST_3 = "INSERT INTO "+TABLE_NAME+" VALUES(1,2,'"+TypeStockage.EQUIPE.toString()+"')";
    public static final String INSERT_STOCKAGE_TEST_4 = "INSERT INTO "+TABLE_NAME+" VALUES(1,3,'"+TypeStockage.PC.toString()+"')";



    public StockageDao(Context context){
        super(context);
        pokemonDao = new PokemonDao(context);
        this.open();
    }

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
        List<StockageLiaison> list=new ArrayList<StockageLiaison>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        while(cursor.moveToNext()){
            StockageLiaison liaison = new StockageLiaison();
            liaison.setPokemon(null);   //todo
            liaison.setType(null);
            liaison.setUser(null);
            list.add(liaison);
        }
        return list;
    }

    public List<Pokemon> getPokemonsFromStockage(User user, String type){
        try{
            List<Pokemon> pokemons = new ArrayList<Pokemon>();
            Cursor cursor;
            if(type == null){
                cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USER_KEY +" = ?"
                        ,new String[]{String.valueOf(user.getId())});

            }else{
                cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USER_KEY +" = ? AND "+TYPE_STOCKAGE+" = ?"
                        ,new String[]{String.valueOf(user.getId()),type});
            }
            if(cursor.getCount() == 0){
                return null;
            }
            while(cursor.moveToNext()){
                Pokemon p = this.pokemonDao.getById(cursor.getLong(1));
                if(p != null){
                    pokemons.add(p);
                }
            }
            return pokemons;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
