package com.example.androidclient.modele;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Activite {
    private long id;
    private double duree;
    private int nombre;
    private String date;
    private Sport sport;
    private Utilisateur utilisateur;


    // Constructeurs


    public Activite(double duree, String date) {
        this.duree = duree;
        this.date = date;
    }

    public Activite(double duree, String date, Sport sport, Utilisateur utilisateur) {
        this.duree = duree;
        this.date = date;
        this.sport = sport;
        this.utilisateur = utilisateur;
    }

    public Activite(double duree, int nombre, String date, Sport sport, Utilisateur utilisateur) {
        this.duree = duree;
        this.nombre = nombre;
        this.date = date;
        this.sport = sport;
        this.utilisateur = utilisateur;
    }

    public Activite(double duree, int nombre, String date) {
        this.duree = duree;
        this.nombre = nombre;
        this.date = date;
    }


    // getter et setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "duree=" + duree +
                ", nombre=" + nombre +
                ", date='" + date + '\'' +
                '}';
    }
}
