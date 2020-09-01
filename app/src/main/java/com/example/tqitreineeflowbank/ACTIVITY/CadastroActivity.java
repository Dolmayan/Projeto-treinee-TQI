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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import eu.inmite.android.lib.validations.form.annotations.NotEmpty;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;

    private EditText edtEmail;

    private EditText edtSenha;

    private Button buttonCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.editNome);
        edtEmail = findViewById(R.id.editEmail);
        edtSenha = findViewById(R.id.editSenha);
        buttonCadastrar = findViewById(R.id.btCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String textoNome = edtNome.getText().toString();
                String textoEmail = edtEmail.getText().toString();
                String textoSenha = edtSenha.getText().toString();

                //validar se os campos foram preenchidos

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrarUsuario(v);

                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o email",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void cadastrarUsuario(final View view) {

        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao cadastrar usuario!",
                            Toast.LENGTH_SHORT).show();
                    finish();

                } else {

                    //VALIDAÇÃO DOS CAMPOS

                    String execao = "";

                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        execao = "digite uma senha mais forte!";

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        execao = "digite um e-mail válido!";

                    } catch (FirebaseAuthUserCollisionException e) {
                        execao = "esse e-mail já está cadastrado";

                    } catch (Exception e) {
                        execao = "Erro ao cadastrar usuário :" + e.getMessage();

                        e.printStackTrace();

                        Toast.makeText(CadastroActivity.this,
                                execao,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void termos(View view) {

        startActivity(new Intent(this, TermosActivity.class));
    }
}
