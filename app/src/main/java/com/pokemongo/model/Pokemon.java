package com.pokemongo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Thomas on 04/01/2016.
 */
public class Pokemon implements Serializable {

    private long id;
    private int pv;
    private int attaque;
    private int attaqueSpe;
    private int defense;
    private int defenseSpe;
    private int vitesse;
    private int niveau;
    private int experience;
    private Race race;
    private List<Attaque> attaques;

    public Pokemon(){
        this.id = -1;
    }
    public Pokemon(long id, int pv, int attaque, int attaqueSpe, int defense, int defenseSpe, int vitesse, int niveau, int experience,
                   Race race) {
        this.id = id;
        this.pv = pv;
        this.attaque = attaque;
        this.attaqueSpe = attaqueSpe;
        this.defense = defense;
        this.defenseSpe = defenseSpe;
        this.vitesse = vitesse;
        this.niveau = niveau;
        this.experience = experience;
        this.race = race;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getAttaqueSpe() {
        return attaqueSpe;
    }

    public void setAttaqueSpe(int attaqueSpe) {
        this.attaqueSpe = attaqueSpe;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDefenseSpe() {
        return defenseSpe;
    }

    public void setDefenseSpe(int defenseSpe) {
        this.defenseSpe = defenseSpe;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }


    public List<Attaque> getAttaques() {
        return attaques;
    }

    public void setAttaques(List<Attaque> attaques) {
        this.attaques = attaques;
    }
}
