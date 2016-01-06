package com.pokemongo.activites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.alex.pokemongo.R;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.Type;

public class PokedexDetailsActivity extends AppCompatActivity {

    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_details);
        setupActionBar();

        Race pokemon = (Race)getIntent().getExtras().get("pokemon");

        ImageView imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        String uri = "drawable/" + pokemon.getNomRace().toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        imagePokemon.setImageDrawable(image);

        TextView nomPokemon = (TextView) findViewById(R.id.nomPokemon);
        nomPokemon.setText(pokemon.getNomRace());
        TextView type1 = (TextView) findViewById(R.id.type1Pokemon);
        type1.setText(pokemon.getType1().toString());
        if (pokemon.getType2()!= Type.None){
        TextView type2 = (TextView) findViewById(R.id.type2Pokemon);
        type2.setText(pokemon.getType2().toString());
        }
        TextView poids = (TextView) findViewById(R.id.poids);
        poids.setText("Poids :" + pokemon.getPoids() +"kg");
        TextView taille = (TextView) findViewById(R.id.taille);
        taille.setText("Taille : "+ pokemon.getTaille() +"m");
        TextView description = (TextView) findViewById(R.id.descriptionPokemon);
        description.setText(pokemon.getDescription());


    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
