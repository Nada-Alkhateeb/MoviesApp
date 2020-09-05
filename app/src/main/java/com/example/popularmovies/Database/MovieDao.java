package com.example.popularmovies.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.popularmovies.Data.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * from Movie")
    LiveData<List<Movie>> getAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Query("DELETE  from Movie where id = :movieId")
    int delete(int movieId);

    @Query("Select * from Movie where id = :movieId")
    public Movie getSingleMovie (int movieId);
}
