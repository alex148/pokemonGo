package com.pokemongo.model;

/**
 * Created by Alex on 04/01/2016.
 */
public class Effet {

    private long id;
    private String type;
    private String stat;

    public Effet(long id, String type, String stat) {
        this.id = id;
        this.type = type;
        this.stat = stat;
    }

    public Effet(){
        this.id = 0;
        this.type = "";
        this.stat = "";
    }
}
