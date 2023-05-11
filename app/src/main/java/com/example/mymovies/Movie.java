package com.example.mymovies;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movie {

    private String titulo;
    private String ano;
    private String id;
    private int likes;

    public Movie() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getLikes() {
        return likes;
    }
    public String getID() {
        return id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setID(String id) {
        this.id = id;
    }

    public void likePlus() {
        this.likes++;

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference movie = dbReference.child("filmes");

        movie.child(this.getID()).child("likes").setValue(this.getLikes());
    }

}
