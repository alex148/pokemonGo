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
import com.pokemongo.model.User;

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

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    LatLng latLng;
    SupportMapFragment mFragment;
    Marker mCurrLocation;
    private GoogleMap mMap;
    LocationManager locationManager;
    String provider;
    LatLng myPosition;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mMap = fm.getMap();
            mMap.setMyLocationEnabled(true);
            //mMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) this
                    .getSystemService(LOCATION_SERVICE);

            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, true);
            //mMap.animateCamera(CameraUpdateFactory.zoomBy(13));

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
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
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

                    if (location != null) {
                        this.addPokemons(location);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14.0f));
                        onLocationChanged(location);
                    }
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        }

    public void addPokemons(Location location){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("pikachu", "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100, false);

        this.pikatchu = mMap.addMarker(new MarkerOptions()
                .position(PIKATCHU)
                .title("pikachu")
                .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));
    }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Toast.makeText(this,"onConnected",Toast.LENGTH_SHORT).show();
                Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    //place marker at current position
                    mMap.clear();
                    latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("Current Position");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    mCurrLocation = mMap.addMarker(markerOptions);
                }

                mLocationRequest = new LocationRequest();
                mLocationRequest.setInterval(5000); //5 seconds
                mLocationRequest.setFastestInterval(3000); //3 seconds
                mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
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
              //  Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();
                //mMap.animateCamera(CameraUpdateFactory.zoomBy(13));
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
