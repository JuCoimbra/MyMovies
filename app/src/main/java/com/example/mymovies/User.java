package com.example.mymovies;

import java.util.HashMap;
import java.util.Objects;

public class User {

    private String usuario;
    private String senha;
    private int id;

<<<<<<< Updated upstream
    public User(String nome, String senha, int id) {
        this.nome = nome;
        this.senha = senha;
        this.id = id;
=======
    public User(String nome, String senha, String id) {
        this.usuario = nome;
        this.senha = senha;
        this.id = id;

    }

    public User(HashMap<Objects,String> a, String id){
        this.usuario = a.get("usuario");
        this.senha = a.get("senha");
        this.id = id;
>>>>>>> Stashed changes
    }

    // getters e setters para nome, senha e id

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}


