package com.example.popularmovies.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.popularmovies.Data.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private Database database;
    //MutableLiveData<List <Movie>> movieLiveData = new MutableLiveData<>();
    private LiveData<List<Movie>> movieLiveData;
    private boolean setAsFavorite;
    public Movie movieShek;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        //database=Database.getDatabase(application);
        database = Database.getDatabase(this.getApplication());

        movieLiveData = database.moviesDAO().getAllMovies();

    }
    public LiveData <List<Movie>> getMovies(){
        //new FavoriteAsyncTask().execute();
        return movieLiveData;
    }

    public void deleteMovies(Movie movie) {
        setAsFavorite = false;
        new MovieAsyncTask(database).execute(movie);
    }

    public void AddMovies(Movie movie) {
        setAsFavorite = true;
        new MovieAsyncTask(database).execute(movie);
    }

   /* public Movie GetMovie(Movie movie) {
        setAsFavorite = 3;
        new deleteFavoriteAsyncTask(database).execute(movie);
        return movieShek;
    }*/


    public class MovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private Database db;

        MovieAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            if (setAsFavorite == true){
                db.moviesDAO().insertMovie(movies[0]);
            }
            else if (setAsFavorite == false){
                db.moviesDAO().delete(movies[0].getId());
            } /*else {
                movieShek=db.moviesDAO().getSingleMovie(movies[0].getId());

            }*/
            return null;
        }
    }

}
