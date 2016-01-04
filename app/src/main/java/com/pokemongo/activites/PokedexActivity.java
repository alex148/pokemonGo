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
import android.widget.ListView;

import com.example.alex.pokemongo.R;
import com.pokemongo.controllers.PokedexAdapter;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.Type;

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

        /*   Liste provisoire de test   */

        Race bulbizarre = new Race();
        bulbizarre.setNomRace("Bulbizarre");
        bulbizarre.setDescription("Plante");
        bulbizarre.setDecouvert(true);

        Race salameche = new Race();
        salameche.setNomRace("Salameche");
        salameche.setDescription("Feu");
        salameche.setDecouvert(true);

        Race carapuce = new Race();
        carapuce.setNomRace("Carapuce");
        carapuce.setDescription("Eau");
        carapuce.setDecouvert(true);

        Race abo = new Race();
        abo.setNomRace("Abo");
        abo.setDescription("Poison");
        abo.setDecouvert(false);

        List<Race> listPokemons= new ArrayList<Race>();
        listPokemons.add(bulbizarre);
        listPokemons.add(carapuce);
        listPokemons.add(salameche);
        listPokemons.add(abo);

        /* fin liste provisoire  */
        listViewPokedex = ( ListView ) findViewById( R.id.pokedex_list);
        listViewPokedex.setAdapter(new PokedexAdapter(ctx, R.layout.item_pokedex, listPokemons));


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
        } else if (id == R.id.nav_settings) {
            Intent newIntent = new Intent(this,SettingsActivity.class);
            startActivity(newIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
