package com.pokemongo.model;

/**
 * Created by Robin on 06/01/2016.
 */
public class Zone {
    private long ID_Zone;
    private float longitude;
    private float latitude;
    private long ID_Race;

    public Zone(){
    }
    public Zone(long ID_Zone, float longitude, float latitude, long ID_Race) {
        this.ID_Zone = ID_Zone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.ID_Race = ID_Race;
    }

    public long getID_Zone() {
        return ID_Zone;
    }

    public void setID_Zone(long ID_Zone) {
        this.ID_Zone = ID_Zone;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public long getID_Race() {
        return ID_Race;
    }

    public void setID_Race(long ID_Race) {
        this.ID_Race = ID_Race;
    }
}
