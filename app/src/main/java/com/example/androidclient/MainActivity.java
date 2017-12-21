package com.example.androidclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclient.http.AsyncResponse;
import com.example.androidclient.http.HttpAsynTask;
import com.example.androidclient.modele.Utilisateur;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMail, editMdp;
    private Button btnInscription, btnConnexion, btnOubli, btnClose;

    private Utilisateur utilisateur;
    private Gson gs = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editMdp = (EditText) findViewById(R.id.editMdp);

        btnInscription = (Button) findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(bClickListenerInscription);

        btnConnexion = (Button) findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(bClickListenerConnexion);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(bClickListenerQuitter);

    }


    // Connexion à son profil
    private View.OnClickListener bClickListenerConnexion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String requestUrl = GlobalVariables.getBaseUrl() + GlobalVariables.getApp_name()
                                + "webServiceUtilisateur/findByEmail"; // params[0]
            String method = "POST"; // params[1]

            Toast.makeText(MainActivity.this, "Connexion en cours", Toast.LENGTH_SHORT).show();

            utilisateur = new Utilisateur(editTextMail.getText().toString(), editMdp.getText().toString());

            try {

                new HttpAsynTask(responseAvailable).execute(requestUrl,
                        method, gs.toJson(utilisateur));

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
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("Email ou mot de passe incorrects, recommencez svp");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            } else {

                Intent i = new Intent(getBaseContext(), ProfilUtilisateur.class);
                i.putExtra("userGsonString",res );
                startActivity(i);
            }

        }
    };


    // Appel formulaire de saisie
    private View.OnClickListener bClickListenerInscription = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(v.getContext(), FormulaireDonnees.class);
            startActivity(i);
        }
    };

    // Fermeture du formulaire
    private View.OnClickListener bClickListenerQuitter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.exit(0);
        }
    };
}

