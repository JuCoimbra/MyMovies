package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    Toolbar toolbar = findViewById(R.id.toolbar);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("user");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Olá");
    }
}