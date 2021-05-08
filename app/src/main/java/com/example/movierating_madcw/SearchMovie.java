package com.example.movierating_madcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movierating_madcw.Adapters.ListAdapter;
import com.example.movierating_madcw.Adapters.SearchAdapter;

public class SearchMovie extends AppCompatActivity implements ListAdapter.ListItemClickListener {
    RecyclerView rv_all;
    MovieDB db;
    Button btn;
    EditText edt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        rv_all=findViewById(R.id.rv_all);
        btn=findViewById(R.id.button);
        edt_search=findViewById(R.id.editTextTextPersonName5);

        // set layout to recyclerview
        rv_all.setLayoutManager(new LinearLayoutManager(SearchMovie.this));
        // init db
        db=MovieDB.getInstance(this);
        // btn to search items
        btn.setOnClickListener(v -> {
            // get text from edit text
            String search=edt_search.getText().toString();
            if(!search.isEmpty()){
                // get searched data against searched text
            db.search(search,list->{
                // get searched data
                rv_all.setAdapter(new SearchAdapter(SearchMovie.this,list));
            });
            }else{

                Toast.makeText(this, "Enter Something", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onItemClick(int position) {

    }
}