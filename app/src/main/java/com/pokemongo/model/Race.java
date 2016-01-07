package com.pokemongo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 04/01/2016.
 */
public class Race implements Serializable {

    private long id;
    private String nomRace;
    private String description;
    private double poids;
    private double taille;
    private Type type1;
    private Type type2;
    private boolean decouvert;
    private List<Zone> zones;

    public Race(long id, String nomRace, String description, double poids, double taille,Type type1,Type type2,Boolean decouvert) {
        this.id = id;
        this.nomRace = nomRace;
        this.description = description;
        this.poids = poids;
        this.taille = taille;
        this.type1=type1;
        this.type2=type2;
        this.decouvert=decouvert;
        zones = new ArrayList<Zone>();
    }
    public Race() {

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

    public Double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
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

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
