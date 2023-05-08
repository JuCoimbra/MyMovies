package com.example.mymovies;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserViewHolder extends RecyclerView.ViewHolder{
    private TextView userName;
    private RecyclerView favoriteMoviesRecyclerView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.user_name_text_view);
        favoriteMoviesRecyclerView = itemView.findViewById(R.id.favorite_movies_recycler_view);
    }

    public void bind(String name, ArrayList movies) {
        userName.setText(name);
        favoriteMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        favoriteMoviesRecyclerView.setAdapter(new MovieAdapter(movies));
    }

}
