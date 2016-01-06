package com.pokemongo.controllers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alex.pokemongo.R;
import com.pokemongo.model.Pokemon;

import java.util.List;

/**
 * Created by Thomas on 06/01/2016.
 */
public class PcAdapter extends ArrayAdapter<Pokemon> {

private int				resource;
private LayoutInflater inflater;
private Context context;
private List<Pokemon> pokemons;

public PcAdapter(Context context, int resource, List<Pokemon> pokemons) {

        super(context, resource, pokemons);
        this.context=context;
        this.resource=resource;
        this.inflater=LayoutInflater.from(context);
        this.pokemons=pokemons;
        }

@Override
public View getView ( int position, View convertView, ViewGroup parent ) {

		/* create a new view of my layout and inflate it in the row */
        convertView = (LinearLayout) inflater.inflate( resource, null );

        Pokemon pokemon = getItem( position );

        TextView pokeName = (TextView) convertView.findViewById(R.id.nomPokemon);
        pokeName.setText(pokemon.getRace().getNomRace());

        TextView pokeNiveau = (TextView) convertView.findViewById(R.id.niveauPokemon);
        pokeNiveau.setText("N°" + String.valueOf(pokemon.getNiveau()));

        ImageView pokeImage = (ImageView) convertView.findViewById(R.id.imagePokemon);
        String uri = "drawable/" + pokemon.getRace().getNomRace().toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        pokeImage.setImageDrawable(image);


        return convertView;

        }
        }
