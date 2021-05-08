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

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>{

    Context context;
    List<MovieModel> list,fav_ids;


    public FavoriteAdapter(Context context, List<MovieModel> list) {

        this.context = context;
        this.list = list;
        fav_ids=new ArrayList<>();
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // set layout design to recyclerview
        return new FavoriteHolder(LayoutInflater.from(context).inflate(R.layout.row_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        // set data on items designs views to show on screen
        holder.review.setText(list.get(position).getMovie_review());
        holder.name.setText(list.get(position).getMovie_name());
        holder.actor.setText(list.get(position).getMovie_actor_actress());
        holder.year.setText(list.get(position).getMovie_year());
        holder.director.setText(list.get(position).getMovie_director());

        // if the item is favorite
        if(list.get(position).getMovie_favorite()){
            holder.check.setVisibility(View.VISIBLE);
            holder.uncheck.setVisibility(View.GONE);
            holder.favorite.setText("Favorite");
            // add item into not to fav
            fav_ids.add(list.get(position));
        }else{
            holder.check.setVisibility(View.GONE);
            holder.uncheck.setVisibility(View.VISIBLE);
            holder.favorite.setText("Not favorite");
        }
        // set rating stars and their values between 1-10 and show items on screen
        holder.rv_rating.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        holder.rv_rating.setAdapter(new RatingAdapter(context, Integer.parseInt(list.get(position).getMovie_rating())));
        holder.itemView.setOnClickListener(v -> {

            if(fav_ids.size()==0){
                    MovieModel model=list.get(position);
                    model.setMovie_favorite(false);
                    fav_ids.add(model);
                    holder.check.setVisibility(View.GONE);
                    holder.uncheck.setVisibility(View.VISIBLE);

                }else{
                    if(fav_ids.contains(list.get(position))){
                        MovieModel model=list.get(position);
                        fav_ids.remove(model);
                        holder.check.setVisibility(View.VISIBLE);
                        holder.uncheck.setVisibility(View.GONE);
                    }else{
                        MovieModel model=list.get(position);
                        model.setMovie_favorite(false);
                        fav_ids.add(model);
                        holder.check.setVisibility(View.GONE);
                        holder.uncheck.setVisibility(View.VISIBLE);
                    }
                }


        });
    }
    public List<MovieModel> getFav(){
        return fav_ids;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView name,year,director,actor,favorite,review;
        RecyclerView rv_rating;
        ImageView check,uncheck;
        public FavoriteHolder(@NonNull View itemView) {
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
