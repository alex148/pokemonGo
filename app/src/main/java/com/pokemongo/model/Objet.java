package com.pokemongo.model;

/**
 * Created by Alex on 04/01/2016.
 */
public class Objet {

    private long id;
    private String nom;
    private String description;
    private CategorieObjet categorie;
    private Stat statConcernee;
    private int valeur;

    public Objet(long id,int valeur, String nom, String description, CategorieObjet categorie, Stat statConcernee) {
        this.valeur = valeur;
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.statConcernee = statConcernee;
    }

    public Objet(){
        this.valeur = 0;
        this.id = 0;
        this.nom = "";
        this.description = "";
        this.categorie = null;
        this.statConcernee = null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategorieObjet getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieObjet categorie) {
        this.categorie = categorie;
    }

    public Stat getStatConcernee() {
        return statConcernee;
    }

    public void setStatConcernee(Stat statConcernee) {
        this.statConcernee = statConcernee;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
