package com.example.popularmovies.Api;

import com.example.popularmovies.Data.MoviesResponse;
import com.example.popularmovies.Data.ReviewResponse;
import com.example.popularmovies.Data.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<VideoResponse> getMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);
}
