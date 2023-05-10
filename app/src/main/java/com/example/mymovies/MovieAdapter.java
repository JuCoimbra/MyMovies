package com.example.mymovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView movie_title;
        private TextView movie_year;
        private TextView movie_likes;
        private ImageButton btn_like;



        public ViewHolder(View view){
            super(view);
            movie_title = view.findViewById(R.id.movie_title);
            movie_year = view.findViewById(R.id.movie_year);
            movie_year = view.findViewById(R.id.movie_likes);
            movie_year = view.findViewById(R.id.btn_like);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movie_title.setText(movie.getTitulo());
        holder.movie_year.setText(movie.getAno());
        holder.movie_likes.setText(String.valueOf(movie.getLikes()));
        holder.btn_like.setOnClickListener(v -> {
            movie.likePlus();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
