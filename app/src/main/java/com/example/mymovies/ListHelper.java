package com.example.mymovies;

import java.util.ArrayList;

public class ListHelper {

    public static Movie getMovie(String nome, ArrayList movies) throws Exception {
        ArrayList<Movie> movi = movies;
        for(Movie mu: movi){
            if(mu.getTitulo().equalsIgnoreCase(nome)){
                return mu;
            }
        }

        throw new Exception("Movie Not Found");
    }
    public static Movie getMovie(String nome, String ano, ArrayList movies) throws Exception {
        ArrayList<Movie> movi = movies;
        for(Movie mu: movi){
            if(mu.getTitulo().equalsIgnoreCase(nome) && ano.equals(String.valueOf(mu.getAno()))){

                return mu;
            }
        }

        throw new Exception("Movie Not Found");
    }
    public static Movie getMovieByID(String id, ArrayList movies) throws Exception{
        ArrayList<Movie> movi = movies;
        for(Movie mu: movi){
            if(mu.getID().equalsIgnoreCase(id)){
                return mu;
            }
        }

        throw new Exception("Movie ID not found");

    }


}
