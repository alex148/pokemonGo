package com.pokemongo.model;

import java.util.List;

/**
 * Created by Robin on 05/01/2016.
 */
public class Pokedex {
    private List<Race> ListRace;

    public Pokedex (List<Race> ListRace){
        this.ListRace=ListRace;
    }

    public List<Race> getListRace() {
        return ListRace;
    }

    public void setListRace(List<Race> listRace) {
        ListRace = listRace;
    }
}
