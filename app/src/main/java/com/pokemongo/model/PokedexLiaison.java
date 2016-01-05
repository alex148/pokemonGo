package com.pokemongo.model;

/**
 * Created by Robin on 05/01/2016.
 */
public class PokedexLiaison {
    private long ID_Utilisateur;
    private long ID_RacePokemon;
    private boolean Decouvert;

    public PokedexLiaison(long ID_Utilisateur, long ID_RacePokemon, boolean decouvert) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.ID_RacePokemon = ID_RacePokemon;
        Decouvert = decouvert;
    }

    public long getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(long ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public long getID_RacePokemon() {
        return ID_RacePokemon;
    }

    public void setID_RacePokemon(long ID_RacePokemon) {
        this.ID_RacePokemon = ID_RacePokemon;
    }

    public boolean isDecouvert() {
        return Decouvert;
    }

    public void setDecouvert(boolean decouvert) {
        Decouvert = decouvert;
    }
}
