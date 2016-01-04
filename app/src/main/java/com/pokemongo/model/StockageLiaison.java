package com.pokemongo.model;

/**
 * Created by Alex on 04/01/2016.
 */
public class StockageLiaison {
    private User user;
    private Pokemon pokemon;
    private String type;

    public StockageLiaison(){
        this.user = new User();
        this.pokemon = new Pokemon();
        this.type = "";
    }

    public StockageLiaison(User user, Pokemon pokemon, String type) {
        this.user = user;
        this.pokemon = pokemon;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
