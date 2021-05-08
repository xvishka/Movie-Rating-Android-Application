package com.example.movierating_madcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.movierating_madcw.Adapters.ListAdapter;
import com.example.movierating_madcw.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class DisplayMovie extends AppCompatActivity implements ListAdapter.ListItemClickListener {
RecyclerView rv_all;
MovieDB db;
Button favourites_btn;
ListAdapter adapter;
List<MovieModel> fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_movie);
        rv_all=findViewById(R.id.rv_all);
        favourites_btn=findViewById(R.id.favourites_btn);
        fav=new ArrayList<>();

        // set layout manager to recyclerview
        rv_all.setLayoutManager(new LinearLayoutManager(DisplayMovie.this));
        // init db
        db=MovieDB.getInstance(this);
        db.getAll(list->{
            // show all items on screen with recycler view
            adapter=new ListAdapter(DisplayMovie.this,list,this,"ADD_FAV");
            rv_all.setAdapter(adapter);
        });

        favourites_btn.setOnClickListener(v -> {
            if(adapter.getFav().size()>0){
          fav= adapter.getFav();
          for(MovieModel model:fav){
              db.update(model);
          }

          finish();

            }else {
                Toast.makeText(this, "Select at least One", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }

}