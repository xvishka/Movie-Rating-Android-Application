package com.example.movierating_madcw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.movierating_madcw.Model.MovieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieDB extends SQLiteOpenHelper {

    public static MovieDB instance;
    private static final String DB_NAME = "MovieRatingDB";
    private static final String DB_TABLE = "MyTable";
    private static final int DB_VERSION = 6;

    Context ctx;
    SQLiteDatabase myDb;

    public MovieDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx=context;
    }
    // get instances of MovieDB
    public static MovieDB getInstance(Context context) {
        if (instance == null) {
            instance = new MovieDB(context);
        }
        return instance;
    }

    // creating db table
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + DB_TABLE + " (movie_id INTEGER primary key AUTOINCREMENT, movie_name text, movie_year text, movie_director text, movie_actor_actress text,movie_rating text, movie_review text,movie_favorite INTEGER);");
            Log.i("Database", "Table Created");
        }catch (Exception exception){
            System.out.println("---------------"+exception);
        }

    }

    // check if the db has the same name table then drop the table and create new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE);
        onCreate(db);
    }


    public void insertData(MovieModel movieModel) {

        try {
            myDb = getWritableDatabase();

            // check if the row is fav or not, by default it is not favorite
            if(movieModel.getMovie_favorite()){

                myDb.execSQL("insert into "+DB_TABLE + "( movie_id,movie_name,movie_year,movie_director,movie_actor_actress,movie_rating,movie_review,movie_favorite)"
                        +"values(NULL,'"+movieModel.getMovie_name()+"','"+movieModel.getMovie_year()+"'" +
                        ",'"+movieModel.getMovie_director()+"','"+movieModel.getMovie_actor_actress()+"'" +
                        ",'"+movieModel.getMovie_rating()+"','"+movieModel.getMovie_review()+"',1)");
            }else{

                myDb.execSQL("insert into "+DB_TABLE + "( movie_id,movie_name,movie_year,movie_director,movie_actor_actress,movie_rating,movie_review,movie_favorite)"
                        +"values(NULL,'"+movieModel.getMovie_name()+"','"+movieModel.getMovie_year()+"'" +
                        ",'"+movieModel.getMovie_director()+"','"+movieModel.getMovie_actor_actress()+"'" +
                        ",'"+movieModel.getMovie_rating()+"','"+movieModel.getMovie_review()+"',0)");
            }


            Toast.makeText(ctx, "Data Saved", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            System.out.println("---------------"+e.getMessage());
            Toast.makeText(ctx, "This ID is already exists", Toast.LENGTH_SHORT).show();
        }
    }

    // get all stored movies from db
    public void getAll(GetAllMoviesCallBack callBack) {

        myDb = getReadableDatabase();
        Cursor cr =  myDb.rawQuery("Select * from " + DB_TABLE, null);
        // init list to store all data from db
   List<MovieModel> list=new ArrayList<>();

        // add items in list which you get from db
        while (cr.moveToNext()) {
            list.add(new MovieModel(
             cr.getString(0)
            ,cr.getString(1)
            ,cr.getString(2)
            ,cr.getString(3)
            ,cr.getString(4)
            ,cr.getString(5)
            ,cr.getString(6)
            ,cr.getInt(7)>0


          ));

        }
        // sort list alphabetically
        Collections.sort(list, (o1, o2) -> o1.getMovie_name().compareToIgnoreCase(o2.getMovie_name()));

        callBack.getAll(list);
    }

    // check is it fav or not
    public void searchFav(int search,GetAllMoviesCallBack callBack){

        myDb = getReadableDatabase();
        Cursor cr =  myDb.rawQuery("Select * from " + DB_TABLE, null);
        List<MovieModel> list=new ArrayList<>();

        while (cr.moveToNext()) {
            if(cr.getInt(7)==search){
                list.add(new MovieModel(
                        cr.getString(0)
                        ,cr.getString(1)
                        ,cr.getString(2)
                        ,cr.getString(3)
                        ,cr.getString(4)
                        ,cr.getString(5)
                        ,cr.getString(6)
                        ,cr.getInt(7)>0

                ));
            }

        }

        // sort alphabetically
        Collections.sort(list, (o1, o2) -> o1.getMovie_name().compareToIgnoreCase(o2.getMovie_name()));

        callBack.getAll(list);
    }

    // search items
    public void search(String search,GetAllMoviesCallBack callBack){

        myDb = getReadableDatabase();
        Cursor cr =  myDb.rawQuery("Select * from " + DB_TABLE, null);
        List<MovieModel> list=new ArrayList<>();

        // loop until all items
        while (cr.moveToNext()) {
            // check if the input text is from following fields
            if(cr.getString(1).toLowerCase().contains(search.toLowerCase())
            || cr.getString(2).toLowerCase().contains(search.toLowerCase())
                    || cr.getString(3).toLowerCase().contains(search.toLowerCase())
                    || cr.getString(4).toLowerCase().contains(search.toLowerCase())
                    || cr.getString(5).toLowerCase().contains(search.toLowerCase())
                    || cr.getString(6).toLowerCase().contains(search.toLowerCase())
            ){
                // add if match
            list.add(new MovieModel(
                    cr.getString(0)
                    ,cr.getString(1)
                    ,cr.getString(2)
                    ,cr.getString(3)
                    ,cr.getString(4)
                    ,cr.getString(5)
                    ,cr.getString(6)
                    ,cr.getInt(7)>0

            ));
            }

        }

        // sort
        Collections.sort(list, (o1, o2) -> o1.getMovie_name().compareToIgnoreCase(o2.getMovie_name()));


        callBack.getAll(list);
    }

    // update item
    public void update(MovieModel movieModel) {
        try {
            myDb = getWritableDatabase();

            // create object to send data to db which is edited
            ContentValues cv = new ContentValues();
            cv.put("movie_name", movieModel.getMovie_name());
            cv.put("movie_year", movieModel.getMovie_year());
            cv.put("movie_director", movieModel.getMovie_director());
            cv.put("movie_actor_actress", movieModel.getMovie_actor_actress());
            cv.put("movie_rating", movieModel.getMovie_rating());
            cv.put("movie_review", movieModel.getMovie_review());

            // check if fav then add 1 otherwise 0
            if(movieModel.getMovie_favorite()){
                cv.put("movie_favorite", 1);
            }else{
                cv.put("movie_favorite", 0);
            }

            // update data against id
            myDb.update(DB_TABLE, cv, "movie_id = ?", new String[]{movieModel.getMovie_id()});
            Toast.makeText(ctx, "Updated", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(ctx, "Could not find ID", Toast.LENGTH_SHORT).show();
        }
    }

    public interface GetAllMoviesCallBack{
        void getAll(List<MovieModel> movieModels);
    }
}
