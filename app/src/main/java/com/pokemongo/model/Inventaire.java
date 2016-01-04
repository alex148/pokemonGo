package com.pokemongo.model;

import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class Inventaire {

    private long id;
    private List<Objet> objets;

    public Inventaire(long id, List<Objet> objets) {
        this.id = id;
        this.objets = objets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void setObjets(List<Objet> objets) {
        this.objets = objets;
    }
}
