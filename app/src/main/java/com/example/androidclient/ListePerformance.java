package com.example.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Activite;

import com.example.androidclient.modele.ActiviteAdapter;
import com.example.androidclient.modele.Sport;
import com.example.androidclient.modele.SportAdapter;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListePerformance extends AppCompatActivity {

private ListView ListViewActivite;
private Button   btnClose;


    private ArrayList<Activite> activiteList;
    private Activite activite;
    private ActiviteAdapter adapter;

    Type listType = new TypeToken<ArrayList<Activite>>() {
    }.getType();
    ArrayList<Activite> mListe;

    private Utilisateur utilisateur;
    private Gson gs = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_performance);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(bClickListenerQuitter);
        ListViewActivite = (ListView) findViewById(R.id.ListViewActivite);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String idGson = extras.getString("idGson");

            // Recuperation de la liste des activités dans la  base de données
            String requestUrl = GlobalVariables.getBaseUrl() + GlobalVariables.getApp_name()
                                    + "webServiceActivite/findActivite/"
                                    + idGson; // params[0]
            Log.d("CODE", "" + requestUrl);

            String method = "GET"; // params[1]

            //  Toast.makeText(ProfilUtilisateur.this, "Sauvegarde en cours", Toast.LENGTH_SHORT).show();

            try {

                new HttpAsynTask(responseAvailable).execute(requestUrl, method, "");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private AsyncResponse responseAvailable = new AsyncResponse() {

        // la méthode httpTaskFinished est appelée à partir du onPostResult del'async task
        @Override
        public void httpTaskFinished(String res) {

            if (res.isEmpty()) {


            } else {

                /*Type listType = new TypeToken<ArrayList<Activite>>() {
                }.getType();*/
                mListe = new Gson().fromJson(res, listType);

                adapter = new ActiviteAdapter(ListePerformance.this, mListe);
                ListViewActivite.setAdapter(adapter);

                Log.d("CODE", "" + mListe);
                Log.d("CODE", "" + res);

            }

        }
    };



    // Fermeture du formulaire
    private View.OnClickListener bClickListenerQuitter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), ProfilUtilisateur.class);
            startActivity(i);

        }
    };
}
