package com.example.androidclient.modele;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Activite {

    private double duree;
    private int nombre;
    private String date;


    // Constructeurs

    public Activite(double duree, int nombre, String date) {
        this.duree = duree;
        this.nombre = nombre;
        this.date = date;
    }


    // getter et setter


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
