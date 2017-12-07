package com.example.androidclient.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Sport {

    private String nom;
    private String iconeUrl;
    private List<Video> videos = new ArrayList<>();

    public Sport(String nom, String iconeUrl, List<Video> videos) {
        this.nom = nom;
        this.iconeUrl = iconeUrl;
        this.videos = videos;
    }


    // getter et setter


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIconeUrl() {
        return iconeUrl;
    }

    public void setIconeUrl(String iconeUrl) {
        this.iconeUrl = iconeUrl;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    @Override
    public String toString() {
        return "Sport{" +
                "nom='" + nom + '\'' +
                ", iconeUrl='" + iconeUrl + '\'' +
                ", videos=" + videos +
                '}';
    }
}
