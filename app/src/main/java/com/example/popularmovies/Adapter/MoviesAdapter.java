package com.example.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.Data.Movie;
import com.example.popularmovies.DetailActivity;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext, List<Movie> movieList){
        this.mContext=mContext;
        this.movieList=movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(movieList.get(position).getTitle());
        Picasso.get().load(movieList.get(position).getPosterPath()).into(holder.thumbnail);
        Log.i("msg", "link is " + movieList.get(position).getPosterPath());

    }

    @Override
    public int getItemCount() {
        return movieList==null?0:movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.Movie_title);
            thumbnail=(ImageView) itemView.findViewById(R.id.Movie_Poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos= getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem =movieList.get(pos);
                        Intent intent=new Intent(mContext, DetailActivity.class);
                        intent.putExtra("Movie",(Serializable) movieList.get(pos));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "You clicked"+ clickedDataItem.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
