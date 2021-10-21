package com.whatnow.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.whatnow.MainActivity;
import com.whatnow.R;
import com.whatnow.ui.register.SkillsActivity;
import com.whatnow.ui.register.registro;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private TextView crearCuentaText;
    private TextView olvidoContraText;

    private FirebaseAuth miAuth;
    private ProgressDialog CargaProc;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        miAuth = FirebaseAuth.getInstance();
        olvidoContraText = findViewById(R.id.textOlvido);
        crearCuentaText = findViewById(R.id.textCrear);

        // LOGIN PROPIO
        usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button BtnInicioSesion = findViewById(R.id.login);
        CargaProc = new ProgressDialog(this);

        // CREAR NUEVA CUENTA
        crearCuentaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), registro.class);
                startActivity(intent);
            }
        });

        // OLVIDE MI CONTRASEÑA
        olvidoContraText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent ccc = new Intent(LoginActivity.this, SignUp.class);
               // startActivity(ccc);
            }
        });

        //LOGIN EMAIL PROPIO
        BtnInicioSesion.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //Intent main = new Intent(getBaseContext(), SkillsActivity.class);
                                                //startActivity(main);
                                                //finish();
                                                CargaProc.setMessage("Verificando con la Base de datos...");
                                                CargaProc.show();
                                                if(!usernameEditText.getText().toString().trim().isEmpty() && !passwordEditText.getText().toString().trim().isEmpty()) {
                                                    InicioUser(usernameEditText.getText().toString().trim(),
                                                            passwordEditText.getText().toString().trim());
                                                }else{
                                                    CargaProc.setMessage("Datos incompletos");
                                                    CargaProc.show();
                                                }
                                            }
                                        }
        );
    }

    // IR A PANTALLA PRINCIPAL
    private void GoMainScreen(){
        Intent ccc = new Intent(LoginActivity.this, SkillsActivity.class);
        startActivity(ccc);
    }

    // LOGIN CON EMAIL Y PASSWORD
    private void InicioUser(final String email, String password){
        miAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = miAuth.getCurrentUser();
                            if(user != null) {
                                GoMainScreen();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            updateUi(" Falla de autentificación.");
                        }
                        CargaProc.dismiss();
                    }
                });
    }

    private void updateUi(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
