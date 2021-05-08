package com.example.movierating_madcw.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating_madcw.Model.MovieModel;
import com.example.movierating_madcw.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    Context context;
    List<MovieModel> list;
    ListItemClickListener listener;
    String type;
    List<MovieModel> fav_ids;


    public ListAdapter(Context context, List<MovieModel> list,ListItemClickListener listener,String type) {
        this.context = context;
        this.list = list;
        this.listener=listener;
        this.type=type;
        fav_ids=new ArrayList<>();


    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListHolder(LayoutInflater.from(context).inflate(R.layout.row_item,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.review.setText(list.get(position).getMovie_review());
        holder.name.setText(list.get(position).getMovie_name());
        holder.actor.setText(list.get(position).getMovie_actor_actress());
        holder.year.setText(list.get(position).getMovie_year());
        holder.director.setText(list.get(position).getMovie_director());
        if(type.equals("EDIT")){
            holder.check.setVisibility(View.GONE);
            holder.uncheck.setVisibility(View.GONE);
        }
        if(list.get(position).getMovie_favorite()){
            if(type.equals("ADD_FAV")){
                holder.check.setVisibility(View.GONE);
                holder.uncheck.setVisibility(View.GONE);
            }
            holder.favorite.setText("Favorite");
        }else{
            holder.favorite.setText("Not favorite");
        }

        holder.itemView.setOnClickListener(v -> {
            if(type.equals("ADD_FAV")){
            if(!list.get(position).getMovie_favorite()){
                MovieModel model=new MovieModel();
                model.setMovie_actor_actress(list.get(position).getMovie_actor_actress());
                model.setMovie_name(list.get(position).getMovie_name());
                model.setMovie_favorite(true);
                model.setMovie_id(list.get(position).getMovie_id());
                model.setMovie_rating(list.get(position).getMovie_rating());
                model.setMovie_review(list.get(position).getMovie_review());
                model.setMovie_year(list.get(position).getMovie_year());
                model.setMovie_director(list.get(position).getMovie_director());


                if(fav_ids.size()==0){
                    fav_ids.add(model);
                    System.out.println("---------------------------1-----");
                    holder.check.setVisibility(View.VISIBLE);
                    holder.uncheck.setVisibility(View.GONE);

                }else{
                    /*  for(int i=0;i<fav_ids.size();i++){
                        if(model.getMovie_id().equals(fav_ids.get(i).getMovie_id())){
                            fav_ids.remove(model);
                            holder.check.setVisibility(View.GONE);
                            System.out.println("---------------------------2-----");
                            holder.uncheck.setVisibility(View.VISIBLE);
                        }
                    }*/
                    int check=containsName(fav_ids,model);
                    if(check>=0){
                        fav_ids.remove(check);
                        holder.check.setVisibility(View.GONE);
                        holder.uncheck.setVisibility(View.VISIBLE);
                    }else{
                        fav_ids.add(model);
                        holder.check.setVisibility(View.VISIBLE);
                        holder.uncheck.setVisibility(View.GONE);
                    }
                }


            }else{
                Toast.makeText(context, "Already Favorite", Toast.LENGTH_SHORT).show();
            }
            }
            else{

                listener.onItemClick(position);

            }
        });
        holder.rv_rating.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        holder.rv_rating.setAdapter(new RatingAdapter(context, Integer.parseInt(list.get(position).getMovie_rating())));

    }

    public int containsName(final List<MovieModel> list, final MovieModel model){
        return Collections.binarySearch(list, model, new Comparator<MovieModel>() {

            @Override
            public int compare(MovieModel o1, MovieModel o2) {
                return o1.getMovie_id().compareTo(o2.getMovie_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public List<MovieModel> getFav(){
        return fav_ids;
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView name,year,director,actor,favorite,review;
        RecyclerView rv_rating;
        ImageView check,uncheck;
        public ListHolder(@NonNull View itemView) {
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
    public interface ListItemClickListener{
        void onItemClick(int position);
    }

}
