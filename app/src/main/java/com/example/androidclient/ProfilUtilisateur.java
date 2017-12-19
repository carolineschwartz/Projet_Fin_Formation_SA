package com.example.androidclient;

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

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.http.HttpAsynTaskSport;
import com.example.androidclient.modele.Sport;
import com.example.androidclient.modele.SportAdapter;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ProfilUtilisateur extends AppCompatActivity {

    private TextView txtViewNom, txtViewPrenom;
    private Button btnIMC, btnClose;
    private Button BtnSport1, BtnSport2, BtnSport3;
    private SportAdapter adapter;


    private Utilisateur utilisateur;
    private Gson gs = new Gson();
    private String base_url = "http://test.legionnaire.ovh:8080";

    private ArrayList<Sport> sportList;
    private Sport sport;

    Type listType = new TypeToken<ArrayList<Sport>>() {
    }.getType();
    ArrayList<Sport> mListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilisateur);

        txtViewNom = (TextView) findViewById(R.id.txtViewNom);
        txtViewPrenom = (TextView) findViewById(R.id.txtViewPrenom);
        adapter = new SportAdapter(this, mListe);


        btnIMC = (Button) findViewById(R.id.btnIMC);
        btnIMC.setOnClickListener(bClickListenerIMC);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(bClickListenerQuitter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userGsonSting = extras.getString("userGsonSting");
            utilisateur = gs.fromJson(userGsonSting, Utilisateur.class);
            txtViewNom.setText(utilisateur.getNom());
            txtViewPrenom.setText(utilisateur.getPrenom());
        }

        BtnSport1 = (Button) findViewById(R.id.BtnSport1);
        BtnSport1.setOnClickListener(bClickListenerSport);

        BtnSport2 = (Button) findViewById(R.id.BtnSport2);
        BtnSport2.setOnClickListener(bClickListenerSport);

        BtnSport3 = (Button) findViewById(R.id.BtnSport3);
        BtnSport3.setOnClickListener(bClickListenerSport);


        // Recuperation de la liste des sport dans la  base de données
        String requestUrl = base_url + "/Projet_Fin_Formation/api/webServiceSport"; // params[0]
        String method = "GET"; // params[1]

        //  Toast.makeText(ProfilUtilisateur.this, "Sauvegarde en cours", Toast.LENGTH_SHORT).show();

        try {

            new HttpAsynTask(responseAvailable).execute(requestUrl, method, "");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private AsyncResponse responseAvailable = new AsyncResponse() {

        // la méthode httpTaskFinished est appelée à partir du onPostResult del'async task
        @Override
        public void httpTaskFinished(String res) {

            if (res.isEmpty()) {


            } else {

//                Type listType = new TypeToken<ArrayList<Sport>>() {
//                }.getType();
                mListe = new Gson().fromJson(res, listType);

                Log.d("CODE", "" + mListe);




                ListView itemsListView = (ListView) findViewById(R.id.LstView);
                itemsListView.setAdapter(adapter);


                BtnSport1.setText(mListe.get(0).getNom());
                BtnSport2.setText(mListe.get(1).getNom());
                BtnSport3.setText(mListe.get(2).getNom());

            }

        }
    };

    // Fermeture du formulaire
    private View.OnClickListener bClickListenerQuitter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

        }
    };
    // Sport = course à pied, ouverture de l'activité
    private View.OnClickListener bClickListenerSport = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), PerformanceActivity.class);
            i.putExtra("userGsonSting", gs.toJson(utilisateur));
            switch (v.getId()) {
                case R.id.BtnSport1:
                   i.putExtra("sportGson", gs.toJson(mListe.get(0)));
                    break;
                case R.id.BtnSport2:
                    i.putExtra("sportGson", gs.toJson(mListe.get(1)));
                    break;
                case R.id.BtnSport3:
                    i.putExtra("sportGson", gs.toJson(mListe.get(2)));
                    break;
            }

            startActivity(i);
        }
                };



            // IMC
            private View.OnClickListener bClickListenerIMC = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ImcFormulaire.class);
                    i.putExtra("userGsonSting", gs.toJson(utilisateur));
                    startActivity(i);
                }
            };

        }

