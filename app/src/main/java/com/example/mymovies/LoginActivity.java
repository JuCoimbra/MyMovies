package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

<<<<<<< Updated upstream
=======
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

>>>>>>> Stashed changes
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference();
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonCadastrar= findViewById(R.id.buttonCadastrar);
        EditText editTextLoginID = findViewById(R.id.editTextLoginName);
        EditText editTextPassword = findViewById(R.id.editTextLoginPassword);
        TextView textViewError = findViewById(R.id.textViewErro);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String login = editTextLoginID.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(login)) {
                    editTextLoginID.setError("Por favor, preencha este campo.");
                    editTextLoginID.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Por favor, preencha este campo.");
                    editTextPassword.requestFocus();
                    return;
                }

                try {
<<<<<<< Updated upstream
                    usersRef.child(login).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
=======
                    int r = Integer.parseInt(login);
                    String ConfLogin = String.valueOf(r);

                    if (!(login.equals(ConfLogin) && login.length() == 9)) {
                        editTextLoginID.setError("RA não pode ser validado. Utilize somente numeros");
                        editTextLoginID.requestFocus();

                        return;
                    }
                } catch (java.lang.NumberFormatException l){
                    System.out.println("NumberFormatException");
                    editTextLoginID.setError("RA não pode ser validado. Utilize somente numeros");
                    editTextLoginID.requestFocus();

                    return;
                }


                try {
                    usersRef.child(login).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            User usuario = new User((HashMap<Objects, String>) task.getResult().getValue(),login);
                           if (!task.isSuccessful() || !password.equals(usuario.getSenha())) {
>>>>>>> Stashed changes
                                textViewError.setVisibility(View.VISIBLE);
                                textViewError.setText("@string/ops_login");
                            } else {
<<<<<<< Updated upstream
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
=======
                                Intent intent = new Intent(LoginActivity.this, FavoritosActivity.class);
>>>>>>> Stashed changes
                                textViewError.setVisibility(View.GONE);
                                intent.putExtra("user", login);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } catch (com.google.firebase.database.DatabaseException e){
                System.out.println("Firebase Exception");
                    textViewError.setVisibility(View.VISIBLE);
                    textViewError.setText("@string/ops_login");
            }
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}