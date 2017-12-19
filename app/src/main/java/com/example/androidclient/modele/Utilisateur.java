package com.example.androidclient.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schwartz on 07/12/2017.
 */

public class Utilisateur {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String passWord;
    private String dateNaiss;
    private double taille;
    private double poids;
    private List<Activite> activites =new ArrayList();
    private List<Utilisateur> listAmi =new ArrayList();

    // Constructeurs


    public Utilisateur(String nom, String prenom, String email, String username, String passWord,
                       String dateNaiss, double taille, double poids, List<Activite> activites,
                       List<Utilisateur> listAmi) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.passWord = passWord;
        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
        this.activites = activites;
        this.listAmi = listAmi;
    }

    public Utilisateur(Long id, String nom, String prenom, String email, String username, String passWord,
                       String dateNaiss, double taille, double poids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.passWord = passWord;
        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
    }

    public Utilisateur(String nom, String prenom, String email, String username, String passWord,
                       String dateNaiss, double taille, double poids) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.passWord = passWord;
        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
    }

    public Utilisateur(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    // getter, setter

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public List<Utilisateur> getListAmi() {
        return listAmi;
    }

    public void setListAmi(List<Utilisateur> listAmi) {
        this.listAmi = listAmi;
    }


    // Calcul IMC (m et kg)
    public double calculImc(double taille, double poids){
        return(poids/(Math.pow((taille/100),2)));
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passWord='" + passWord + '\'' +
                ", dateNaiss='" + dateNaiss + '\'' +
                ", taille=" + taille +
                ", poids=" + poids +
                ", activites=" + activites +
                ", listAmi=" + listAmi +
                '}';
    }
}
