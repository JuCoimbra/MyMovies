package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usuarios = dbReference.child("usuarios");
    ArrayList listUsuarios = new ArrayList<>();
    ArrayList listMovies = new ArrayList<>();
    ArrayList listMoviesUsers = new ArrayList<>();
    Toolbar toolbar = findViewById(R.id.toolbar);
    ListView listViewUsers = findViewById(R.id.listViewUsers);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("user");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Olá");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUsuarios.clear();

                for(DataSnapshot current_user: snapshot.getChildren()){
                    User usu = new User();
                    usu.setNome(current_user.child("nome").getValue().toString());
                    usu.setSenha(current_user.child("senha").getValue().toString());
                    usu.setId(current_user.getKey().toString());
                    listUsuarios.add(usu);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReference.child("filmes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMovies.clear();
                for(DataSnapshot current_movie: snapshot.getChildren()){
                    Movie mov = new Movie();
                    mov.setTitulo(current_movie.child("titulo").getValue().toString());
                    mov.setAno(current_movie.child("ano").getValue().toString());
                    mov.setLikes(Integer.parseInt(current_movie.child("likes").getValue().toString()));
                    mov.setID(current_movie.getKey().toString());
                    listMovies.add(mov);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReference.child("usuario_filme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMoviesUsers.clear();

                for(DataSnapshot current_MU: snapshot.getChildren()){
                    UserMovies mu = new UserMovies();
                    mu.setMovieID(current_MU.child("filmeID").getValue().toString());
                    mu.setUserID(current_MU.child("usuarioID").getValue().toString());
                    listMoviesUsers.add(mu);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

