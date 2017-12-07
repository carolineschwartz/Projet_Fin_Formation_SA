package com.example.androidclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FormulaireDonnees extends AppCompatActivity {

    private Button btnForm;
    private EditText editTxtNom, editTxtPrenom, editTxtUsername, editTxtMail;
    private EditText editTxtDateNaiss, editTxtTaille, editTxtPoids;
    private EditText editTxtMdp, editTxtMdpConf;

    JSONObject utilisateur = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_donnees);

        editTxtNom = (EditText) findViewById(R.id.editTxtNom);
        editTxtPrenom = (EditText) findViewById(R.id.editTxtPrenom);
        editTxtUsername = (EditText) findViewById(R.id.editTxtUsername);
        editTxtMail = (EditText) findViewById(R.id.editTxtMail);
        editTxtDateNaiss = (EditText) findViewById(R.id.editTxtDateNaiss);
        editTxtTaille = (EditText) findViewById(R.id.editTxtTaille);
        editTxtPoids = (EditText) findViewById(R.id.editTxtPoids);
        editTxtMdp = (EditText) findViewById(R.id.editTxtMdp);
        editTxtMdpConf = (EditText) findViewById(R.id.editTxtMdpConf);

        btnForm = (Button) findViewById(R.id.btnForm);
        btnForm.setOnClickListener(bClick2ListenerFormulaire);

    }


    private View.OnClickListener bClick2ListenerFormulaire = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTxtMdp.getText().toString().equals((editTxtMdpConf.getText().toString()))) {

                // Creation de l'objet JSON

                try {
                    utilisateur.put("nom", editTxtNom.getText().toString());
                    utilisateur.put("prenom", editTxtPrenom.getText().toString());
                    utilisateur.put("email", editTxtMail.getText().toString());
                    utilisateur.put("username", editTxtUsername.getText().toString());
                    utilisateur.put("passWord", editTxtMdp.getText().toString());
                    utilisateur.put("dateNaiss", editTxtDateNaiss.getText().toString());
                    utilisateur.put("taille", editTxtTaille.getText().toString());
                    utilisateur.put("poids", editTxtPoids.getText().toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(utilisateur);

                // Connexion serveur et appel requete POST
                String url = "http://192.168.14.236:8080/Projet_Fin_Formation/api/modele.utilisateur";
                new HttpAsyncTask().execute(url,utilisateur.toString());

            } else {

                Intent i = new Intent(v.getContext(), FormulaireDonnees.class);
                startActivity(i);
            }
        }
    };


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            URL url = null;
            HttpURLConnection connection = null;
            StringBuilder result = null;

            try {
                url = new URL(path);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
               // connection.setConnectTimeout(10000);
               // connection.setReadTimeout(10000);
                connection.setRequestProperty("Content-Type", "application/json");

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(utilisateur.toString());
                out.close();
                connection.connect();

                int httpResult = connection.getResponseCode();
                result = new StringBuilder();
                Log.d("CODE", "" + httpResult);

                // Je vérifie que la requete a bien eu lieu
                if (200 <= httpResult && httpResult < 300) {
                    // Je recupère la réponse de la requete
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    br.close();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            if (result == null) {
                return "";
            }
            return result.toString();

        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);

        }
    }
}

