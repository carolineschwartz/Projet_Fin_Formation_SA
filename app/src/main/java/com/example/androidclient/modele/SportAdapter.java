package com.example.androidclient.modele;

/**
// * Created by schwartz on 11/12/2017.
// */



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;


import com.example.androidclient.FormulaireDonnees;
import com.example.androidclient.PerformanceActivity;
import com.example.androidclient.ProfilUtilisateur;
import com.example.androidclient.R;

import java.util.ArrayList;






import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.androidclient.VideoActivity;
import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.http.HttpAsynTaskSport;
import com.example.androidclient.modele.Sport;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class SportAdapter extends BaseAdapter {

    // public class TodoAdapter extends BaseAdapter implements Filterable {

    private TextView text_view_sport_name;
    private Button btnNomSport, btnVideoSport;

    private ArrayList<Sport> sportList;
    private Sport currentSport;
    private Gson gs = new Gson();
    private Utilisateur utilisateur;

    private Context context;

    public SportAdapter(Context context, ArrayList<Sport> list, Utilisateur utilisateur ) {
        this.context = context;
        this.sportList = list;
        this.utilisateur=utilisateur;
    }

    // Ajouter un sport dans la liste
    public void add(Sport sport) {
        sportList.add(sport);
        // notifyDataSetChanged => on signale que les données ont changé
        // et donc cela va "recharger" la listView
        notifyDataSetChanged();
    }

    // Nombre d'éléments dans ma liste
    // Il faut le surchager la méthode car on a pas tout le temps
    // des arraylist
    @Override
    public int getCount() {
        return this.sportList.size();
    }

    // D'obtenir le sport à la position index
    @Override
    public Object getItem(int index) {
        return this.sportList.get(index);
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
                    inflate(R.layout.row_sport, parent, false);
        }

        // Je récupère le sport on est entrain d'afficher
        currentSport = (Sport) getItem(position);


        // Ici je dois "remplir" les élements graphiques de mon row_todo

        btnNomSport = (Button) linearLayout.findViewById(R.id.btnNomSport);
        btnNomSport.setOnClickListener(bClickListenerSport);
        btnNomSport.setText("Performances");
        btnVideoSport = (Button) linearLayout.findViewById(R.id.btnVideoSport);
       // btnVideoSport.setOnClickListener(bClicListenerVideo);
        btnVideoSport.setText("Videos");
        text_view_sport_name= (TextView) linearLayout.findViewById(R.id.text_view_sport_name);
        text_view_sport_name.setText(currentSport.getNom());

        return linearLayout;
    }


    private View.OnClickListener bClickListenerSport = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), PerformanceActivity.class);
            i.putExtra("userGsonString", gs.toJson(utilisateur));
            i.putExtra("sportGson", gs.toJson(currentSport));
            v.getContext().startActivity(i);
        }
    };
/*
    private View.OnClickListener bClicListenerVideo = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(),VideoActivity.class);
            i.putExtra("sportGson", gs.toJson(currentSport));
            view.getContext().startActivity(i);
        }
    };*/
}



