package com.whatnow.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.whatnow.MainActivity;
import com.whatnow.R;
import com.whatnow.ui.login.LoginActivity;

//import com.google.firebase.auth.FirebaseAuth;

public class registro extends AppCompatActivity {

    private Spinner spRol;
    private EditText txtMail;
    private EditText txtCel;
    private EditText txtPassword;
    private EditText txtPassConfirm;
    private Button btnSiguiente;
    //private FirebaseAuth firebase;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtMail = (EditText) findViewById(R.id.editTextEmail2);
        txtCel = (EditText) findViewById(R.id.editTextEmail);
        txtPassword = (EditText) findViewById(R.id.editTextPassword);
        txtPassConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);
        btnSiguiente = (Button) findViewById(R.id.buttonNext);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
