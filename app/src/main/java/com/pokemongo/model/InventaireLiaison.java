package com.pokemongo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class InventaireLiaison {

    private User user;
    private Objet objet;
    private int quantite;

    public InventaireLiaison(User user, Objet objet, int quantite) {
        this.user = user;
        this.objet = objet;
        this.quantite = quantite;
    }
    
    public InventaireLiaison(){
        this.user = new User();
        this.objet = new Objet();
        this.quantite = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Objet getObjet() {
        return this.objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
