package com.pokemongo.activites;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.pokemongo.R;
import com.pokemongo.dao.InventaireDao;
import com.pokemongo.dao.ObjetDao;
import com.pokemongo.dao.PokemonDao;
import com.pokemongo.dao.RaceDao;
import com.pokemongo.dao.StockageDao;
import com.pokemongo.dao.UserDao;
import com.pokemongo.model.Inventaire;
import com.pokemongo.model.InventaireLiaison;
import com.pokemongo.model.Objet;
import com.pokemongo.model.Objets;
import com.pokemongo.model.Pokemon;
import com.pokemongo.model.Race;
import com.pokemongo.model.Stockage;
import com.pokemongo.model.StockageLiaison;
import com.pokemongo.model.Type;
import com.pokemongo.model.TypeStockage;
import com.pokemongo.model.User;

public class CreerCompteActivity extends AppCompatActivity {

    private EditText mPassword;
    private EditText mPassword2;
    private EditText mLogin;
    private UserDao userDao;
    private ObjetDao objetDao;
    private InventaireDao inventaireDao;
    private PokemonDao pokemonDao;
    private RaceDao raceDao;
    private StockageDao stockageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);
        setupActionBar();

        mPassword = (EditText) findViewById(R.id.passwordCreerC);
        mPassword2 = (EditText) findViewById(R.id.passwordCreerCVerif);
        mLogin = (EditText) findViewById(R.id.login);

        Button creerCompte = (Button) findViewById(R.id.creer_compte_buttonCr);
        creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPassword.getText().length() >= 4){
                    if(mPassword.getText().toString().equals(mPassword2.getText().toString())){
                        User user = CreerCompte(mLogin.getText().toString(),mPassword.getText().toString());
                        Intent newIntent = new Intent(CreerCompteActivity.this,MainActivity.class);
                        if(user != null) {
                            newIntent.putExtra("user", user);
                            startActivity(newIntent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Un utilisateur avec ce login existe déjà", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Les deux mots de passes sont différents", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Le mot de passe doit faire au moins 4 caractères", Toast.LENGTH_SHORT).show();
                }
            }
        });

        userDao = new UserDao(this);
        objetDao = new ObjetDao(this);
        inventaireDao = new InventaireDao(this);
        pokemonDao = new PokemonDao(this);
        raceDao = new RaceDao(this);
        stockageDao = new StockageDao(this);
    }

    public User CreerCompte(String login, String password){
        try{
            User user = new User(login,password);
            long id = userDao.insert(user);
            if(id == -1)
                return null;
            user.setId(id);
            Inventaire inventaire = new Inventaire();
            Objets objets = new Objets();
            Objet objet = this.objetDao.getById(2); //pokeball
            objets.setObjet(objet);
            objets.setQuantite(5);
            inventaire.addItem(objets);

            InventaireLiaison invLiaison = new InventaireLiaison();
            invLiaison.setObjet(objet);
            invLiaison.setQuantite(5);
            invLiaison.setUser(user);
            long invId = this.inventaireDao.insert(invLiaison);
            if(invId != -1)
                user.getInventaire().setId(invId);

            Race race = raceDao.getRaceByID(0);
            Pokemon p = new Pokemon(-1, 30, 10, 25, 10, 10, 30, 1, 0, race);
            Stockage equipe = this.creerStockageEtPokemon(user,p,TypeStockage.EQUIPE);
            user.setEquipe(equipe);

            //PC à null
            user.setPc(null);

            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       return null;
    }

    public Stockage creerStockageEtPokemon(User user, Pokemon pokemon, TypeStockage typeStockage){

        long pokId = this.pokemonDao.insert(pokemon);
        if (pokId != -1)
            pokemon.setId(pokId);

        Stockage stockage = new Stockage();
        stockage.setType(typeStockage);
        stockage.addPokemon(pokemon);

        StockageLiaison stLiaison = new StockageLiaison();
        stLiaison.setUser(user);
        stLiaison.setType(typeStockage.toString());
        stLiaison.setPokemon(pokemon);
        this.stockageDao.insert(stLiaison);
        return stockage;

    }



    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
