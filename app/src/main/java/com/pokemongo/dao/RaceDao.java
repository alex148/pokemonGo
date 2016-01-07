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

    private ZoneDao zoneDao;
    public RaceDao(Context context){
        super(context);
        zoneDao=new ZoneDao(context);
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
            +POIDS+" REAL NOT NULL,"
            +TAILLE+" REAL NOT NULL,"
            +TYPE1+" STRING NOT NULL,"
            +TYPE2+" STRING NOT NULL "
            + ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_RACE = "INSERT INTO "+TABLE_NAME+" VALUES (0,'Pikachu','Une souris qui fait des éclairs',14,90,'Electrique','None');";
    public static final String INSERT_RACE_2 = "INSERT INTO "+TABLE_NAME+" VALUES (1,'Boustiflor','Boustiflor peut faire penser à une cloche. Ses bras sont deux épaisses feuilles vertes, qu il agite comme des ailes.',6.4,1.0,'Plante','Poison');";
    public static final String INSERT_RACE_3 = "INSERT INTO "+TABLE_NAME+" VALUES (2,'Krabby','Krabby est un petit crabe au haut du corps et aux pinces rouges et au bas de la mâchoire crème.',6.5,0.4,'Eau','None');";
    public static final String INSERT_RACE_4 = "INSERT INTO "+TABLE_NAME+" VALUES (3,'Minidraco','Minidraco ressemble a un petit serpent de mer ayant une perle sur le front eu un dessous blanc.',3.3,1.8,'Dragon','None');";
    public static final String INSERT_RACE_5 = "INSERT INTO "+TABLE_NAME+" VALUES (4,'Mewtwo','Mewtwo ressemble a Mew. Il a une tête faisant penser à un chat, son corps quant à lui rapelle celui de la gerboise. Il est mauve presque blanc sur l ensemble du corps, ses yeux et sa queue sont plus foncés. Il possède trois doigts à chaque main se terminant par une sphère.',122,2,'Psy','None');";

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
                        cursor.getFloat(3),cursor.getFloat(4),Type.valueOf(cursor.getString(5)),Type.valueOf(cursor.getString(6)),false);
                r.setZones(zoneDao.getZonesByPokemon(r.getId()));

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
                    cursor.getFloat(3),cursor.getFloat(4),Type.valueOf(cursor.getString(5)),Type.valueOf(cursor.getString(6)),false);
            zoneDao.getZonesByPokemon(race.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return race;
    }
}
