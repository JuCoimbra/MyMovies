package com.example.mymovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<UserMovies> userMovies;

    public UserAdapter(ArrayList<User> users, ArrayList<Movie> movies, ArrayList<UserMovies>userMovies) {
        this.users = users;
        this.movies = movies;
        this.userMovies = userMovies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private RecyclerView reciclerView;
        private RecyclerView.Adapter myAdapter;
        private RecyclerView.LayoutManager layoutManager;



        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.user_name_text_view);
            reciclerView = view.findViewById(R.id.user_name_text_view);

            layoutManager = new LinearLayoutManager(view.getContext());
            reciclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        ArrayList<String> filmesId = new ArrayList<>();
        ArrayList<Movie> userFavoriteMovies = new ArrayList<>();



        userFavoriteMovies.clear();

        for(UserMovies mu: userMovies) {

            filmesId.clear();
            filmesId.add(mu.getFilmeId_1());
            filmesId.add(mu.getFilmeId_2());
            filmesId.add(mu.getFilmeId_3());
            filmesId.add(mu.getFilmeId_4());

            if(mu.getUserID() == user.getId()){
                for(String id : filmesId){
                    if(id != null){
                        userFavoriteMovies.add(movies.get(Integer.parseInt(id)+1));
                    }
                }
            }
        }

        holder.myAdapter = new MovieAdapter(userFavoriteMovies);
        holder.textView.setText(user.getUsuario());
        holder.reciclerView.setAdapter(holder.myAdapter);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
