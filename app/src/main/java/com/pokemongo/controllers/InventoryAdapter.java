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
import com.pokemongo.model.Objets;
import com.pokemongo.model.Race;
import com.pokemongo.model.Type;

import java.util.List;

/**
 * Created by Thomas on 06/01/2016.
 */
public class InventoryAdapter extends ArrayAdapter<Objets> {

    private int				resource;
    private LayoutInflater inflater;
    private Context context;
    private List<Objets> objets;

    public InventoryAdapter(Context context, int resource, List<Objets> objets) {

        super(context, resource, objets);
        this.context=context;
        this.resource=resource;
        this.inflater=LayoutInflater.from(context);
        this.objets=objets;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

		/* create a new view of my layout and inflate it in the row */
        convertView = (LinearLayout) inflater.inflate( resource, null );

        Objets objet = getItem( position );

        TextView nomObjet = (TextView) convertView.findViewById(R.id.nomObjet);
        nomObjet.setText(objet.getObjet().getNom());

        TextView nbObjet = (TextView) convertView.findViewById(R.id.nbObjet);
        nbObjet.setText("x"+objet.getQuantite());

        ImageView objetImage = (ImageView) convertView.findViewById(R.id.imageObjet);
        String uri = "drawable/" + objet.getObjet().getNom().toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        objetImage.setImageDrawable(image);


        return convertView;

    }
}
