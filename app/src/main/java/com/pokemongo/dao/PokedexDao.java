package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.pokemongo.model.Pokedex;
import com.pokemongo.model.PokedexLiaison;
import com.pokemongo.model.Race;
import com.pokemongo.model.User;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class PokedexDao extends Dao implements Crud<PokedexLiaison> {

    private RaceDao raceDao;
    public PokedexDao(Context context){
        super(context);
        this.open();
        raceDao=new RaceDao(context);
    }

    public static final String TABLE_NAME = "pokedex";
    public static final String USER_KEY = "ID_Utilisateur";
    public static final String RACE_KEY = "ID_RacePokemon";
    public static final String DECOUVERT = "estDecouvert";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +USER_KEY+" INTEGER NOT NULL , "
            +RACE_KEY+" INTEGER NOT NULL , "
            +DECOUVERT +" INTEGER NOT NULL,"
            +"PRIMARY KEY("+USER_KEY+","+RACE_KEY+"),"
            +"FOREIGN KEY("+USER_KEY+") REFERENCES "+UserDao.TABLE_NAME+"("+UserDao.KEY+"),"
            +"FOREIGN KEY("+RACE_KEY+") REFERENCES "+RaceDao.TABLE_NAME+"("+RaceDao.KEY+")"
            + ");";



    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    public static final String INSERT_POKEDEX = "INSERT INTO "+TABLE_NAME+" VALUES (1,0,1);";
    public static final String INSERT_POKEDEX_2 = "INSERT INTO "+TABLE_NAME+" VALUES (1,2,1);";
    public static final String INSERT_POKEDEX_3 = "INSERT INTO "+TABLE_NAME+" VALUES (1,3,1);";
    public static final String INSERT_POKEDEX_4 = "INSERT INTO "+TABLE_NAME+" VALUES (1,4,1);";
    @Override
    public long insert(PokedexLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getID_Utilisateur());
            value.put(RACE_KEY, object.getID_RacePokemon());
            value.put(DECOUVERT, object.isDecouvert());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(PokedexLiaison object) {
        try {
            database.delete(TABLE_NAME, USER_KEY + " = ? AND " + RACE_KEY + " = ?", new String[]{String.valueOf(object.getID_Utilisateur()), String.valueOf(object.getID_RacePokemon())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(PokedexLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getID_Utilisateur());
            value.put(RACE_KEY, object.getID_RacePokemon());
            value.put(DECOUVERT, object.isDecouvert());
            database.update(TABLE_NAME, value, USER_KEY + " = ? AND " + RACE_KEY + " = ?", new String[]{String.valueOf(object.getID_Utilisateur()), String.valueOf(object.getID_RacePokemon())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<PokedexLiaison> getAll() {
        List<PokedexLiaison> ListPL=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                PokedexLiaison p = new PokedexLiaison(cursor.getLong(0),cursor.getLong(1),cursor.getInt(2)>0);
                ListPL.add(p);
            }while (cursor.moveToNext());
            return ListPL;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListPL;
    }


    public List<PokedexLiaison> getAllByUser(User user) {
        List<PokedexLiaison> ListRace=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID_Utilisateur="+user.getId(),null);
            cursor.moveToFirst();
            do{
                PokedexLiaison p = new PokedexLiaison(cursor.getLong(0),cursor.getLong(1),cursor.getInt(2)>0);
                ListRace.add(p);
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListRace;
    }

    public Pokedex getPokedex(User user){
        List<PokedexLiaison> ListPokedex=getAllByUser(user);
        List<Race> ListRace=raceDao.getAll() ;

        for (PokedexLiaison p :ListPokedex)
        {
            for(Race r : ListRace){
                if(p.getID_RacePokemon()==r.getId()){
                    r.setDecouvert(p.isDecouvert());
                }
            }
        }

        return (new Pokedex(ListRace));
    }
}
