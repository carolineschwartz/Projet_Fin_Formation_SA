package com.example.androidclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Sport;
import com.example.androidclient.modele.SportAdapter;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ProfilUtilisateur extends AppCompatActivity {

    private TextView txtViewNom, txtViewPrenom;
    private Button btnIMC, btnListePerf, btnClose;
    private Button BtnSport1, BtnSport2, BtnSport3;
    private ListView itemsListView;


    private Utilisateur utilisateur;
    private Gson gs = new Gson();

    private ArrayList<Sport> sportList;
    private Sport sport;
    private SportAdapter adapter;

    Type listType = new TypeToken<ArrayList<Sport>>() {
    }.getType();
    ArrayList<Sport> mListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilisateur);

        txtViewNom = (TextView) findViewById(R.id.txtViewNom);
        txtViewPrenom = (TextView) findViewById(R.id.txtViewPrenom);
        itemsListView = (ListView) findViewById(R.id.itemsListView);

        btnIMC = (Button) findViewById(R.id.btnIMC);
        btnIMC.setOnClickListener(bClickListenerIMC);

        btnListePerf = (Button) findViewById(R.id.btnListePerf);
        btnListePerf.setOnClickListener(bClickListenerbtnListePerf);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(bClickListenerQuitter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            /*SharedPreferences settings = getSharedPreferences("USER_CONFIG", 0);
            String userGsonString = settings.getString("userGsonString", "");
            System.out.println("Test recup getSHared " + userGsonString);*/

            String userGsonString = extras.getString("userGsonString");
            utilisateur = gs.fromJson(userGsonString, Utilisateur.class);
            txtViewNom.setText(utilisateur.getNom());
            txtViewPrenom.setText(utilisateur.getPrenom());
        }


        // Recuperation de la liste des sport dans la  base de données
        String requestUrl = GlobalVariables.getBaseUrl() + GlobalVariables.getApp_name()
                            +"webServiceSport"; // params[0]
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

               Type listType = new TypeToken<ArrayList<Sport>>() {
                }.getType();
                mListe = new Gson().fromJson(res, listType);

                adapter = new SportAdapter(ProfilUtilisateur.this, mListe,utilisateur);
                itemsListView.setAdapter(adapter);

                Log.d("CODE", "" + mListe);
                Log.d("CODE", "" + res);

            }

        }
    };

    // IMC
    private View.OnClickListener bClickListenerIMC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ImcFormulaire.class);
            i.putExtra("userGsonString", gs.toJson(utilisateur));
            startActivity(i);

        }
    };


    // Affiche la liste des performances
    private View.OnClickListener bClickListenerbtnListePerf = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ListePerformance.class);
            i.putExtra("userGsonString", gs.toJson(utilisateur));
            startActivity(i);

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
}
