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
    private boolean decouvert;

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
}
