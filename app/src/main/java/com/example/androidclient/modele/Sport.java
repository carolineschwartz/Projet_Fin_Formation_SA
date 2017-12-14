package com.example.androidclient.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Sport {

    private Long id;
    private String nom;
    private String iconeUrl;
    private int calorie;


    public Sport(Long id,String nom) {
        this.id=id;
        this.nom = nom;
    }

    public Sport(String nom, String iconeUrl, int calorie) {
        this.nom = nom;
        this.iconeUrl = iconeUrl;
        this.calorie = calorie;
    }


    // getter et setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "nom='" + nom + '\'' +
                ", iconeUrl='" + iconeUrl + '\'' +
                ", calorie=" + calorie +
                '}';
    }
}
