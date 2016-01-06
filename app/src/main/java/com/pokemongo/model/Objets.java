package com.pokemongo.model;

import java.io.Serializable;

/**
 * Created by Alex on 05/01/2016.
 */
public class Objets implements Serializable {

    private Objet objet;
    private int quantite;

    public Objets(Objet objet, int quantite) {
        this.objet = objet;
        this.quantite = quantite;
    }

    public Objets() {
        this.objet = new Objet();
        this.quantite = 0;
    }

    public Objet getObjet() {
        return objet;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
