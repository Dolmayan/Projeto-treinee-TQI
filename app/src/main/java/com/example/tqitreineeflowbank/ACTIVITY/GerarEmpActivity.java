package com.example.tqitreineeflowbank.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tqitreineeflowbank.MODEL.Emprestimos;
import com.example.tqitreineeflowbank.R;

public class GerarEmpActivity extends AppCompatActivity {

    private EditText edtValor, edtParcelas;
    private Button buttonFinalizar;
    private Emprestimos emprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_emp);

        edtParcelas = findViewById(R.id.edtParcelas);
        edtValor = findViewById(R.id.edtValor);
        buttonFinalizar = findViewById(R.id.buttonFinalizar);


        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEmprestimo(v);
            }
        });
    }

    public void confirmarEmprestimo (View view){
        if (!edtParcelas.toString().isEmpty()){
            if (!edtValor.toString().isEmpty()){

                String textoValor = edtValor.getText().toString();
                String textoParcela = edtParcelas.getText().toString();

                emprestimos = new Emprestimos();
                emprestimos.setValor(textoValor);
                emprestimos.setParcelas(textoParcela);
                finish();

                Toast.makeText(GerarEmpActivity.this,
                        "Solicitação bem sucedida",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(GerarEmpActivity.this,
                    "Preencha os dados para emprestimo!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}