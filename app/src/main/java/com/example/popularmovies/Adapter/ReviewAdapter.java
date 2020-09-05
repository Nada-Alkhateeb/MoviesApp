package com.example.popularmovies.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.Data.Review;
import com.example.popularmovies.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Review> mReviewList;

    public ReviewAdapter(Context mContext, List<Review> mReviewList){
        this.mContext=mContext;
        this.mReviewList=mReviewList;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_card_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder holder, int position) {

        holder.reviewAuthor.setText(mReviewList.get(holder.getAdapterPosition()).getAuthor());
        holder.review.setText(mReviewList.get(holder.getAdapterPosition()).getContent());

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView review;
        private TextView reviewAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.content);
            reviewAuthor = itemView.findViewById(R.id.author);
        }
    }
}
