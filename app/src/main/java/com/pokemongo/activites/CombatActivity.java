package com.pokemongo.activites;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.pokemongo.R;
import com.pokemongo.controllers.CombatManager;
import com.pokemongo.dao.PokemonDao;
import com.pokemongo.model.Attaque;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.SingletonUser;
import com.pokemongo.model.User;

import java.util.List;
import java.util.Random;

public class CombatActivity extends AppCompatActivity {

    private boolean tourDuJoueur;
    private boolean tourJoueurFini;
    private boolean tourIAFini;
    private boolean sortir;
    private Pokemon pokemon;
    private Pokemon IA;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.red);
        mediaPlayer.start();
        setContentView(R.layout.activity_combat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        PokemonDao pokemonDao = new PokemonDao(this);
        pokemonDao.getById(0);
        IA = (Pokemon) getIntent().getExtras().get("pokemon");
        sortir = false;
        User u = SingletonUser.getInstance().getUser();
        pokemon = u.getEquipe().getPokemons().get(0);

        ImageView imageIA = (ImageView) findViewById(R.id.imageIA);
        String uriIA = "drawable/" + IA.getRace().getNomRace().toLowerCase();
        int imageIAResource = this.getResources().getIdentifier(uriIA, null, this.getPackageName());
        Drawable IAimage = this.getResources().getDrawable(imageIAResource);
        imageIA.setImageDrawable(IAimage);

        TextView nomIA = (TextView) findViewById(R.id.nomIA);
        nomIA.setText(IA.getRace().getNomRace() + " N." + IA.getNiveau());

        TextView nomPokemon = (TextView) findViewById(R.id.nomPokemon);
        nomPokemon.setText(pokemon.getRace().getNomRace()+" N."+pokemon.getNiveau());

        TextView pv_num_IA = (TextView) findViewById(R.id.pv_num_IA);
        pv_num_IA.setText(IA.getPv() + "/" + IA.getPv());

        TextView pv_num_pokemon = (TextView) findViewById(R.id.pv_num_pokemon);
        pv_num_pokemon.setText(pokemon.getPv()+"/"+pokemon.getPv());

        ProgressBar vieIA = (ProgressBar) findViewById(R.id.VieIA);
        vieIA.setMax(IA.getPv());
        vieIA.setProgress(IA.getPv());

        ImageView imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        String uri = "drawable/" + pokemon.getRace().getNomRace().toLowerCase();
        int imagePokemonResource = this.getResources().getIdentifier(uri, null, this.getPackageName());
        Drawable image = this.getResources().getDrawable(imagePokemonResource);
        imagePokemon.setImageDrawable(image);



        ProgressBar viePokemon = (ProgressBar) findViewById(R.id.ViePokemon);
        viePokemon.setMax(pokemon.getPv());
        viePokemon.setProgress(pokemon.getPv());

        disableSuite();
        enableAttaque();

        final List<Attaque> attaques = pokemon.getAttaques();


        if (attaques.size() > 0) {
            final Button attaque1 = (Button) findViewById(R.id.Attaque1);
            attaque1.setText(attaques.get(0).getNom());
            attaque1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    nouveauTour(0);
                }
            });
        }
        if (attaques.size() > 1) {
            Button attaque1 = (Button) findViewById(R.id.Attaque2);
            attaque1.setText(attaques.get(1).getNom());
            attaque1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    nouveauTour(1);
                }
            });
        }
        if (attaques.size() > 2) {
            Button attaque1 = (Button) findViewById(R.id.Attaque3);
            attaque1.setText(attaques.get(2).getNom());
            attaque1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    nouveauTour(2);
                }
            });
        }
        if (attaques.size() > 3) {
            Button attaque1 = (Button) findViewById(R.id.Attaque4);
            attaque1.setText(attaques.get(3).getNom());
            attaque1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    nouveauTour(3);
                }
            });
        }
        ImageButton suite = (ImageButton) findViewById(R.id.suite);
        suite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Random rand = new Random();
                List<Attaque> listAttaques = IA.getAttaques();
                tourSuivant(-1);
            }
        });
    }

    private void nouveauTour(int choix) {
        tourDuJoueur = IA.getVitesse() <= pokemon.getVitesse();
        tourJoueurFini = false;
        tourIAFini = false;
        disableAttaque();
        enableSuite();
        tourSuivant(choix);
    }

    private void tourSuivant(int choix) {
        if (sortir) {
            this.mediaPlayer.stop();
            finish();
        } else if (pokemonKO()) {
        } else if (tourJoueurFini && tourIAFini) {
            afficheMessage("Choisissez une attaque !");
            tourIAFini = false;
            tourJoueurFini = false;
            enableAttaque();
            disableSuite();
        } else {
            if (tourDuJoueur) {
                tourJoueur(choix);
                tourDuJoueur = false;
            } else {
                tourIA();
                tourDuJoueur = true;
            }
        }

    }

    private void tourJoueur(int choix) {
        int degat = CombatManager.getDegat(pokemon, IA, pokemon.getAttaques().get(choix));
        ProgressBar vieIA = (ProgressBar) findViewById(R.id.VieIA);
        vieIA.setProgress(vieIA.getProgress() - degat);
        afficheMessageLanceAttaque(pokemon, pokemon.getAttaques().get(choix), IA, degat);

        TextView pv_num_IA = (TextView) findViewById(R.id.pv_num_IA);
        pv_num_IA.setText(vieIA.getProgress() + "/" + IA.getPv());

        tourJoueurFini = true;
    }

    private void tourIA() {
        Random rand = new Random();
        List<Attaque> listAttaques = IA.getAttaques();
        int choix = rand.nextInt(listAttaques.size());
        int degat = CombatManager.getDegat(IA, pokemon, listAttaques.get(choix));
        ProgressBar viePokemon = (ProgressBar) findViewById(R.id.ViePokemon);
        viePokemon.setProgress(viePokemon.getProgress() - degat);
        afficheMessagePrendAttaque(IA, listAttaques.get(choix), pokemon, degat);

        TextView pv_num_pokemon = (TextView) findViewById(R.id.pv_num_pokemon);
        pv_num_pokemon.setText(viePokemon.getProgress() + "/" + pokemon.getPv());

        tourIAFini = true;
    }

    private void disableAttaque() {
        Button attaque1 = (Button) findViewById(R.id.Attaque1);
        attaque1.setEnabled(false);
        Button attaque2 = (Button) findViewById(R.id.Attaque2);
        attaque2.setEnabled(false);
        Button attaque3 = (Button) findViewById(R.id.Attaque3);
        attaque3.setEnabled(false);
        Button attaque4 = (Button) findViewById(R.id.Attaque4);
        attaque4.setEnabled(false);
    }

    private void enableAttaque() {
        Button attaque1 = (Button) findViewById(R.id.Attaque1);
        attaque1.setEnabled(true);
        Button attaque2 = (Button) findViewById(R.id.Attaque2);
        attaque2.setEnabled(true);
        Button attaque3 = (Button) findViewById(R.id.Attaque3);
        attaque3.setEnabled(true);
        Button attaque4 = (Button) findViewById(R.id.Attaque4);
        attaque4.setEnabled(true);
    }

    private void disableSuite() {
        ImageButton suite = (ImageButton) findViewById(R.id.suite);
        suite.setEnabled(false);
    }

    private void enableSuite() {
        ImageButton suite = (ImageButton) findViewById(R.id.suite);
        suite.setEnabled(true);
    }

    private void afficheMessageLanceAttaque(Pokemon attaquant, Attaque attaque, Pokemon defenseur, int degats) {
        afficheMessage(attaquant.getRace().getNomRace() + " lance " + attaque.getNom()
                + "!\n" + defenseur.getRace().getNomRace() + " ennemi perd " + degats + " PV!");
    }

    private void afficheMessagePrendAttaque(Pokemon attaquant, Attaque attaque, Pokemon defenseur, int degats) {
        afficheMessage(attaquant.getRace().getNomRace() + " ennemi lance " + attaque.getNom()
                + "!\n" + defenseur.getRace().getNomRace() + " perd " + degats + " PV!");
    }

    private void afficheMessage(String message) {
        TextView messageBox = (TextView) findViewById(R.id.infoCombat);
        messageBox.setText(message);
    }

    private boolean pokemonKO() {
        boolean KO = false;
        ProgressBar viePokemon = (ProgressBar) findViewById(R.id.ViePokemon);
        ProgressBar vieIA = (ProgressBar) findViewById(R.id.VieIA);
        if (viePokemon.getProgress() <= 0) {
            afficheMessage("Votre pokemon est KO !");
            sortir = true;
            KO = true;
        } else if (vieIA.getProgress() <= 0) {
            afficheMessage("Vous avez gagné le combat !");
            sortir = true;
            KO = true;
        }
        return KO;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.combat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_capture) {
            Toast.makeText(getApplicationContext(), "Fonction à venir", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_potion) {
            Toast.makeText(getApplicationContext(), "Fonction à venir", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_fuite) {
            mediaPlayer.stop();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            // on ne fuit pas avec le bouton back
    }

}
