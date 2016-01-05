package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Inventaire;
import com.pokemongo.model.InventaireLiaison;
import com.pokemongo.model.Objets;
import com.pokemongo.model.Objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class InventaireDao extends Dao implements Crud<InventaireLiaison> {

    private ObjetDao objetDao;

    public static final String TABLE_NAME = "inventaire";
    public static final String USER_KEY = "ID_User";
    public static final String OBJET_KEY = "ID_Objet";
    public static final String QUANTITE ="quantite";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "+ USER_KEY +" INTEGER NOT NULL , "+
            OBJET_KEY+" INTEGER NOT NULL, "+
            QUANTITE+" INTEGER NOT NULL," +
            "FOREIGN KEY("+USER_KEY+") REFERENCES "+UserDao.TABLE_NAME+"("+UserDao.KEY+"),"+
            "FOREIGN KEY("+OBJET_KEY+") REFERENCES "+ObjetDao.TABLE_NAME+"("+ObjetDao.OBJET_KEY+")," +
            "PRIMARY KEY("+ USER_KEY +","+OBJET_KEY+"));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public static final String INSERT_INVENTAIRE_TEST_POTION = "INSERT INTO "+TABLE_NAME+" VALUES(1,1,10);";
    public static final String INSERT_INVENTAIRE_TEST_POKEBALL = "INSERT INTO "+TABLE_NAME+" VALUES(1,2,8);";


    public InventaireDao(Context context){
        super(context);
        this.open();
        this.objetDao = new ObjetDao(context);
    }
    @Override
    public long insert(InventaireLiaison object) {
        try {
            ContentValues value = new ContentValues();
            value.put(USER_KEY, object.getUser().getId());
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
            database.delete(TABLE_NAME, USER_KEY + " = ? AND "+OBJET_KEY+" = ?" , new String[]{String.valueOf(object.getUser().getId()),
                    String.valueOf(object.getObjet().getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(InventaireLiaison object) {
        try{
            ContentValues value = new ContentValues();
            value.put(QUANTITE, object.getQuantite());
            database.update(TABLE_NAME, value, USER_KEY + " = ? AND "+OBJET_KEY+" = ?", new String[]{String.valueOf(object.getUser().getId()),
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

    public Inventaire getUserInventaire(long idUser){
        Inventaire inventaire = new Inventaire();
        List<Long> objectIds = new ArrayList<Long>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ USER_KEY +" = ?",new String[]{String.valueOf(idUser)});
        if(cursor.getCount() > 0){
            inventaire.setId(idUser);
            while(cursor.moveToNext()){
                Objet objet = this.objetDao.getById(cursor.getLong(1));
                Objets item = new Objets(objet,cursor.getInt(2));
                inventaire.getItems().add(item);
            }
        }else{
            return null;
        }
        return inventaire;
    }
}
