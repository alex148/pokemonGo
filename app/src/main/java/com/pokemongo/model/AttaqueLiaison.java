package com.pokemongo.model;

/**
 * Created by Robin on 05/01/2016.
 */
public class AttaqueLiaison {
    private long ID_Attaque;
    private long ID_Pokemon;

    public AttaqueLiaison(long ID_Attaque, long ID_Pokemon) {
        this.ID_Attaque = ID_Attaque;
        this.ID_Pokemon = ID_Pokemon;
    }

    public long getID_Attaque() {
        return ID_Attaque;
    }

    public void setID_Attaque(long ID_Attaque) {
        this.ID_Attaque = ID_Attaque;
    }

    public long getID_Pokemon() {
        return ID_Pokemon;
    }

    public void setID_Pokemon(long ID_Pokemon) {
        this.ID_Pokemon = ID_Pokemon;
    }
}
