package com.pokemongo.model;

/**
 * Created by Thomas on 07/01/2016.
 */
public class SingletonUser {

    private User userSingleton;
    /** Constructeur privé */
    private SingletonUser()
    {}

    /** Holder */
    private static class SingletonHolder
    {
        /** Instance unique non préinitialisée */
        private final static SingletonUser instance = new SingletonUser();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonUser getInstance()
    {
        return SingletonHolder.instance;
    }

    public void ajoutUser (User user){
        this.userSingleton=user;
    }

    public User getUser(){
        return userSingleton;
    }
}
