package com.example.mymovies;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText editTextLoginRA = findViewById(R.id.editTextLoginRA);
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextLoginPassword);
        EditText editTextConfPassword = findViewById(R.id.editTextLoginPasswordConfirm);
        Button buttonCadastrar  = findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(v -> {
                int r = 0;
                String login = editTextLoginRA.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String passwordconf = editTextConfPassword.getText().toString().trim();

                if (TextUtils.isEmpty(login)) {
                    editTextLoginRA.setError("Por favor, preencha este campo.");
                    editTextLoginRA.requestFocus();
                    r = 1;
                }
                if (TextUtils.isEmpty(username)) {
                    editTextUsername.setError("Por favor, preencha este campo.");
                    editTextUsername.requestFocus();
                    r = 1;
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Por favor, preencha este campo.");
                    editTextPassword.requestFocus();
                    r = 1;
                }
                if (TextUtils.isEmpty(passwordconf)) {
                    editTextConfPassword.setError("Por favor, preencha este campo.");
                    editTextConfPassword.requestFocus();
                    r = 1;
                } else if (!passwordconf.equals(password)) {
                    editTextConfPassword.setError("As Senhas não coincidem.");
                    editTextConfPassword.requestFocus();
                    r = 1;
                }
                if (r > 0) {
                    return;
                }

               /* try(){
                        todo : Fazer cadastro no firebase

                } catch(com.google.firebase.database.DatabaseException e){

                }  */

            }
        );


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback); //Clicando no botão de voltar, volta para a tela de login

    }



}