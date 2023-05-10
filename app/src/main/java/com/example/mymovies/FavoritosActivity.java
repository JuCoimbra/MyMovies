package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class FavoritosActivity extends AppCompatActivity {


    ArrayList listUsuarios = new ArrayList<>();
    ArrayList listMovies = new ArrayList<>();
    ArrayList listMoviesUsers = new ArrayList<>();

    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();

    ImageButton home_btn;
    ImageButton favorite_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // ler json
        String userNameJson;
        String userId;
        String filePath = getFilesDir() + "fileuser.json";

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
        // ler json

        home_btn = findViewById(R.id.btn_home);
        favorite_btn = findViewById(R.id.btn_favorites);

        favorite_btn.setImageResource(R.drawable.baseline_account_white);
        home_btn.setImageResource(R.drawable.baseline_home);

        Button[] buttonAdd = {findViewById(R.id.buttonF1),findViewById(R.id.buttonF2),findViewById(R.id.buttonF3),findViewById(R.id.buttonF4)};
        Button buttonConf = findViewById(R.id.buttonConf);
        Button[] buttonCheck = {findViewById(R.id.buttonCheck1),findViewById(R.id.buttonCheck2),findViewById(R.id.buttonCheck3),findViewById(R.id.buttonCheck4),};
        ToggleButton[] buttonCurtir = {findViewById(R.id.buttonCurtir1),findViewById(R.id.buttonCurtir2),findViewById(R.id.buttonCurtir3),findViewById(R.id.buttonCurtir4)};
        Button[] buttonEdit = {findViewById(R.id.buttonEdit1),findViewById(R.id.buttonEdit2),findViewById(R.id.buttonEdit3),findViewById(R.id.buttonEdit4),};
        TextView[] textViewFNome = {findViewById(R.id.textViewF1nome),findViewById(R.id.textViewF2nome),findViewById(R.id.textViewF3nome),findViewById(R.id.textViewF4nome)};
        TextView[] textCurtidas = {findViewById(R.id.textViewC1),findViewById(R.id.textViewC2),findViewById(R.id.textViewC3),findViewById(R.id.textViewC4),};
        EditText[]  editTextFNome = {findViewById(R.id.editTextFilme1),findViewById(R.id.editTextFilme2),findViewById(R.id.editTextFilme3),findViewById(R.id.editTextFilme4)};
        EditText[] editTextAno = {findViewById(R.id.editTextFilmeAno1),findViewById(R.id.editTextFilmeAno2),findViewById(R.id.editTextFilmeAno3),findViewById(R.id.editTextFilmeAno4)};
        ImageView[] imageFilme = {findViewById(R.id.imageViewF1),findViewById(R.id.imageViewF2),findViewById(R.id.imageViewF3),findViewById(R.id.imageViewF4)};

        //pegar dados da db


        dbReference.child("filmes").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                listMovies.clear();
                for(DataSnapshot current_movie: task.getResult().getChildren()){
                    Movie mov = new Movie();
                    mov.setTitulo(current_movie.child("titulo").getValue().toString());
                    mov.setAno(current_movie.child("ano").getValue().toString());
                    mov.setLikes(Integer.parseInt(current_movie.child("likes").getValue().toString()));
                    mov.setID(current_movie.getKey().toString());
                    listMovies.add(mov);

                }
                dbReference.child("usuario_filme").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        listMoviesUsers.clear();

                        for(DataSnapshot current_MU: task.getResult().getChildren()){
                            UserMovies mu = new UserMovies();
                            if(current_MU.getKey().toString().equals(userId)){
                                for(int z = 0; z<4; z++){
                                    if(current_MU.child(String.valueOf(z+1)).getValue() != null){
                                        Movie mo;
                                        System.out.println(current_MU.child(String.valueOf(z+1)).getValue());
                                        try {
                                           mo = ListHelper.getMovieByID(current_MU.child(String.valueOf(z+1)).getValue().toString(), listMovies);
                                        } catch (Exception e) {
                                            return;
                                        }
                                        editTextFNome[z].setText(mo.getTitulo());
                                        buttonAdd[z].setVisibility(View.INVISIBLE);
                                        textViewFNome[z].setText(mo.getTitulo()+" ("+mo.getAno()+")");
                                        textViewFNome[z].setVisibility(View.VISIBLE);
                                        textCurtidas[z].setText("Curtidas: "+ mo.getLikes());
                                        textCurtidas[z].setVisibility(View.VISIBLE);
                                        imageFilme[z].setVisibility(View.VISIBLE);
                                        buttonEdit[z].setVisibility(View.VISIBLE);

                                    }
                                }
                            }
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
                    }
                });}});

        // pegar dados da db


        buttonConf.setOnClickListener(v-> {
            MovieAdapter movAdapter = new MovieAdapter(listMovies);

            for(int i = 0; i<4; i++) {
                String ano = "0000";
                if(!TextUtils.isEmpty(editTextAno[i].getText().toString().trim())){
                    ano = editTextAno[i].getText().toString().trim();
                }else {ano = "0000";}
                try {
                    if (!TextUtils.isEmpty(editTextFNome[i].getText().toString().trim())) {
                        String movname = editTextFNome[i].getText().toString().trim();

                        Movie mov;
                        if(ano == "0000"){mov = ListHelper.getMovie(movname,listMovies);
                        }else { mov = ListHelper.getMovie(movname,ano, listMovies);}
                        dbReference.child("usuario_filme").child(userId).child(String.valueOf(i+1)).setValue(mov.getID());
                    }else{
                        dbReference.child("usuario_filme").child(userId).child(String.valueOf(i+1)).removeValue();
                    }

                } catch (Exception e){
                    DatabaseReference movref = dbReference.child("filmes").child(String.valueOf(movAdapter.getItemCount()+1));
                    movref.child("titulo").setValue(editTextFNome[i].getText().toString().trim());
                    movref.child("ano").setValue(ano);
                    movref.child("likes").setValue(0);
                    dbReference.child("usuario_filme").child(userId).child(String.valueOf(i+1)).setValue(String.valueOf(movAdapter.getItemCount()));
                }

            }});


                for(int i =0; i<4; i++){
                    int I = i;
                    buttonAdd[I].setOnClickListener(v-> {
                        if (buttonAdd[I].getVisibility() == View.VISIBLE) {
                            editTextAno[I].setVisibility(View.VISIBLE);
                            buttonAdd[I].setVisibility(View.INVISIBLE);
                            editTextFNome[I].setVisibility(View.VISIBLE);
                            editTextFNome[I].requestFocus();
                            buttonCheck[I].setVisibility(View.VISIBLE);
                        }
                    });
                    buttonCheck[I].setOnClickListener(v-> {
                                if(!TextUtils.isEmpty(editTextFNome[I].getText().toString().trim())){
                                    String FilmeNome = editTextFNome[I].getText().toString().trim();
                                    String AnoFilme;
                                    Movie mov;
                                    int Likes;
                                    if(!TextUtils.isEmpty(editTextAno[I].getText().toString().trim())){
                                        AnoFilme = editTextAno[I].getText().toString().trim();
                                    }else { AnoFilme = "0000";}
                                    try{
                                        if(AnoFilme == "0000") {
                                          mov =  ListHelper.getMovie(FilmeNome,listMovies);
                                            AnoFilme = mov.getAno();
                                        }else{
                                            mov = ListHelper.getMovie(FilmeNome, AnoFilme, listMovies);
                                        }
                                        FilmeNome = mov.getTitulo();
                                        Likes = mov.getLikes();
                                    }catch(Exception e) {
                                        Likes = 0;
                                        System.out.println("Exception Mov");
                                    }
                                    editTextAno[I].setVisibility(View.GONE);
                                    editTextFNome[I].setVisibility(View.GONE);
                                    textViewFNome[I].setText(FilmeNome+" ("+AnoFilme+")");
                                    textViewFNome[I].setVisibility(View.VISIBLE);
                                    textCurtidas[I].setText("Curtidas: "+ String.valueOf(Likes));
                                    textCurtidas[I].setVisibility(View.VISIBLE);
                                    //buttonCurtir[I].setVisibility(View.VISIBLE);
                                    imageFilme[I].setVisibility(View.VISIBLE);
                                    buttonEdit[I].setVisibility(View.VISIBLE);
                                    buttonCheck[I].setVisibility(View.GONE);

                                }else {
                                    editTextAno[I].setVisibility(View.GONE);
                                    imageFilme[I].setVisibility(View.GONE);
                                    editTextFNome[I].setVisibility(View.GONE);
                                    buttonCheck[I].setVisibility(View.GONE);
                                    textViewFNome[I].setVisibility(View.GONE);
                                    //textCurtidas[I].setVisibility(View.GONE);
                                    buttonCurtir[I].setVisibility(View.GONE);
                                    buttonAdd[I].setVisibility(View.VISIBLE);
                                }
                            });
                        buttonEdit[I].setOnClickListener(v->{
                        imageFilme[I].setVisibility(View.GONE);
                        textViewFNome[I].setVisibility(View.GONE);
                        textCurtidas[I].setVisibility(View.GONE);
                        editTextAno[I].setVisibility(View.VISIBLE);
                       // buttonCurtir[I].setVisibility(View.GONE);
                        editTextFNome[I].setVisibility(View.VISIBLE);
                        editTextFNome[I].requestFocus();
                        buttonEdit[I].setVisibility(View.GONE);
                        buttonCheck[I].setVisibility(View.VISIBLE);
                    });

                }
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}