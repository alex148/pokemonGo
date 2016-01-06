package com.pokemongo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class Stockage implements Serializable {

    private List<Pokemon> pokemons;
    private TypeStockage type;

    public Stockage(TypeStockage type, List<Pokemon> pokemons, User user) {
        this.type = type;
        this.pokemons = pokemons;
    }

    public Stockage(){
        this.type = null;
        this.pokemons = new ArrayList<Pokemon>();
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public TypeStockage getType() {
        return type;
    }

    public void setType(TypeStockage type) {
        this.type = type;
    }

    public void addPokemon(Pokemon p){
        this.pokemons.add(p);
    }
}
