package com.example.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.Adapter.MoviesAdapter;
import com.example.popularmovies.Adapter.ReviewAdapter;
import com.example.popularmovies.Api.ApiClient;
import com.example.popularmovies.Api.ApiInterface;
import com.example.popularmovies.Api.ApiKay;
import com.example.popularmovies.Data.Movie;
import com.example.popularmovies.Data.MoviesResponse;
import com.example.popularmovies.Data.Review;
import com.example.popularmovies.Data.ReviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    private static final String TAG = ReviewActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorLight));

        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        final Movie movie=(Movie) intent.getSerializableExtra("movie");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ReviewIntent=new Intent(ReviewActivity.this,DetailActivity.class);
                ReviewIntent.putExtra("Movie",movie);
                startActivity(ReviewIntent);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.reviewRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        String str=String.valueOf(id);
        ReviewActivity.this.setTitle(str);
        reviews(id);

    }

    public void reviews(int id){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ReviewResponse> call = apiService.getMovieReviews(id, ApiKay.apiKey);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                List<Review> reviews = response.body().getResults();
                recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(),reviews));
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
