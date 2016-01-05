package com.pokemongo.model;

/**
 * Created by Alex on 18/12/2015.
 */
public class User {

    private long id;
    private String login;
    private String password;
    private Stockage pokemons;
    private Inventaire inventaire;

    public User(){
        this.id = -1;
        this.login = "";
        this.password = "";
        this.pokemons = new Stockage();
        this.inventaire = new Inventaire();
    }

    public User(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
        this.pokemons = new Stockage();
        this.inventaire = new Inventaire();
    }

    public User(long id, String login, String password, Stockage pokemons){
        this.id = id;
        this.login = login;
        this.password = password;
        this.pokemons = pokemons;
    }

    public User(long id, String login, String password, Stockage pokemons, Inventaire inventaire){
        this.id = id;
        this.login = login;
        this.password = password;
        this.pokemons = pokemons;
        this.inventaire = inventaire;
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

    public Stockage getPokemons() {
        return pokemons;
    }

    public void setPokemons(Stockage pokemons) {
        this.pokemons = pokemons;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
}
