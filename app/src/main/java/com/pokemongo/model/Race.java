package com.pokemongo.model;

/**
 * Created by Thomas on 04/01/2016.
 */
public class Race {

    private long id;
    private String nomRace;
    private String description;
    private int poids;
    private int taille;
    private Type type1;
    private Type type2;
    private boolean decouvert;

    public Race(long id, String nomRace, String description, int poids, int taille,Type type1,Type type2) {
        this.id = id;
        this.nomRace = nomRace;
        this.description = description;
        this.poids = poids;
        this.taille = taille;
        this.type1=type1;
        this.type2=type2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomRace() {
        return nomRace;
    }

    public void setNomRace(String nomRace) {
        this.nomRace = nomRace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public boolean isDecouvert() {
        return decouvert;
    }

    public void setDecouvert(boolean decouvert) {
        this.decouvert = decouvert;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }
}
