package com.pokemongo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 04/01/2016.
 */
public class Inventaire {

    private long id;
    private List<Item> items;

    public Inventaire(long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public Inventaire(){
        this.id = 0;
        this.items = new ArrayList<Item>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
