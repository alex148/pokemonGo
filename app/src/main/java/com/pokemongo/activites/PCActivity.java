package com.pokemongo.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alex.pokemongo.R;
import com.pokemongo.controllers.PcAdapter;
import com.pokemongo.controllers.TeamAdapter;
import com.pokemongo.model.Attaque;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.SingletonUser;
import com.pokemongo.model.Type;
import com.pokemongo.model.User;

import java.util.ArrayList;
import java.util.List;

public class PCActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listViewPc;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc);
        ctx=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        List<Pokemon> pc = SingletonUser.getInstance().getUser().getPc().getPokemons();

        listViewPc = (ListView) findViewById( R.id.pc_list);
        listViewPc.setAdapter(new PcAdapter(ctx, R.layout.item_pc, pc));
        listViewPc.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon pokemon = (Pokemon) listViewPc.getAdapter().getItem(position);

                Intent intent = new Intent(PCActivity.this, PokemonDetailsActivity.class);
                intent.putExtra("pokemon", pokemon);
                startActivity(intent);

            }
        });

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
            Intent newIntent = new Intent(this,PokedexActivity.class);
            startActivity(newIntent);
        } else if (id == R.id.nav_inventory) {
            Intent newIntent = new Intent(this,InventoryActivity.class);
            startActivity(newIntent);
        }
        else if (id == R.id.nav_pc) {
           // on y est déja
        } else if (id == R.id.nav_settings) {
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
