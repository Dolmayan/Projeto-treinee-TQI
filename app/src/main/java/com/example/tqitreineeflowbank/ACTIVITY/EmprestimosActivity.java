package com.example.tqitreineeflowbank.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tqitreineeflowbank.ADAPTER.AdapterMovimentacao;
import com.example.tqitreineeflowbank.MODEL.Emprestimos;
import com.example.tqitreineeflowbank.R;

import java.util.ArrayList;
import java.util.List;

public class EmprestimosActivity extends AppCompatActivity {

    private Button solicitarButton;
    private RecyclerView recyclerView;
    private AdapterMovimentacao adapterMovimentacao;
    private List<Emprestimos> emprestimos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimos);

        solicitarButton = findViewById(R.id.buttonSolicitar);
        recyclerView = findViewById(R.id.recyclerEmp);

        //Configurar adapter
        adapterMovimentacao = new AdapterMovimentacao(emprestimos, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);
    }

    public void novoEmprestimo(View view){
        startActivity(new Intent(this, GerarEmpActivity.class));
    }

}