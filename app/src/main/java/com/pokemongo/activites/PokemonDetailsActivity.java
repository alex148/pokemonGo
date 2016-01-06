package com.pokemongo.activites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.pokemongo.R;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.Type;

public class PokemonDetailsActivity extends AppCompatActivity {

    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        setupActionBar();

        Pokemon pokemon = (Pokemon)getIntent().getExtras().get("pokemon");

        ImageView imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        String uri = "drawable/" + pokemon.getRace().getNomRace().toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        imagePokemon.setImageDrawable(image);

        TextView nomPokemon = (TextView) findViewById(R.id.nomPokemon);
        nomPokemon.setText(pokemon.getRace().getNomRace());

        TextView type1 = (TextView) findViewById(R.id.type1Pokemon);
        type1.setText(pokemon.getRace().getType1().toString());
        if (pokemon.getRace().getType2()!= Type.None){
            TextView type2 = (TextView) findViewById(R.id.type2Pokemon);
            type2.setText(pokemon.getRace().getType2().toString());
        }

        TextView niveau = (TextView) findViewById(R.id.niveauPokemon);
        niveau.setText("Niveau : " + pokemon.getNiveau());

        TextView exp = (TextView) findViewById(R.id.expPokemon);
        exp.setText("EXP : "+ pokemon.getExperience());

        TextView pv = (TextView) findViewById(R.id.pv);
        pv.setText("PV : "+pokemon.getPv());

        TextView attaque = (TextView) findViewById(R.id.attaque);
        attaque.setText("Attaque : "+pokemon.getAttaque());

        TextView attaqueSpe = (TextView) findViewById(R.id.attaqueSpe);
        attaqueSpe.setText("Attaque Spé : "+pokemon.getAttaqueSpe());

        TextView vitesse = (TextView) findViewById(R.id.vitesse);
        vitesse.setText("Vitesse : "+pokemon.getVitesse());

        TextView defense = (TextView) findViewById(R.id.defense);
        defense.setText("Défense : "+pokemon.getDefense());

        TextView defenseSpe = (TextView) findViewById(R.id.defenseSpe);
        defenseSpe.setText("Défense Spé : "+pokemon.getDefenseSpe());

        int nbAttaques = pokemon.getAttaques().size();

        if (nbAttaques>0) {
            TextView nomAttaque1 = (TextView) findViewById(R.id.nomAttaque1);
            nomAttaque1.setText(pokemon.getAttaques().get(0).getNom());

            TextView typeAttaque1 = (TextView) findViewById(R.id.typeAttaque1);
            typeAttaque1.setText("Type : " + pokemon.getAttaques().get(0).getType());

            TextView descAttaque1 = (TextView) findViewById(R.id.descriptionAttaque1);
            descAttaque1.setText(pokemon.getAttaques().get(0).getDescription());
        }
        if (nbAttaques>1) {
            TextView nomAttaque2 = (TextView) findViewById(R.id.nomAttaque2);
            nomAttaque2.setText(pokemon.getAttaques().get(1).getNom());

            TextView typeAttaque2 = (TextView) findViewById(R.id.typeAttaque2);
            typeAttaque2.setText("Type : " + pokemon.getAttaques().get(1).getType());

            TextView descAttaque2 = (TextView) findViewById(R.id.descriptionAttaque2);
            descAttaque2.setText(pokemon.getAttaques().get(1).getDescription());
        }
        if (nbAttaques>2) {
            TextView nomAttaque3 = (TextView) findViewById(R.id.nomAttaque3);
            nomAttaque3.setText(pokemon.getAttaques().get(2).getNom());

            TextView typeAttaque3 = (TextView) findViewById(R.id.typeAttaque3);
            typeAttaque3.setText("Type : " + pokemon.getAttaques().get(2).getType());

            TextView descAttaque3 = (TextView) findViewById(R.id.descriptionAttaque3);
            descAttaque3.setText(pokemon.getAttaques().get(2).getDescription());
        }
        if (nbAttaques>3) {
            TextView nomAttaque4 = (TextView) findViewById(R.id.nomAttaque4);
            nomAttaque4.setText(pokemon.getAttaques().get(3).getNom());

            TextView typeAttaque4 = (TextView) findViewById(R.id.typeAttaque4);
            typeAttaque4.setText("Type : " + pokemon.getAttaques().get(3).getType());

            TextView descAttaque4 = (TextView) findViewById(R.id.descriptionAttaque4);
            descAttaque4.setText(pokemon.getAttaques().get(3).getDescription());
        }

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