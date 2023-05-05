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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference();
        Button buttonLogin = findViewById(R.id.buttonLogin);
        EditText editTextLoginID = findViewById(R.id.editTextLoginName);
        EditText editTextPassword = findViewById(R.id.editTextLoginPassword);

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

                usersRef.child(login).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("user", login);
                            startActivity(intent);
                            finish();
                        }
                    }
                });



            }
        });
    }
}