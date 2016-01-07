package com.pokemongo.model;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by Alex on 07/01/2016.
 */
public class MarkerPokemon {
    private Marker marker;
    private Pokemon pokemon;

    public MarkerPokemon(Marker marker, Pokemon pokemon){
        this.marker = marker;
        this.pokemon = pokemon;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
