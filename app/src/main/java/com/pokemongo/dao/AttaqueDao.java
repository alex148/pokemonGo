package com.pokemongo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pokemongo.model.Attaque;
import com.pokemongo.model.CategorieAttaque;
import com.pokemongo.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class AttaqueDao extends Dao implements Crud<Attaque> {


    public AttaqueDao(Context context){
        super(context);
        this.open();
    }

    public static final String TABLE_NAME = "attaque";
    public static final String KEY = "ID_Attaque";
    public static final String NOM= "nom";
    public static final String DEGAT= "degat";
    public static final String PRECISION= "precision";
    public static final String DESCRIPTION= "description";
    public static final String CATEGORIE= "categorie";
    public static final String TYPE= "type";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" " +
            "( "
            +KEY+"  INTEGER PRIMARY KEY AUTOINCREMENT , "
            +NOM+" STRING NOT NULL , "
            +DEGAT+" INTEGER NOT NULL,"
            +PRECISION+" INTEGER NOT NULL,"
            +DESCRIPTION+" STRING NOT NULL , "
            +CATEGORIE+" STRING NOT NULL , "
            +TYPE+" STRING NOT NULL"
            + ");";


    public static final String INSERT_ATTAQUE = "INSERT INTO "+TABLE_NAME+" VALUES(0,'Charge',20,100,'Charge l adversaire de tout son poids','Physique','Normal');";
    public static final String INSERT_ATTAQUE_2 = "INSERT INTO "+TABLE_NAME+" VALUES(1,'A la Queue',0,100,'Retient la cible de force, l obligeant à agir en dernier.','Physique','Tenebre');";
    public static final String INSERT_ATTAQUE_3 = "INSERT INTO "+TABLE_NAME+" VALUES(2,'Abime',0,30,'Fait tomber l ennemi dans une crevasse et le met K.O.','Physique','Sol');";
    public static final String INSERT_ATTAQUE_4 = "INSERT INTO "+TABLE_NAME+" VALUES(3,'Aboiement',55,95,'Le lanceur hurle sur l ennemi. Baisse l Attaque Spéciale de l ennemi.','Speciale','Tenebre');";
    public static final String INSERT_ATTAQUE_5 = "INSERT INTO "+TABLE_NAME+" VALUES(4,'Abri',20,0,'Esquive l attaque, mais peut échouer si réutilisé.','Physique','Normal');";
    public static final String INSERT_ATTAQUE_6 = "INSERT INTO "+TABLE_NAME+" VALUES(5,'Acidarmure',0,100,'Augmente la Défense du lanceur de deux niveaux.','Physique','Poison');";

    public static final String INSERT_ATTAQUE_7 = "INSERT INTO "+TABLE_NAME+" VALUES(6,'Aide d Eau',50,92,'Une masse d eau s abat sur l ennemi. En l utilisant avec Aire de Feu, l effet augmente et un arc-en-ciel apparaît.','Speciale','Eau');";
    public static final String INSERT_ATTAQUE_8 = "INSERT INTO "+TABLE_NAME+" VALUES(7,'Aire d Herbe',50,80,'Une masse végétale s abat sur l ennemi. En l utilisant avec Aire d Eau, l effet augmente et un marécage apparaît.','Speciale','Plante');";
    public static final String INSERT_ATTAQUE_9 = "INSERT INTO "+TABLE_NAME+" VALUES(8,'Aide de Feu',50,70,'Une masse de feu s abat sur l ennemi. En l utilisant avec Aire d Herbe, l effet augmente et une mer de feu apparaît.','Speciale','Feu');";

    public static final String INSERT_ATTAQUE_10 = "INSERT INTO "+TABLE_NAME+" VALUES(9,'Appel Attak',90,50,'Haut taux de Coup Critique. (+1)','Physique','Insecte');";
    public static final String INSERT_ATTAQUE_11 = "INSERT INTO "+TABLE_NAME+" VALUES(10,'Balle Graine',10,95,'Mitraille l ennemi avec 2 à 5 rafales à la suite.','Physique','Plante');";
    public static final String INSERT_ATTAQUE_12 = "INSERT INTO "+TABLE_NAME+" VALUES(11,'Bulldoboule',65,90,'Le lanceur se roule en boule et écrase son ennemi. Peut aussi apeurer l ennemi.','Physique','Insecte');";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    @Override
    public long insert(Attaque object) {
        try {
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNom());
            value.put(DEGAT, object.getDegats());
            value.put(PRECISION, object.getPrecision());
            value.put(DESCRIPTION, object.getDescription());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(TYPE, object.getType().toString());
            return database.insert(TABLE_NAME, null, value);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void delete(Attaque object) {
        try {
            database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Attaque object) {
        try {
            ContentValues value = new ContentValues();
            value.put(NOM, object.getNom());
            value.put(DEGAT, object.getDegats());
            value.put(PRECISION, object.getPrecision());
            value.put(DESCRIPTION, object.getDescription());
            value.put(CATEGORIE, object.getCategorie().toString());
            value.put(TYPE, object.getType().toString());
            database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Attaque> getAll() {
        List<Attaque> ListAttaque=new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            do{
                Attaque p = new Attaque(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)
                        ,cursor.getString(4), CategorieAttaque.valueOf(cursor.getString(5)), Type.valueOf(cursor.getString(6)));
                ListAttaque.add(p);
            }while (cursor.moveToNext());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListAttaque;
    }
    public Attaque getAttaqueById(long id) {
        Attaque a=new Attaque();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID_Attaque="+id,null);
            cursor.moveToFirst();
            a = new Attaque(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)
                    ,cursor.getString(4), CategorieAttaque.valueOf(cursor.getString(5)), Type.valueOf(cursor.getString(6)));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }
}
