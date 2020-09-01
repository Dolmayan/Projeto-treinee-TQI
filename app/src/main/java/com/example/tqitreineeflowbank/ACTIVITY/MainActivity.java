package com.example.tqitreineeflowbank.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tqitreineeflowbank.ACTIVITY.CadastroActivity;
import com.example.tqitreineeflowbank.ACTIVITY.InicioActivity;
import com.example.tqitreineeflowbank.ACTIVITY.LoginActivity;
import com.example.tqitreineeflowbank.CONFIGFB.ConfiguracaoFirebase;
import com.example.tqitreineeflowbank.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        verificarUserLogado();
    }

    //tela de criar conta ou login
    public void inicio(View view) {
        startActivity(new Intent(this, InicioActivity.class));
    }



    public void verificarUserLogado(){

        auth = ConfiguracaoFirebase.getAutenticacao();

        auth.signOut();

        if (auth.getCurrentUser() != null){
            loginConfirmado();
        }
    }

    public void loginConfirmado() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}