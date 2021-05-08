package com.example.movierating_madcw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movierating_madcw.Model.MovieModel;

import java.util.UUID;

public class RegisterMovie extends AppCompatActivity {
EditText edt_title,edt_year,edt_director,edt_actor,edt_rating,edt_review;
Button btn_save;
MovieDB db;
boolean isEdit;
MovieModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        edt_actor=findViewById(R.id.editTextTextPersonName3);
        edt_title=findViewById(R.id.editTextTextPersonName);
        edt_year=findViewById(R.id.editTextNumber);
        edt_director=findViewById(R.id.editTextTextPersonName2);
        edt_rating=findViewById(R.id.editTextNumber2);
        edt_review=findViewById(R.id.editTextTextPersonName4);

        // check this screen come from edit screen or main because we are creating new items and also update them on this screen
        isEdit=getIntent().getBooleanExtra("isEdit",false);
        // if screen comes from edit then go inside condition
        if(isEdit){
            // add items into MoiveModel's object
            model=new MovieModel(
            getIntent().getStringExtra("Id"),
            getIntent().getStringExtra("Name"),
            getIntent().getStringExtra("Year"),
            getIntent().getStringExtra("Director"),
            getIntent().getStringExtra("Actor"),
            getIntent().getStringExtra("Rating"),
            getIntent().getStringExtra("Review"),
            getIntent().getBooleanExtra("Fav",false));

            edt_title.setText(model.getMovie_name());
            edt_review.setText(model.getMovie_review());
            edt_rating.setText(model.getMovie_rating());
            edt_year.setText(model.getMovie_year());
            edt_actor.setText(model.getMovie_actor_actress());
            edt_director.setText(model.getMovie_director());

        }
        //init db
        db=MovieDB.getInstance(RegisterMovie.this);
        btn_save=findViewById(R.id.save_btn);
        // set click on save button
        btn_save.setOnClickListener(v -> {
            // get all data from edit texts
            String name=edt_title.getText().toString();
            String year=edt_year.getText().toString();
            String director=edt_director.getText().toString();
            String actor=edt_actor.getText().toString();
            String rating=edt_rating.getText().toString();
            String review=edt_review.getText().toString();
            // check is edittext giving empty value of not
            if(!name.isEmpty() ||
                    !year.isEmpty() ||
                    !director.isEmpty() ||
                    !actor.isEmpty() ||
                    !rating.isEmpty() ||
                    !review.isEmpty()
            ){
                // if edit texts are not empty then check if rating is between 1-10
                if(Integer.parseInt(rating)>10 || Integer.parseInt(rating)<1){
                    Toast.makeText(this, "Enter Rating Under 1-10", Toast.LENGTH_SHORT).show();
                }else{
                    // check here is year greater then 1895 and also check if year_edit get string items length 4
                    if(year.length()>4 || year.length()<4 || Integer.parseInt(year)<1895){
                        Toast.makeText(this, "Enter Year above than 1895 and should contain 4 characters", Toast.LENGTH_SHORT).show();
                    }else{

                if(isEdit){
                    // update items here
                    MovieModel model1=new MovieModel(model.getMovie_id(),name,year,director,actor,rating,review,model.getMovie_favorite());
                    db.update(model1);
                    startActivity(new Intent(RegisterMovie.this,EditMovie.class));
                    finish();
                }else{
                    // if screen is not from edit then insert into db
               MovieModel model=new MovieModel(UUID.randomUUID().toString(),name,year,director,actor,rating,review,false);
             db.insertData(model);

                }
                    }
                }
            }else{
                // if user miss something in edit text
                Toast.makeText(this, "Something missing", Toast.LENGTH_SHORT).show();
            }
        });
    }


}