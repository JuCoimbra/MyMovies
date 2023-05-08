package com.example.mymovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<UserMovies> userMovies;

    public UserAdapter(ArrayList<User> users, ArrayList<Movie> movies, ArrayList<UserMovies>userMovies) {
        this.users = users;
        this.movies = movies;
        this.userMovies = userMovies;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_list, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        ArrayList<Movie> userFavoriteMovies = new ArrayList<>();

        userFavoriteMovies.clear();

        for(UserMovies mu: userMovies) {
            if(mu.getUserID() == user.getId()){
                for(Movie mov:movies){
                    if(mu.getMovieID() == mov.getID()){
                        userFavoriteMovies.add(mov);
                        break;
                    }
                }
            }
        }

        holder.bind(user.getNome(), userFavoriteMovies);
        userFavoriteMovies.clear();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
