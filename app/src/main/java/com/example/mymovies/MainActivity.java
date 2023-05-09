package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar = findViewById(R.id.toolbar);
    ImageButton home_btn;
    ImageButton favorite_btn;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_btn = findViewById(R.id.btn_home);
        favorite_btn = findViewById(R.id.btn_favorites);
        userName = findViewById(R.id.user_name_text_view);

        home_btn.setBackgroundColor(Color.rgb(111, 138, 247));

        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new UserAdapter(listUsuarios, listMovies, listMoviesUsers);
        recyclerView.setAdapter(myAdapter);

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUsuarios.clear();

                for(DataSnapshot current_user: snapshot.getChildren()){
                    User usu = new User();
                    usu.setUsuario(current_user.child("nome").getValue().toString());
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

