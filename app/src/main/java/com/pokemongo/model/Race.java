package com.pokemongo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Thomas on 04/01/2016.
 */
public class Race implements Parcelable {

    private long id;
    private String nomRace;
    private String description;
    private double poids;
    private double taille;
    private Type type1;
    private Type type2;
    private boolean decouvert;

    public Race(long id, String nomRace, String description, double poids, double taille,Type type1,Type type2,Boolean decouvert) {
        this.id = id;
        this.nomRace = nomRace;
        this.description = description;
        this.poids = poids;
        this.taille = taille;
        this.type1=type1;
        this.type2=type2;
        this.decouvert=decouvert;
    }
    public Race() {

    }

    public Race(Parcel in){

        this.id = in.readLong();
        this.nomRace = in.readString();
        this.description = in.readString();
        this.poids = in.readDouble();
        this.taille = in.readDouble();
        this.type1 = Type.valueOf(in.readString());
        this.type2 = Type.valueOf(in.readString());
        boolean[] boolarray = new boolean[1];  // impossible de passer un boolean tout seul -> passe un array
        in.readBooleanArray(boolarray);
        this.decouvert=boolarray[0];
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(id);
        dest.writeString(nomRace);
        dest.writeString(description);
        dest.writeDouble(poids);
        dest.writeDouble(taille);
        dest.writeString(type1.toString());
        dest.writeString(type2.toString());
        dest.writeBooleanArray(new boolean[]{decouvert});
    }

    public static final Parcelable.Creator<Race> CREATOR = new Parcelable.Creator<Race>()
    {
        @Override
        public Race createFromParcel(Parcel source)
        {
            return new Race(source);
        }

        @Override
        public Race[] newArray(int size)
        {
            return new Race[size];
        }
    };
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomRace() {
        return nomRace;
    }

    public void setNomRace(String nomRace) {
        this.nomRace = nomRace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public boolean isDecouvert() {
        return decouvert;
    }

    public void setDecouvert(boolean decouvert) {
        this.decouvert = decouvert;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public static Parcelable.Creator<Race> getCreator()
    {
        return CREATOR;
    }
}
