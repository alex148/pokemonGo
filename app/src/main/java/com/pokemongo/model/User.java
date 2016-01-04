package com.pokemongo.model;

/**
 * Created by Alex on 18/12/2015.
 */
public class User {

    private long id;
    private String login;
    private String password;
    private Stockage pokemons;

    public User(){
        this.id = -1;
        this.login = "";
        this.password = "";
    }

    public User(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
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
}
