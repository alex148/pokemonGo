package com.pokemongo.dao;

import android.content.ContentValues;

import com.pokemongo.model.Inventaire;
import com.pokemongo.model.InventaireLiaison;

import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class InventaireDao extends Dao implements Crud<InventaireLiaison> {

    public static final String TABLE_NAME = "inventaire";
    public static final String INVENTAIRE_KEY = "ID_Inventaire";
    public static final String OBJET_KEY = "ID_Objet";
    public static final String QUANTITE ="quantite";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+INVENTAIRE_KEY+" INTEGER NOT NULL , "+
            OBJET_KEY+" INTEGER NOT NULL, "+
            QUANTITE+" INTEGER NOT NULL," +
            "PRIMARY KEY("+INVENTAIRE_KEY+","+OBJET_KEY+")"+
            "FOREIGN KEY("+OBJET_KEY+") REFERENCES "+ObjetDao.TABLE_NAME+"("+ObjetDao.OBJET_KEY+"));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_INVENTAIRE_TEST_POTION = "INSERT INTO "+TABLE_NAME+" VALUES(1,1,10);";
    public static final String INSERT_INVENTAIRE_TEST_POKEBALL = "INSERT INTO "+TABLE_NAME+" VALUES(1,2,8);";

    @Override
    public long insert(InventaireLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(INVENTAIRE_KEY, object.getUser().getInventaire().getId());
            value.put(OBJET_KEY, object.getObjet().getId());
            value.put(QUANTITE, object.getQuantite());
            return database.insert(TABLE_NAME, null, value);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;

    }

    @Override
    public void delete(InventaireLiaison object) {
        try {
            database.delete(TABLE_NAME, INVENTAIRE_KEY + " = ?" , new String[]{String.valueOf(object.getUser().getInventaire().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(InventaireLiaison object) {
        try{
            ContentValues value = new ContentValues();
            value.put(QUANTITE, object.getQuantite());
            database.update(TABLE_NAME, value, INVENTAIRE_KEY + " = ? AND "+OBJET_KEY+" = ?", new String[]{String.valueOf(object.getUser().getInventaire().getId()),
                    String.valueOf(object.getObjet().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<InventaireLiaison> getAll() {

        //todo
        return null;
    }
}
