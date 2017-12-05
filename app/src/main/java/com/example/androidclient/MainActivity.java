package com.example.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMail, editMdp;
    private Button btnInscription, btnConnexion, btnOubli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInscription = (Button) findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(bClick2Listener);

    }

    private View.OnClickListener bClick2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(v.getContext(), FormulaireDonnees.class);
            startActivity(i);
        }
    };
}

