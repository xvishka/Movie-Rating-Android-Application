package com.example.movierating_madcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.movierating_madcw.Adapters.FavoriteAdapter;
import com.example.movierating_madcw.Adapters.ListAdapter;
import com.example.movierating_madcw.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMovie extends AppCompatActivity implements ListAdapter.ListItemClickListener {
RecyclerView rv_all;
Button save_favourite_btn;
MovieDB db;
FavoriteAdapter adapter;
List<MovieModel> fav,allfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);

        rv_all=findViewById(R.id.rv_all);
        save_favourite_btn=findViewById(R.id.save_favourite_btn);
        rv_all.setLayoutManager(new LinearLayoutManager(this));

        db=MovieDB.getInstance(this);

        // init lists of unselected favorites and total favorites
        fav=new ArrayList<>();
        allfav=new ArrayList<>();

        // 1 for favorite, o for not
        db.searchFav(1,list->{
            allfav=list;
            adapter=new FavoriteAdapter(this,allfav);
            rv_all.setAdapter(adapter);
        });

        // update favorites to non favorites
        save_favourite_btn.setOnClickListener(v -> {
            fav=adapter.getFav();
            if(fav.size()>0){
                for(MovieModel model:fav){
                    db.update(model);
                    allfav.remove(model);
                }
               finish();

            }else{
                Toast.makeText(this, "Select at least one", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onItemClick(int position) {

    }
}