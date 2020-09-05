package com.example.popularmovies.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Movie implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int keyId;

    @SerializedName("id")
    private int id;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

   // public String ImageUrl="https://image.tmdb.org/t/p/w600_and_h900_bestv2/";

   // public Movie(){}

  /*  protected Movie(Parcel in) {
       // movieId = in.readInt();
        popularity = in.readString();
        voteCount = in.readInt();
        posterPath = in.readString();
        id = in.readInt();
        backdropPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
        releaseDate = in.readString();
    }*/

      public Movie(String popularity,int voteCount,String posterPath,int id
            ,String backdropPath,String originalLanguage,String originalTitle,
                 String title,double voteAverage,String overview,String releaseDate){
        this.popularity=popularity;
        this.voteCount=voteCount;
        //this.video=video;
        this.posterPath=posterPath;
        this.id=id;
        //this.adult=adult;
        this.backdropPath=backdropPath;
        this.originalLanguage=originalLanguage;
        this.originalTitle=originalTitle;
        this.title=title;
        this.voteAverage=voteAverage;
        this.overview=overview;
        this.releaseDate=releaseDate;

    }


  /*  public Movie(String popularity,int voteCount,Boolean video,String posterPath,int id
            ,String backdropPath,String originalLanguage,String originalTitle,
                 String title,double voteAverage,String overview,String releaseDate){
        this.popularity=popularity;
        this.voteCount=voteCount;
        this.video=video;
        this.posterPath=posterPath;
        this.id=id;
        //this.adult=adult;
        this.backdropPath=backdropPath;
        this.originalLanguage=originalLanguage;
        this.originalTitle=originalTitle;
        this.title=title;
        this.voteAverage=voteAverage;
        this.overview=overview;
        this.releaseDate=releaseDate;

    }*/


  /*  public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };*/

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String ImageURL(){
        String ImageUrl="https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
        if (posterPath.startsWith("https")){
            return posterPath;
        }else {
            return ImageUrl+posterPath;
        }
    }


    public String getPosterPath() {
        return ImageURL();
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    //public boolean isAdult() {
       // return adult;
    //}

   // public void setAdult(boolean adult) {
      //  this.adult = adult;
   // }

    public String getBackdropPath() {
        String ImageUrl="https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
        return ImageUrl+backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

 /*   @Override
    public int describeContents() {
        return 0;
    }*/

  /*  @Override
    public void writeToParcel(Parcel parcel, int i) {
        //parcel.writeInt(movieId);
        parcel.writeString(popularity);
        parcel.writeInt(voteCount);
        //parcel.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        parcel.writeString(posterPath);
        parcel.writeInt(id);
        parcel.writeString(backdropPath);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeString(title);
        parcel.writeDouble(voteAverage);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }*/
}
