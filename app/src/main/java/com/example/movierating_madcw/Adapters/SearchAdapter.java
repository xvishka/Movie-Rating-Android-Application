package com.example.movierating_madcw.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating_madcw.Model.MovieModel;
import com.example.movierating_madcw.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{
    Context context;
    List<MovieModel> list;

    public SearchAdapter(Context context, List<MovieModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHolder((LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)));

    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.review.setText(list.get(position).getMovie_review());
        holder.name.setText(list.get(position).getMovie_name());
        holder.actor.setText(list.get(position).getMovie_actor_actress());
        holder.year.setText(list.get(position).getMovie_year());
        holder.director.setText(list.get(position).getMovie_director());
        holder.check.setVisibility(View.GONE);
        holder.uncheck.setVisibility(View.GONE);
        if(list.get(position).getMovie_favorite()){

            holder.favorite.setText("Favorite");
        }else{
            holder.favorite.setText("Not favorite");
        }
        holder.rv_rating.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        holder.rv_rating.setAdapter(new RatingAdapter(context, Integer.parseInt(list.get(position).getMovie_rating())));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder{
        TextView name,year,director,actor,favorite,review;
        RecyclerView rv_rating;
        ImageView check,uncheck;
        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            year=itemView.findViewById(R.id.year);
            director=itemView.findViewById(R.id.director);
            actor=itemView.findViewById(R.id.actor);
            favorite=itemView.findViewById(R.id.favourite);
            review=itemView.findViewById(R.id.review);
            rv_rating=itemView.findViewById(R.id.rv_rating);
            check=itemView.findViewById(R.id.check);
            uncheck=itemView.findViewById(R.id.uncheck);
        }
    }
}
