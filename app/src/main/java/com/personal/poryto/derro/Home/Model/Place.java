package com.personal.poryto.derro.Home.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Pory Sovann on 9/22/2018.
 */
@IgnoreExtraProperties
public class Place {

    private int id;
    private int rate;
    private String name;
    private String company;
    private double latitude;
    private double longitude;

    public Place() {
    }

    public Place(int id, int rate, String name, String company, double lat, double lng) {
        this.id = id;
        this.rate = rate;
        this.name = name;
        this.company = company;
        this.latitude = lat;
        this.longitude = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", rate=" + rate +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
