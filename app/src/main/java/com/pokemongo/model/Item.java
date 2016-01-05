package com.pokemongo.model;

/**
 * Created by Alex on 05/01/2016.
 */
public class Item {

    private Objet objet;
    private int quantite;

    public Item(Objet objet, int quantite) {
        this.objet = objet;
        this.quantite = quantite;
    }

    public Item() {
        this.objet = new Objet();
        this.quantite = 0;
    }

    public Objet getObjet() {
        return objet;
    }

    public int getQuantite() {
        return quantite;
    }
}
