package com.example.movierating_madcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.movierating_madcw.Adapters.ListAdapter;
import com.example.movierating_madcw.Model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class EditMovie extends AppCompatActivity implements ListAdapter.ListItemClickListener {
    RecyclerView rv_all;
    MovieDB db;
    List<MovieModel> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        rv_all=findViewById(R.id.rv_all);


        rv_all.setLayoutManager(new LinearLayoutManager(EditMovie.this));
        db=MovieDB.getInstance(this);
        mylist=new ArrayList<>();

        // get all items from sql lite db all logic is in MovieDB
        db.getAll(list->{
            mylist=list;
            rv_all.setAdapter(new ListAdapter(EditMovie.this,list,this,"EDIT"));
        });
    }

    @Override
    public void onItemClick(int position) {
        // here you will get click of item user want to edit and will get position of item you need to send update screen
        MovieModel model=mylist.get(position);
        // create intent to move next activity
        Intent intent=new Intent(EditMovie.this,RegisterMovie.class);
        // send data next screen to update and store on db
        intent.putExtra("isEdit",true);
        intent.putExtra("Name",model.getMovie_name());
        intent.putExtra("Id",model.getMovie_id());
        intent.putExtra("Year",model.getMovie_year());
        intent.putExtra("Rating",model.getMovie_rating());
        intent.putExtra("Review",model.getMovie_review());
        intent.putExtra("Actor",model.getMovie_actor_actress());
        intent.putExtra("Director",model.getMovie_director());
        intent.putExtra("Fav",model.getMovie_favorite());
        // transaction of screens
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // update list on screen while back from update item screen
        rv_all.getAdapter().notifyDataSetChanged();
    }
}