package com.example.androidclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Activite;
import com.example.androidclient.modele.Sport;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;

import java.util.Date;


public class PerformanceActivity extends AppCompatActivity {

    private TextView txtViewNomSport, idDate;
    private TextView txtViewNom, txtViewPrenom, txtViewCalorieCalculee;
    private Button btnStart, btnPause, btnReset;
    private Button BtnCalorie, btnSave, btnClose;
    private Chronometer chrono;


    private Utilisateur utilisateur;
    private Activite activite;
    private Sport sport;
    private Gson gs = new Gson();
    private String base_url = GlobalVariables.getBaseUrl();

    private long time = 0;
    private long timeFinal = 0;
    private boolean running = false;
    private double caloriesPerdues = 0;




//    Calendar c = Calendar.getInstance();
//    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
//    String datetime = dateformat.format(c.getTime());
//        System.out.println(datetime);

   // Date getTimestamp ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        txtViewNom = (TextView) findViewById(R.id.txtViewNom);
        txtViewPrenom = (TextView) findViewById(R.id.txtViewPrenom);
        txtViewNomSport = (TextView) findViewById(R.id.txtViewNomSport);
        txtViewCalorieCalculee = (TextView) findViewById(R.id.txtViewCalorieCalculee);
        idDate = (TextView) findViewById(R.id.idDate);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnReset = (Button) findViewById(R.id.btnReset);

        BtnCalorie = (Button) findViewById(R.id.BtnCalorie);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnClose = (Button) findViewById(R.id.btnClose);

        BtnCalorie.setOnClickListener(bClickListenerCalculCalorie);
        btnClose.setOnClickListener(bClickListenerClose);
        btnSave.setOnClickListener(bClickListenerSave);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userGsonString = extras.getString("userGsonString");
            String sportGson = extras.getString("sportGson");
            utilisateur = gs.fromJson(userGsonString, Utilisateur.class);
            sport = gs.fromJson(sportGson, Sport.class);

            txtViewNom.setText(utilisateur.getNom());
            txtViewPrenom.setText(utilisateur.getPrenom());
            txtViewNomSport.setText(sport.getNom());
        }

    }


    // Calcul calorie perdues
    private View.OnClickListener bClickListenerCalculCalorie = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.d("CODE", "" + time/1000);
            Log.d("CODE", "" + caloriesPerdues);
            //Nb de calories perdues par heure
            caloriesPerdues = (sport.getCalorie() * (time / 1000))/3600;
            txtViewCalorieCalculee.setText(String.valueOf(caloriesPerdues));

        }
    };


    // Fermeture du formulaire
    private View.OnClickListener bClickListenerClose = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(v.getContext(), ProfilUtilisateur.class);
            i.putExtra("userGsonSting", gs.toJson(utilisateur));
            startActivity(i);
        }
    };

    // Sauvegarde la performance
    private View.OnClickListener bClickListenerSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Création  de l'activite

            timeFinal = (time / 1000);
            if(caloriesPerdues==0){
                caloriesPerdues = (sport.getCalorie() * (time / 1000))/3600;
            }

            Log.d("CODE", "" + timeFinal);
            Log.d("CODE", "" + caloriesPerdues);

            activite = new Activite(timeFinal, idDate.getText().toString(), sport, utilisateur,caloriesPerdues);


            Toast.makeText(PerformanceActivity.this, "Sauvegarde en cours", Toast.LENGTH_SHORT).show();
            String requestUrl = base_url + GlobalVariables.getApp_name()+"api/activiteWebService"; // params[0]

            String method = "POST"; // params[1]

            Log.d("CODE", "" + activite);

            try {

                new HttpAsynTask(responseAvailable).execute(requestUrl,
                        method, gs.toJson(activite));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    private AsyncResponse responseAvailable = new AsyncResponse() {

        // la méthode httpTaskFinished est appelée à partir du onPostResult de
        // l'async task
        @Override
        public void httpTaskFinished(String res) {

            if (res.isEmpty()) {
                AlertDialog alertDialog = new AlertDialog.Builder(PerformanceActivity.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("La sauvegarde a échoué, recommencez svp");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            } else {
                Toast.makeText(PerformanceActivity.this, "Sauvegarde réussie", Toast.LENGTH_SHORT).show();
            }

        }
    };


    // CHRONOMETRE
    // elapsedRealtime = temps actuel
    // getBase         = temps de lancement du chrono

    // @Override
    public void startChronometer(View view) {
        chrono = (Chronometer) findViewById(R.id.chrono);
        if (running == true) {
            chrono.setBase(SystemClock.elapsedRealtime() - time);
            chrono.start();
            running = false;
        } else {
            chrono.setBase(SystemClock.elapsedRealtime());
            chrono.start();
        }
    }

    public void pauseChronometer(View view) {
        chrono = (Chronometer) findViewById(R.id.chrono);
        running = true;
        time = SystemClock.elapsedRealtime() - chrono.getBase();
        chrono.stop();
    }

    public void resetChronometer(View view) {
        chrono = (Chronometer) findViewById(R.id.chrono);
        running = false;
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.stop();

    }


}

