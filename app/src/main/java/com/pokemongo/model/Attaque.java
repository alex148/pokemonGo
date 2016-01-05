package com.pokemongo.model;

/**
 * Created by Thomas on 04/01/2016.
 */
public class Attaque {

    private long id;
    private String nom;
    private int degats;
    private int precision;
    private String description;
    private CategorieAttaque categorie;
    private Type type;

    public Attaque(long id, String nom, int degats, int precision, String description, CategorieAttaque categorie, Type type) {
        this.id = id;
        this.nom = nom;
        this.degats = degats;
        this.precision = precision;
        this.description = description;
        this.categorie = categorie;
        this.type = type;
    }
    public Attaque(){

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CategorieAttaque getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieAttaque categorie) {
        this.categorie = categorie;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
