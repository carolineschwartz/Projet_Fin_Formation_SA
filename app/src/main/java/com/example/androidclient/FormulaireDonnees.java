package com.example.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormulaireDonnees extends AppCompatActivity {

    private Button btnFrom;
    private EditText editTxtMdp, editTxtMdpConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_donnees);

        editTxtMdp = (EditText) findViewById(R.id.editTxtMdp);
        editTxtMdpConf = (EditText) findViewById(R.id.editTxtMdpConf);
        btnFrom = (Button) findViewById(R.id.btnFrom);
        btnFrom.setOnClickListener(bClick2ListenerFormulaire);

    }
    private View.OnClickListener bClick2ListenerFormulaire = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editTxtMdp.getText().toString().equals((editTxtMdpConf.getText().toString()))){
                // Envoie les données du formulaire

            }else{
                Intent i = new Intent(v.getContext(), FormulaireDonnees.class);
                startActivity(i);
            }
        }
    };
}
