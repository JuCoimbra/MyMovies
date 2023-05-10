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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    //RecyclerView.LayoutManager layoutManager;
    //Toolbar toolbar = findViewById(R.id.toolbar);
    ImageButton home_btn;
    ImageButton favorite_btn;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userNameJson;
        String userId;
        String filePath = getFilesDir() + "fileuser.json";

        home_btn = findViewById(R.id.btn_home);
        favorite_btn = findViewById(R.id.btn_favorites);
        userName = findViewById(R.id.user_name_text_view);
        recyclerView = findViewById(R.id.recicleViewMain);

        home_btn.setImageResource(R.drawable.baseline_home_white);
        favorite_btn.setImageResource(R.drawable.baseline_account);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            String json = reader.readLine();
            JSONObject obj = new JSONObject(json);
            userNameJson = obj.getString("usuario");
            userId = obj.getString("id");
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        userName.setText(userNameJson);

        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new UserAdapter(listUsuarios, listMovies, listMoviesUsers);
        recyclerView.setAdapter(myAdapter);

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUsuarios.clear();

                for(DataSnapshot current_user: snapshot.getChildren()){
                    String nome = current_user.child("nome").getValue().toString();

                    if(nome != userNameJson){
                        User usu = new User();
                        usu.setUsuario(nome);
                        usu.setSenha(current_user.child("senha").getValue().toString());
                        usu.setId(current_user.getKey().toString());
                        listUsuarios.add(usu);
                    }
                };

                myAdapter.notifyDataSetChanged();
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
                    try {
                        mov.setTitulo(current_movie.child("titulo").getValue().toString());
                    }catch (Exception e){
                        mov.setTitulo("default_title");
                    }
                    try {
                        mov.setAno(current_movie.child("ano").getValue().toString());
                    }catch (Exception e){
                        mov.setAno("0000");
                    }
                    try {
                        mov.setLikes(Integer.parseInt(current_movie.child("likes").getValue().toString()));
                    }catch (Exception e){
                        mov.setLikes(0);
                    }
                    mov.setID(current_movie.getKey().toString());
                    listMovies.add(mov);
                }

                myAdapter.notifyDataSetChanged();
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
                    String strmu[] = new String[5];
                    for(int i = 1; i<5; i++){
                        try{
                            strmu[i] = current_MU.child(String.valueOf(i)).getValue().toString();
                        }catch (java.lang.NullPointerException e){
                            strmu[i] = null;
                        }
                    }
                    mu.setFilmeId_1(strmu[1]);
                    mu.setFilmeId_2(strmu[2]);
                    mu.setFilmeId_3(strmu[3]);
                    mu.setFilmeId_4(strmu[4]);
                    listMoviesUsers.add(mu);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

