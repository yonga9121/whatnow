package com.whatnow.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.whatnow.MainActivity;
import com.whatnow.R;
import com.whatnow.controllers.users.UsersController;
import com.whatnow.models.Session;
import com.whatnow.services.users.UsersService;
import com.whatnow.ui.login.LoginActivity;
import com.whatnow.utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

                UsersController.signup(
                        txtMail.getText().toString(),
                        txtPassword.getText().toString(),
                        txtPassConfirm.getText().toString()
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Gson gson = new Gson();
                        try {
                            String auxResponse = response.body().string();
                            Session session = gson.fromJson(auxResponse, Session.class);
                            Utils.saveString("token", session.getToken(), getBaseContext());
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Fallo el registrio. Intenta de nuevo", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Fallo el registrio. Intenta de nuevo", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }

}
