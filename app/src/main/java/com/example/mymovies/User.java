package com.example.mymovies;

import java.util.HashMap;
import java.util.Objects;

public class User {

    private String usuario;
    private String senha;
    private String id;

    public User(){
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}


