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
            boolean degatPositif=(attaquant.getAttaque() - defenseur.getDefense()+attaque.getDegats())>0;
            return degatPositif?attaquant.getAttaque() - defenseur.getDefense()+attaque.getDegats():0;
        }else{
            boolean degatPositif=(attaquant.getAttaqueSpe() - defenseur.getDefenseSpe()+attaque.getDegats())>0;
            return degatPositif?attaquant.getAttaqueSpe() - defenseur.getDefenseSpe()+attaque.getDegats():0;
        }
    }
}
