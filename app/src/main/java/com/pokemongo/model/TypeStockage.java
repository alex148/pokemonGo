package com.pokemongo.model;

/**
 * Created by Alex on 04/01/2016.
 */
public class TypeStockage {

    private long id;
    private String libelle;

    public TypeStockage(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public TypeStockage(){
        this.id = 0;
        this.libelle = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
