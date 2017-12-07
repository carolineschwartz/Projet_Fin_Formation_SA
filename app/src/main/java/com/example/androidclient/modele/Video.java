package com.example.androidclient.modele;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Video {

    private String nom;
    private String url;
    private Sport sport;

    public Video(String nom, String url, Sport sport) {
        this.nom = nom;
        this.url = url;
        this.sport = sport;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "Video{" +
                "nom='" + nom + '\'' +
                ", url='" + url + '\'' +
                ", sport=" + sport +
                '}';
    }
}
