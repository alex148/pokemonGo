package com.pokemongo.controllers;

import com.pokemongo.model.Attaque;
import com.pokemongo.model.CategorieAttaque;
import com.pokemongo.model.Pokemon;

/**
 * Created by Robin on 07/01/2016.
 */
public class CombatManager {
    public static int getDegat(Pokemon attaquant, Pokemon defenseur, Attaque attaque){
        if(attaque.getCategorie()== CategorieAttaque.Physique) {
            return (attaquant.getAttaque() - defenseur.getDefense()+attaque.getDegats());
        }else{
            return (attaquant.getAttaqueSpe() - defenseur.getDefenseSpe()+attaque.getDegats());
        }
    }
}
