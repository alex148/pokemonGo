package com.pokemongo.model;

/**
 * Created by Robin on 04/01/2016.
 */
public class Type {
    private long id;
    private String nom;

    public Type(){
        this.id=-1;
        this.nom="";
    }
    public Type(long id, String nom){
        this.id=id;
        this.nom=nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
