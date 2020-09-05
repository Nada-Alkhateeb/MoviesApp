package com.example.popularmovies.Database;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.popularmovies.Data.Movie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//@android.arch.persistence.room.Database(entities = MovieModel.class, version = 1, exportSchema = false)

//@androidx.room.Database(entities = Movie.class,version = 1,exportSchema = false)
@androidx.room.Database(entities = Movie.class,version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract  MovieDao moviesDAO ();

    private static Database database;
   // private static final int NUMBER_OF_THREADS = 4;
   // static final ExecutorService databaseWriteExecutor =
           // Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Return an object of the database (1 single object should be used throughout the entire app)
    public static Database getDatabase (Context context) {
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), Database.class, "Favorite_Movie.db")
                    .fallbackToDestructiveMigration().build();
        }

        return database;
    }


}
