package com.pokemongo.model;

import java.io.Serializable;

/**
 * Created by Alex on 18/12/2015.
 */
public class User implements Serializable {

    private long id;
    private String login;
    private String password;
    private Stockage equipe;
    private Stockage pc;
    private Inventaire inventaire;

    public User(){
        this.id = -1;
        this.login = "";
        this.password = "";
        this.equipe = new Stockage();
        this.inventaire = new Inventaire();
        this.pc = new Stockage();
    }

    public User(String login, String password){
        this.id = -1;
        this.login = login;
        this.password = password;
        this.equipe = new Stockage();
        this.inventaire = new Inventaire();
        this.pc = new Stockage();
    }

    public User(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
        this.equipe = new Stockage();
        this.pc = new Stockage();
        this.inventaire = new Inventaire();
    }

    public User(long id, String login, String password, Stockage pokemons){
        this.id = id;
        this.login = login;
        this.password = password;
        this.equipe = pokemons;
    }

    public User(long id, String login, String password, Stockage equipe,Stockage pc, Inventaire inventaire){
        this.id = id;
        this.login = login;
        this.password = password;
        this.equipe = equipe;
        this.inventaire = inventaire;
        this.pc = pc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Stockage getEquipe() {
        return equipe;
    }

    public void setEquipe(Stockage equipe) {
        this.equipe = equipe;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public Stockage getPc() {
        return pc;
    }

    public void setPc(Stockage pc) {
        this.pc = pc;
    }
}
