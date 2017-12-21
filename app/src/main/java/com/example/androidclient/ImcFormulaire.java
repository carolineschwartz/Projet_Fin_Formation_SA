package com.example.androidclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;

public class ImcFormulaire extends AppCompatActivity {

    private TextView txtViewNom, txtViewPrenom, txtViewImc;
    private Button btnCalculImc, btnSauvegarde, btnFermer;
    private EditText editTxtTaille, editTxtPoids;

    private Utilisateur utilisateur;
    private Gson gs = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_formulaire);

        txtViewNom = (TextView) findViewById(R.id.txtViewNom);
        txtViewPrenom = (TextView) findViewById(R.id.txtViewPrenom);
        txtViewImc = (TextView) findViewById(R.id.txtViewImc);
        editTxtTaille = (EditText) findViewById(R.id.editTxtTaille);
        editTxtPoids = (EditText) findViewById(R.id.editTxtPoids);

        btnCalculImc = (Button) findViewById(R.id.btnCalculImc);
        btnCalculImc.setOnClickListener(bClickListenerCalculIMC);

        btnFermer = (Button) findViewById(R.id.btnFermer);
        btnFermer.setOnClickListener(bClickListenerFermer);

        btnSauvegarde = (Button) findViewById(R.id.btnSauvegarde);
        btnSauvegarde.setOnClickListener(bClickListenerSave);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userGsonString = extras.getString("userGsonString");
            utilisateur = gs.fromJson(userGsonString, Utilisateur.class);
            txtViewNom.setText(utilisateur.getNom());
            txtViewPrenom.setText(utilisateur.getPrenom());
            editTxtTaille.setText(String.valueOf(utilisateur.getTaille()));
            editTxtPoids.setText(String.valueOf(utilisateur.getPoids()));

            Double imc = utilisateur.calculImc(utilisateur.getTaille(), utilisateur.getPoids());
            txtViewImc.setText(String.valueOf(imc));

        }
    }

    // Méthode - Calcul IMC
    private View.OnClickListener bClickListenerCalculIMC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Double tailleNew = Double.parseDouble(editTxtTaille.getText().toString());
            Double poidsNew = Double.parseDouble(editTxtPoids.getText().toString());

            Double imcCalculee = utilisateur.calculImc(tailleNew, poidsNew);
            txtViewImc.setText(String.valueOf(imcCalculee));
        }
    };

    // Méthode - Sauvegarde les données et persiste dans la BDD
    private void saveDonneesTaillePoids(Double taille, Double poids) {
        utilisateur.setTaille(taille);
        utilisateur.setPoids(poids);

        Toast.makeText(ImcFormulaire.this, "Sauvegarde en cours", Toast.LENGTH_SHORT).show();

        String requestUrl =GlobalVariables.getBaseUrl() + GlobalVariables.getApp_name()
                            +"webServiceUtilisateur"; // params[0]
        String method = "PUT"; // params[1]

        try {

            new HttpAsynTask(responseAvailable).execute(requestUrl,
                    method, gs.toJson(utilisateur));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fermer le formulaire IMC et sauvegarder les données poids et taille
    private View.OnClickListener bClickListenerSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ImcFormulaire.this.saveDonneesTaillePoids(Double.parseDouble(editTxtTaille.getText().toString()),
                    Double.parseDouble(editTxtPoids.getText().toString()));

        }

    };

    // Fermer le formulaire IMC et sauvegarder les données poids et taille
    private View.OnClickListener bClickListenerFermer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(ImcFormulaire.this);
            builder1.setTitle("Fermeture");
            builder1.setMessage("Voulez-vous sauvegarder les nouvelles données ?");
            //builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OUI",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            ImcFormulaire.this.saveDonneesTaillePoids(Double.parseDouble(editTxtTaille.getText().toString()),
                                    Double.parseDouble(editTxtPoids.getText().toString()));

                        }
                    });

            builder1.setNegativeButton(
                    "NON",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            editTxtTaille.setText(String.valueOf(utilisateur.getTaille()));
                            editTxtPoids.setText(String.valueOf(utilisateur.getPoids()));
                            Intent i = new Intent(getBaseContext(), ProfilUtilisateur.class);
                            i.putExtra("userGsonString", gs.toJson(utilisateur));
                            startActivity(i);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }

    };


    private AsyncResponse responseAvailable = new AsyncResponse() {

        // la méthode httpTaskFinished est appelée à partir du onPostResult de
        // l'async task
        @Override
        public void httpTaskFinished(String res) {

            if (res.isEmpty()) {
                AlertDialog alertDialog = new AlertDialog.Builder(ImcFormulaire.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("La sauvegarde a échoué, recommencez svp");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            } else {

                Toast.makeText(ImcFormulaire.this, "Sauvegarde réussie", Toast.LENGTH_SHORT).show();

                AlertDialog alertDialog = new AlertDialog.Builder(ImcFormulaire.this).create();
                alertDialog.setMessage("Fermer maintenant ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OUI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent i = new Intent(getBaseContext(), ProfilUtilisateur.class);
                        i.putExtra("userGsonString", gs.toJson(utilisateur));
                        startActivity(i);
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NON", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();


            }

        }
    };

}


