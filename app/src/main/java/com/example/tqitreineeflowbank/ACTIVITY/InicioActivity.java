package com.example.tqitreineeflowbank.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tqitreineeflowbank.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void btCriarct(View view) {
        startActivity(new Intent(this, CadastroActivity.class));

    }

    public void btEntrar(View view) {
        startActivity(new Intent(this, LoginActivity.class));

    }
}