package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.CategorieObjet;
import com.pokemongo.model.Objet;
import com.pokemongo.model.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 05/01/2016.
 */
public class ObjetDao extends Dao implements Crud<Objet> {

    public static final String TABLE_NAME = "objet";
    public static final String OBJET_KEY = "ID_Objet";
    public static final String NOM ="nom";
    public static final String DESCRIPTION ="description";
    public static final String CATEGORIE ="categorie";
    public static final String STAT_CONCERNEE = "stat";
    public static final String VALEUR = "valeur";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+OBJET_KEY+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
            VALEUR+" INTEGER NOT NULL, "+
            DESCRIPTION+" STRING NOT NULL," +
            NOM+" STRING NOT NULL, "+
            CATEGORIE+" STRING, "+
            STAT_CONCERNEE+" STRING);";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";


    public static final String INSERT_OBJECT_POTION = "INSERT INTO "+TABLE_NAME+" VALUES (1,10,'une potion magique', 'potion'," +
            " '"+ CategorieObjet.CONSOMMABLE.toString()+"', '"+Stat.ATTAQUE.toString()+"');";
    public static final String INSERT_OBJECT_POKEBALL = "INSERT INTO "+TABLE_NAME+" VALUES (2,15, 'une pokeball de merde', 'pokeball'," +
            " '"+ CategorieObjet.BALL.toString()+"', '"+Stat.DEFENSE.toString()+"');";

    public ObjetDao(Context context){
        super(context);
        this.open();
    }

    @Override
    public long insert(Objet object) {
        try {
            ContentValues value = new ContentValues();
            value.put(VALEUR, object.getValeur());
            value.put(DESCRIPTION, object.getDescription());
            value.put(NOM, object.getNom());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(STAT_CONCERNEE, object.getStatConcernee().toString());
            return database.insert(TABLE_NAME, null, value);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Objet object) {
        try {
            database.delete(TABLE_NAME, OBJET_KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Objet object) {
        try{
            ContentValues value = new ContentValues();
            value.put(VALEUR, object.getValeur());
            value.put(DESCRIPTION, object.getDescription());
            value.put(NOM, object.getNom());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(STAT_CONCERNEE, object.getStatConcernee().toString());
            database.update(TABLE_NAME, value, OBJET_KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Objet> getAll() {
        List<Objet> list=new ArrayList<Objet>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        while(cursor.moveToNext()){
            Objet objet = new Objet();
            objet.setId(cursor.getLong(0));
            objet.setValeur(1);
            objet.setDescription(cursor.getString(2));
            objet.setNom(cursor.getString(3));
            objet.setCategorie(CategorieObjet.valueOf(cursor.getString(4)));
            objet.setStatConcernee(Stat.valueOf(cursor.getString(5)));
            list.add(objet);
        }
        return list;
    }

    public Objet getById(long id){
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+OBJET_KEY+" = ?", new String[]{String.valueOf(id)});
            if(cursor.getCount() != 1)
                return null;
            cursor.moveToFirst();
            Objet objet = new Objet();
            objet.setId(cursor.getLong(0));
            objet.setValeur(cursor.getInt(1));
            objet.setNom(cursor.getString(3));
            objet.setDescription(cursor.getString(2));
            objet.setCategorie(CategorieObjet.valueOf(cursor.getString(4)));
            objet.setStatConcernee(Stat.valueOf(cursor.getString(5)));
            return objet;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
