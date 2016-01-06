package com.pokemongo.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.pokemongo.R;
import com.pokemongo.controllers.PokedexAdapter;
import com.pokemongo.dao.PokedexDao;
import com.pokemongo.model.Pokedex;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.Type;
import com.pokemongo.model.User;

import java.util.ArrayList;
import java.util.List;

public class PokedexActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listViewPokedex;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        ctx=this;
        
        PokedexDao pokedexDao = new PokedexDao(this);
        Pokedex pokedex=pokedexDao.getPokedex(new User(1, "test", "test"));

        /*   Liste provisoire de test   */

        /*Race bulbizarre = new Race();
        bulbizarre.setNomRace("Bulbizarre");
        bulbizarre.setType1(Type.Plante);
        bulbizarre.setType2(Type.Poison);
        bulbizarre.setDecouvert(true);
        bulbizarre.setPoids(12.3);
        bulbizarre.setTaille(1.5);
        bulbizarre.setId(1);
        bulbizarre.setDescription("bulbi");

        Race herbizarre = new Race();
        herbizarre.setNomRace("Herbizarre");
        herbizarre.setType1(Type.Plante);
        herbizarre.setType2(Type.Poison);
        herbizarre.setDecouvert(true);
        herbizarre.setPoids(13);
        herbizarre.setTaille(1.5);
        herbizarre.setId(2);
        herbizarre.setDescription("Le bulbe sur le dos de Herbizarre a éclos, dévoilant ainsi une fleur fermée. Plus il passe de temps au soleil, plus sa fleur se prépare à s'ouvrir, et quelques temps avant son évolution, il dégage une douce odeur fruitée. Attention à ses Tranch'herbes et à ses Fouets lianes.");

        Race florizarre = new Race();
        florizarre.setNomRace("Florizarre");
        florizarre.setType1(Type.Plante);
        florizarre.setType2(Type.Poison);
        florizarre.setDecouvert(true);
        florizarre.setPoids(12.3);
        florizarre.setTaille(1.5);
        florizarre.setId(3);
        florizarre.setDescription("Comme un panneau solaire , les pétales de la fleur de Florizarre transforment les rayons du soleil en énergie. Il est maître d'un troupeau de Herbizarre et de Bulbizarre, qu'il garde férocement. L'odeur se dégageant de sa fleur est exquise, mais mieux vaut ne pas s'approcher d'un Florizarre sauvage et de sa horde.");



        Race salameche = new Race();
        salameche.setNomRace("Salameche");
        salameche.setType1(Type.Feu);
        salameche.setType2(Type.None);
        salameche.setDecouvert(true);
        salameche.setPoids(11.3);
        salameche.setTaille(1.6);
        salameche.setId(4);
        salameche.setDescription("sala");

        Race carapuce = new Race();
        carapuce.setNomRace("Carapuce");
        carapuce.setType1(Type.Eau);
        carapuce.setType2(Type.None);
        carapuce.setDecouvert(true);
        carapuce.setPoids(10.3);
        carapuce.setTaille(1.7);
        carapuce.setId(5);
        carapuce.setDescription("cara");

        Race abo = new Race();
        abo.setNomRace("Abo");
        abo.setType1(Type.Poison);
        abo.setType2(Type.None);
        abo.setDecouvert(false);
        abo.setPoids(12.3);
        abo.setTaille(1.5);
        abo.setId(6);
        abo.setDescription("abo");

        List<Race> listPokemons= new ArrayList<Race>();
        listPokemons.add(bulbizarre);
        listPokemons.add(herbizarre);
        listPokemons.add(florizarre);
        listPokemons.add(carapuce);
        listPokemons.add(salameche);
        listPokemons.add(abo);*/

        /* fin liste provisoire  */

        listViewPokedex = ( ListView ) findViewById( R.id.pokedex_list);
        listViewPokedex.setAdapter(new PokedexAdapter(ctx, R.layout.item_pokedex, pokedex.getListRace()));
        listViewPokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Race pokemon = (Race) listViewPokedex.getAdapter().getItem( position );
               if (pokemon.isDecouvert()) {
                   Intent intent = new Intent(PokedexActivity.this, PokedexDetailsActivity.class);
                   intent.putExtra("pokemon", pokemon);
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getApplicationContext(), "Pokémon inconnu", Toast.LENGTH_SHORT).show();
               }
           }
       });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent newIntent = new Intent(this,MainActivity.class);
            startActivity(newIntent);
        }else if (id == R.id.nav_team) {
            Intent newIntent = new Intent(this,TeamActivity.class);
            startActivity(newIntent);
        } else if (id == R.id.nav_pokedex) {
            // on y est déja
        } else if (id == R.id.nav_inventory) {
            Intent newIntent = new Intent(this,InventoryActivity.class);
            startActivity(newIntent);
        }
        else if (id == R.id.nav_pc) {
            Intent newIntent = new Intent(this,PCActivity.class);
            startActivity(newIntent);
        }else if (id == R.id.nav_settings) {
            Intent newIntent = new Intent(this,SettingsActivity.class);
            startActivity(newIntent);
        }else if( id == R.id.deconnexion){
            Intent newIntent = new Intent(this,LoginActivity.class);
            startActivity(newIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
