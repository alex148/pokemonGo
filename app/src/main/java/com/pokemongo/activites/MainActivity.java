package com.pokemongo.activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alex.pokemongo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pokemongo.dao.AttaqueDao;
import com.pokemongo.dao.RaceDao;
import com.pokemongo.model.Attaque;
import com.pokemongo.model.MarkerPokemon;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.User;
import com.pokemongo.model.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        OnMapReadyCallback

{

    // The minimum distance to change Updates in meters
    private long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters

    // The minimum time between updates in milliseconds
    private long MIN_TIME_BW_UPDATES = 1;

    private static final LatLng PIKATCHU = new LatLng(45.783713, 4.868944);
    private Marker pikatchu;

    private GoogleMap mMap;
    LocationManager locationManager;

    private RaceDao raceDao;
    private AttaqueDao attaqueDao;
    private List<MarkerPokemon> markers;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mMap = fm.getMap();
            mMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) this
                    .getSystemService(LOCATION_SERVICE);


            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                boolean canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: ask permission

                        return;
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            this.MIN_TIME_BW_UPDATES,
                            this.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                }
                if (locationManager != null) {
                    Location location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(location == null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                    if(location == null){
                        location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    }
                    if (location != null) {
                        this.addPokemons();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14.0f));
                        onLocationChanged(location);
                    }
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        }

    public void addPokemons(){
        try{
            List<Attaque> attaques = new ArrayList<Attaque>();
            List<Race> races = this.raceDao.getAll();
            Bitmap imageBitmap;
            Bitmap resizedBitmap;
            Random rand = new Random();
            int randomNum = 0;

            attaques = this.attaqueDao.getAll();
            int minAttaque = 0;
            int maxAttaque = attaques.size()-1;
            int attaque = 0;
            for (Race r: races) {
                if(r.getZones() != null && !r.getZones().isEmpty()){
                    for (Zone zone: r.getZones()) {
                        Pokemon p = new Pokemon();
                        p.setId(-1);
                        p.setPv(rand.nextInt(300 - 30 + 1) + 30);
                        p.setDefense(rand.nextInt(200 - 30 + 1) + 30);
                        p.setDefenseSpe(rand.nextInt(210 - 40 + 1) + 40);
                        p.setAttaque(rand.nextInt(210 - 40 + 1) + 40);
                        p.setAttaqueSpe(rand.nextInt(210 - 40 + 1) + 40);
                        p.setExperience(0);
                        p.setNiveau(rand.nextInt(30 - 1 + 1) + 1);
                        p.setRace(r);
                        p.setVitesse(rand.nextInt(210 - 40 + 1) + 40);
                        // attaque = rand.nextInt(maxAttaque-minAttaque + 1) + minAttaque;   //todo
                        // p.setAttaques(attaques.get(attaque));

                        imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(r.getNomRace().toLowerCase(), "drawable", getPackageName()));
                        resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100, false);
                        Marker m = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(zone.getLongitude(), zone.getLatitude()))
                                .title(r.getNomRace())
                                .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));
                        this.markers.add(new MarkerPokemon(m,p));

                    }
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.raceDao = new RaceDao(this);
            this.attaqueDao = new AttaqueDao(this);
            this.markers = new ArrayList<MarkerPokemon>();
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


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
            // on y est déja
        }else if (id == R.id.nav_team) {
            Intent newIntent = new Intent(this,TeamActivity.class);
            startActivity(newIntent);
        } else if (id == R.id.nav_pokedex) {
            Intent newIntent = new Intent(this,PokedexActivity.class);
            startActivity(newIntent);
        } else if (id == R.id.nav_inventory) {
            Intent newIntent = new Intent(this,InventoryActivity.class);
            startActivity(newIntent);
        }else if (id == R.id.nav_pc) {
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
            @Override
            public void onConnected(Bundle bundle) {

            }

            @Override
            public void onConnectionSuspended(int i) {
                Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {
                Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLocationChanged(Location location){

                Location pikaLoca = new Location(location);
                pikaLoca.setLatitude(pikatchu.getPosition().latitude);
                pikaLoca.setLongitude(pikatchu.getPosition().longitude);
                float f = location.distanceTo(pikaLoca);
                if(f<50){
                    Toast.makeText(this,"Entrée dans la zone de pikachu !! ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Distance to pikachu :  "+String.valueOf(f)+" m",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

}
