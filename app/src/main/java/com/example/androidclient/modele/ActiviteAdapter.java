package com.example.androidclient.modele;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidclient.PerformanceActivity;
import com.example.androidclient.R;
import com.example.androidclient.VideoActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by HB on 19/12/2017.
 */

public class ActiviteAdapter extends BaseAdapter {

    // public class TodoAdapter extends BaseAdapter implements Filterable {

    private TextView TxtViewDate, txtViewDuree;
    private TextView textViewCalorie, txtViewNb, textViewSport;

    private ArrayList<Activite> activitetList;
    private Activite currentActivite;
    private Gson gs = new Gson();
    private Utilisateur utilisateur;

    private Context context;

    public ActiviteAdapter(Context context, ArrayList<Activite> list) {
        this.context = context;
        this.activitetList = list;
    }


    // Ajouter un sport dans la liste
    public void add(Activite activite) {
        activitetList.add(activite);
        // notifyDataSetChanged => on signale que les données ont changé
        // et donc cela va "recharger" la listView
        notifyDataSetChanged();
    }

    // Nombre d'éléments dans ma liste
    // Il faut le surchager la méthode car on a pas tout le temps
    // des arraylist
    @Override
    public int getCount() {
        return this.activitetList.size();
    }

    // D'obtenir le sport à la position index
    @Override
    public Object getItem(int index) {
        return this.activitetList.get(index);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    // Cette méthode getView va être appelée automatiquement à chaque fois
    // qu'une ligne est affichée à l'écran
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // LinearLayout correspond à l'élement racine de row_todo
        LinearLayout linearLayout = null;
        if (convertView != null) {
            linearLayout = (LinearLayout) convertView;
            convertView.setOnClickListener(null);
        } else {
            linearLayout = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.row_activite, parent, false);
        }
        // Je récupère le sport on est entrain d'afficher
        currentActivite = (Activite) getItem(position);


        // Ici je dois "remplir" les élements graphiques de mon row_todo
        TxtViewDate = (TextView) linearLayout.findViewById(R.id.TxtViewDate);
        txtViewDuree = (TextView) linearLayout.findViewById(R.id.txtViewDuree);
        textViewCalorie = (TextView) linearLayout.findViewById(R.id.textViewCalorie);
        txtViewNb = (TextView) linearLayout.findViewById(R.id.txtViewNb);
        textViewSport = (TextView) linearLayout.findViewById(R.id.textViewSport);

        TxtViewDate.setText(currentActivite.getDate());
        txtViewDuree.setText(String.valueOf(currentActivite.getDuree()));
        textViewCalorie.setText(String.valueOf(currentActivite.getCaloriePerdues()));
        txtViewNb.setText(String.valueOf(currentActivite.getNombre()));
        textViewSport.setText(currentActivite.getSport().getNom());


        return linearLayout;
    }


}
