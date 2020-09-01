package com.example.tqitreineeflowbank.ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tqitreineeflowbank.CONFIGFB.ConfiguracaoFirebase;
import com.example.tqitreineeflowbank.MODEL.Usuario;
import com.example.tqitreineeflowbank.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button buttonEntrar;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmailLG);
        campoSenha = findViewById(R.id.editSenhaLG);
        buttonEntrar = findViewById(R.id.btEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmailLG = campoEmail.getText().toString();
                String textoSenhaLG = campoSenha.getText().toString();

                if (!textoEmailLG.isEmpty()) {
                    if (!textoSenhaLG.isEmpty()) {

                        usuario = new Usuario();
                        usuario.setEmail(textoEmailLG);
                        usuario.setSenha(textoSenhaLG);
                        //validarLG(v);
                        telaHome();

                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(LoginActivity.this,
                            "Preencha o email",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //Tive um bug relacionado a task e não conseguir resolver portanto desativei o login via conta cadastrada

    public void validarLG(final View view) {

        auth = ConfiguracaoFirebase.getAutenticacao();
        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    telaHome();

                } else {

                    //VALIDAÇÃO DOS CAMPOS

                    String execao = "";

                    try {
                        throw task.getException();

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        execao = "E-mail ou senha não correspondem a um usuário cadastrado!";

                    } catch (FirebaseAuthInvalidUserException e) {
                        execao = "Usuário não está cadastrado!";

                    } catch (Exception e) {
                        execao = "Erro ao cadastrar usuário :" + e.getMessage();

                        e.printStackTrace();

                        Toast.makeText(LoginActivity.this,
                                execao,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void telaHome() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}





