package com.example.androidclient.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpAsynTask extends AsyncTask<String, Void, String> {

    private AsyncResponse asyncResponse;

    public HttpAsynTask() {
    }

    public HttpAsynTask(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    private void sendGet() {

    }

    private void sendPost(HttpURLConnection connection, String data) throws IOException {
        //Write data
        OutputStreamWriter osw =
                new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        osw.write(data);
        osw.flush();
        osw.close();
    }

    private void sendPut(HttpURLConnection connection, String data) throws IOException {
        //Write data
        OutputStreamWriter osw =
                new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        osw.write(data);
        osw.flush();
        osw.close();
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String method = params[1];
        String nom=params[2];

        // traitement faire une requete HTTP
        // ...
        URL urlObj = null;
        HttpURLConnection connection = null;
        StringBuilder result = null;

        Log.d("CODE", "" +  nom);

        try {
            urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod(method);

            connection.setRequestProperty("Content-Type", "application/json");

            if (method.equals("GET")) {
                this.sendGet();
            } else if (method.equals("POST")) {
                this.sendPost(connection, nom.toString());
            }else if (method.equals("PUT")) {
                this.sendPut(connection, nom.toString());
            }
            connection.connect();

            // code HTTP 200 404 500 ...
            int codeResponse = connection.getResponseCode();

            Log.d("CODE", "" + codeResponse);

            result = new StringBuilder();

            if( 200 <= codeResponse && codeResponse < 300 ){
                // Je recupère la réponse de la requete
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;

                while((line = br.readLine()) != null){
                    result.append(line); // ajoute la ligne de réponse dans result
                }
                br.close();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("CODE", "" +  result.toString());
        if(result == null) {
            return "";
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("Result = " + result);
        if (this.asyncResponse != null) {
            this.asyncResponse.httpTaskFinished(result);
        }
    }

}
