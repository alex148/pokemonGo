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
import com.pokemongo.model.Race;

import java.util.List;

/**
 * Created by Thomas on 18/12/2015.
 */
public class PokedexAdapter extends ArrayAdapter<Race> {

    private int				resource;
    private LayoutInflater inflater;
    private Context 		context;
    private List<Race> listPokemons;

    public PokedexAdapter(Context context, int resource, List<Race> pokemons) {

        super(context, resource, pokemons);
        this.context=context;
        this.resource=resource;
        this.inflater=LayoutInflater.from(context);
        this.listPokemons=pokemons;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

		/* create a new view of my layout and inflate it in the row */
        convertView = (LinearLayout) inflater.inflate( resource, null );

        Race pokemon = getItem( position );

        if(pokemon.isDecouvert()) {
            TextView pokeName = (TextView) convertView.findViewById(R.id.nomPokemon);
            pokeName.setText(pokemon.getNomRace());

            TextView pokeType = (TextView) convertView.findViewById(R.id.typePokemon);
            pokeType.setText(pokemon.getDescription());

            ImageView pokeImage = (ImageView) convertView.findViewById(R.id.imagePokemon);
            String uri = "drawable/" + pokemon.getNomRace().toLowerCase();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable image = context.getResources().getDrawable(imageResource);
            pokeImage.setImageDrawable(image);
        }
        else {
            TextView pokeName = (TextView) convertView.findViewById(R.id.nomPokemon);
            pokeName.setText("???");

            TextView pokeType = (TextView) convertView.findViewById(R.id.typePokemon);
            pokeType.setText("????");
        }

        return convertView;

    }
}
