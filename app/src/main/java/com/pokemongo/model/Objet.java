package com.pokemongo.model;

/**
 * Created by Alex on 04/01/2016.
 */
public class Objet {

    private long id;
    private int valeur;
    private String description;
    private Categorie categorie;
    private Effet effet;

    public Objet(long id, int valeur, String description, Categorie categorie, Effet effet) {
        this.id = id;
        this.valeur = valeur;
        this.description = description;
        this.categorie = categorie;
        this.effet = effet;
    }

    public Objet(){
        this.id = 0;
        this.valeur = 0;
        this.description = "";
        this.categorie = new Categorie();
        this.effet = new Effet();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Effet getEffet() {
        return effet;
    }

    public void setEffet(Effet effet) {
        this.effet = effet;
    }
}
