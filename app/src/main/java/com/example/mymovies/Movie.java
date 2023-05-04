package com.example.mymovies;

public class Movie {

    private String nome;
    private String ano;
    private int likes;

    public Movie(String nome, String ano, int likes) {
        this.nome = nome;
        this.ano = ano;
        this.likes = likes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
