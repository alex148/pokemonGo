package com.pokemongo.dao;

/**
 * Created by Alex on 18/12/2015.
 */
public interface Crud<T> {

    public long insert(T object);
    public void delete(T object);
    public void update(T object);
    public T get(T object);

}
