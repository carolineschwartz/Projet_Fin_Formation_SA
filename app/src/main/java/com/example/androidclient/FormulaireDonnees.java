package com.example.androidclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class FormulaireDonnees extends AppCompatActivity {

    private Button btnForm, btnRetour;
    private EditText editTxtNom, editTxtPrenom, editTxtUsername, editTxtMail;
    private EditText editTxtDateNaiss, editTxtTaille, editTxtPoids;
    private EditText editTxtMdp, editTxtMdpConf;

    private Utilisateur utilisateur;
    private Gson gs = new Gson();
    private String base_url = "http://192.168.137.1:8080";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_donnees);

        // First declare an intent
        final Intent intent = new Intent().setClass(this, ProfilUtilisateur.class);

        editTxtNom = (EditText) findViewById(R.id.editTxtNom);
        editTxtPrenom = (EditText) findViewById(R.id.editTxtPrenom);
        editTxtUsername = (EditText) findViewById(R.id.editTxtUsername);
        editTxtMail = (EditText) findViewById(R.id.editTxtMail);
        editTxtDateNaiss = (EditText) findViewById(R.id.editTxtDateNaiss);
        editTxtTaille = (EditText) findViewById(R.id.editTxtTaille);
        editTxtPoids = (EditText) findViewById(R.id.editTxtPoids);
        editTxtMdp = (EditText) findViewById(R.id.editTxtMdp);
        editTxtMdpConf = (EditText) findViewById(R.id.editTxtMdpConf);

        btnRetour = (Button) findViewById(R.id.btnRetour);
        btnForm = (Button) findViewById(R.id.btnForm);
        //  btnForm.setOnClickListener(bClick2ListenerFormulaire);

        btnForm.setOnClickListener(formClickListener);
        btnRetour.setOnClickListener(retourClickListener);

    }

    private View.OnClickListener retourClickListener = new View.OnClickListener() {

        @Override

        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    };

    private View.OnClickListener formClickListener = new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            Toast.makeText(FormulaireDonnees.this, "Inscription en cours", Toast.LENGTH_SHORT).show();
            String requestUrl = base_url + "/Projet_Fin_Formation/api/utilisateurCreate"; // params[0]
            String method = "POST"; // params[1]

            if (editTxtMail.getText().toString().isEmpty()) {
                AlertDialog alertDialog = new AlertDialog.Builder(FormulaireDonnees.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("Email non saisie");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            } else {
                if (editTxtMdp.getText().toString().equals((editTxtMdpConf.getText().toString()))) {

                    utilisateur = new Utilisateur(0L, editTxtNom.getText().toString(), editTxtPrenom.getText().toString(),
                            editTxtMail.getText().toString(), editTxtUsername.getText().toString(),
                            editTxtMdp.getText().toString(), editTxtDateNaiss.getText().toString(),
                            Double.parseDouble(editTxtTaille.getText().toString()),
                            Double.parseDouble(editTxtPoids.getText().toString()));

                    try {

                        new HttpAsynTask(responseAvailable).execute(requestUrl,
                                method, gs.toJson(utilisateur));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(FormulaireDonnees.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Les 2 mots de passe sont différents");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }

            }
        }
    };


    // Ici j'implemente un class anonyme qui implemente directement
    // l'interface AsyncResponseView
    private AsyncResponse responseAvailable = new AsyncResponse() {

        // la méthode httpTaskFinished est appelée à partir du onPostResult de
        // l'async task
        @Override
        public void httpTaskFinished(String res) {

            if (res.isEmpty()) {
                AlertDialog alertDialog = new AlertDialog.Builder(FormulaireDonnees.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("L'inscription a échoué, recommencez svp");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            } else if (gs.fromJson(res, Utilisateur.class).getId() == 0) {
                AlertDialog alertDialog = new AlertDialog.Builder(FormulaireDonnees.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("L'émail existe déja, recommencez svp");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            } else {

                Intent i = new Intent(getBaseContext(), ProfilUtilisateur.class);
                i.putExtra("userGsonString", res);
                startActivity(i);
            }

        }
    };

}



