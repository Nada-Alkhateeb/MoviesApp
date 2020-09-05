package com.example.popularmovies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.popularmovies.Adapter.MoviesAdapter;
import com.example.popularmovies.Api.ApiClient;
import com.example.popularmovies.Api.ApiInterface;
import com.example.popularmovies.Api.ApiKay;
import com.example.popularmovies.Data.Movie;
import com.example.popularmovies.Data.MoviesResponse;
import com.example.popularmovies.Database.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private int SortBy=0;
    private MovieViewModel movieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.this.setTitle("Popular Movies");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorLight));

        if (ApiKay.apiKey.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected)popular();
    }

    public void popular(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(ApiKay.apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }

        });

    }

    public void TopRated(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(ApiKay.apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }

        });

    }

    public void favorite(){

        movieViewModel.getMovies().observe(MainActivity.this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        if (SortBy==1){
            menu.findItem(R.id.menu_sort_top_rated).setChecked(true);
        }
        menu.findItem(R.id.menu_sort_popularity).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        switch (itemId){
            case R.id.menu_sort_popularity:
                popular();
                item.setChecked(true);
                MainActivity.this.setTitle(R.string.Popular_Movie_Title);
                break;

            case R.id.menu_sort_top_rated:
                TopRated();
                item.setChecked(true);
                MainActivity.this.setTitle(R.string.Top_Rated_Title);
                SortBy=1;
                break;
            case R.id.menu_sort_favorite:
                favorite();
                item.setChecked(true);
                MainActivity.this.setTitle(R.string.Favorite_Title);
                SortBy=2;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
