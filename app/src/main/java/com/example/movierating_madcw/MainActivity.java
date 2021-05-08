package com.example.movierating_madcw;

/**
 * The Movie Rating program implements an application that
 * has five activities to add movie data, display, edit, add to favourite, search movies.
 *
 * @author  Kavindu Avishka
 * @version 1.0
 * @since   2021-05-06
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //onClick to evoke register movie activity.
        Button register_movie_btn = findViewById(R.id.register_movie_btn);
        register_movie_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterMovie.class);
                intent.putExtra("isEdit",false);
                startActivity(intent);
            }
        });

        //onClick to evoke display movie activity.
        Button display_movies_btn = findViewById(R.id.display_movies_btn);
        display_movies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayMovie.class);
                startActivity(intent);
            }
        });

        //onClick to evoke favourite movie activity.
        Button favourites_btn = findViewById(R.id.favourites_btn);
        favourites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavouriteMovie.class);
                startActivity(intent);
            }
        });

        //onClick to evoke favourite movie activity.
        Button edit_movies_btn = findViewById(R.id.edit_movies_btn);
        edit_movies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditMovie.class);
                startActivity(intent);
            }
        });

        //onClick to evoke search movie activity.
        Button search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchMovie.class);
                startActivity(intent);
            }
        });



    }
}


