package com.example.mymovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieViewHolder extends RecyclerView.ViewHolder{
    public TextView movieTitle;
    public TextView movieYear;
    public ImageButton btnLike;

    public MovieViewHolder(View itemView) {
        super(itemView);
        movieTitle = itemView.findViewById(R.id.movie_title);
        movieYear = itemView.findViewById(R.id.movie_year);
        btnLike = itemView.findViewById(R.id.btn_like);
    }
}
