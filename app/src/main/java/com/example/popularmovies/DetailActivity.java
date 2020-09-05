package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Adapter.VideosAdapter;
import com.example.popularmovies.Api.ApiClient;
import com.example.popularmovies.Api.ApiInterface;
import com.example.popularmovies.Api.ApiKay;
import com.example.popularmovies.Data.Movie;
import com.example.popularmovies.Data.Video;
import com.example.popularmovies.Data.VideoResponse;
import com.example.popularmovies.Database.Database;
import com.example.popularmovies.Database.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public TextView mReview,mOverview,mVoteAverage,mReleaseDate,mTitle;
    public ImageView mPosterPath,mBackdropPath;
    private static final String TAG = DetailActivity.class.getSimpleName();
    public NestedScrollView Scroll;
    private RecyclerView recyclerView;
    public int mScroll=-1;
    private Movie movie;
    private ImageView hart;
    private Boolean fromClick=false;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Dtoolbar);
        setSupportActionBar(toolbar);
        DetailActivity.this.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.DetailRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        mTitle = (TextView) findViewById(R.id.title);
        mOverview = (TextView) findViewById(R.id.overview);
        mVoteAverage = (TextView) findViewById(R.id.vote);
        mReleaseDate = (TextView) findViewById(R.id.release_date);
        mReview=(TextView) findViewById(R.id.review);
        hart=(ImageView)findViewById(R.id.heart_button);

        mPosterPath = (ImageView) findViewById(R.id.image_poster);
        mBackdropPath = (ImageView) findViewById(R.id.movieBackImage);

        Intent intent = getIntent();

        movie = (Movie) intent.getSerializableExtra("Movie");
        //retrievedMovieFromIntent = getIntent().getParcelableExtra("movie");

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        setDetail(movie);
        setVideo(movie.getId());
        //mReview.setText(movie.getId());
        mReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReviewIntent=new Intent(DetailActivity.this,ReviewActivity.class);
                ReviewIntent.putExtra("id",movie.getId());
                ReviewIntent.putExtra("movie",movie);
                startActivity(ReviewIntent);
            }
        });

        new MovieAsyncTask().execute(movie);

        hart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromClick=true;
                new MovieAsyncTask().execute(movie);
            }
        });
    }

    public void setDetail(Movie movie){

        Picasso.get().load(movie.getPosterPath()).into(mPosterPath);
        Picasso.get().load(movie.getBackdropPath()).into(mBackdropPath);

        mTitle.setText(movie.getTitle());
        mOverview.setText(movie.getOverview());
        mVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        mReleaseDate.setText(movie.getReleaseDate());

    }

    public void setVideo(int videoId){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<VideoResponse> call=apiService.getMovieVideos(videoId, ApiKay.apiKey);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                List<Video> videos = response.body().getResults();
                recyclerView.setAdapter(new VideosAdapter(getApplicationContext(),videos));
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }


    private void markAsFavorite() { movieViewModel.AddMovies(movie); }

    private void unFavoriteMovie() {
        movieViewModel.deleteMovies(movie);
    }



    private class MovieAsyncTask extends AsyncTask<Movie, Void, Movie> {

        @Override
        protected Movie doInBackground(Movie... movie) {

            Database database= Database.getDatabase(DetailActivity.this);

            Movie singleMovie = database.moviesDAO().getSingleMovie(movie[0].getId());


            return singleMovie;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);

            if (fromClick) {
                if (movie != null) {
                    unFavoriteMovie();
                    hart.setImageResource(R.drawable.thumb_off);
                } else {
                    markAsFavorite();
                    hart.setImageResource(R.drawable.thumb_on);
                }
            } else {
                if (movie == null) {
                    hart.setImageResource(R.drawable.thumb_off);
                } else {
                    hart.setImageResource(R.drawable.thumb_on);
                }
            }


        }

    }
}
