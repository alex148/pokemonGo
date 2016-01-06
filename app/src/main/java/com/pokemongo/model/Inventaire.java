package com.pokemongo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class Inventaire implements Serializable {

    private long id;
    private List<Objets> items;

    public Inventaire(long id, List<Objets> items) {
        this.id = id;
        this.items = items;
    }

    public Inventaire(){
        this.id = 0;
        this.items = new ArrayList<Objets>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Objets> getItems() {
        return items;
    }

    public void setItems(List<Objets> items) {
        this.items = items;
    }
}
