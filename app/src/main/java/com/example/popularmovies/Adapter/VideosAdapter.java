package com.example.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;


import com.example.popularmovies.Data.Video;
import com.example.popularmovies.R;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    private String youtubeURl = "https://www.youtube.com/watch?v=";
    private Context mContext;
    private List<Video> mVideosList;

    public VideosAdapter(Context mContext, List<Video> mVideosList){
        this.mContext=mContext;
        this.mVideosList=mVideosList;
    }

    @NonNull
    @Override
    public VideosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_card_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.MyViewHolder holder, int position) {
        Log.e("Pos", String.valueOf(position));
        holder.videoTitle.setText(mVideosList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView videoTitle;
        private ImageView videoIcon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.video_title);
            videoIcon = itemView.findViewById(R.id.Video_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(
                            youtubeURl + mVideosList.get(getAdapterPosition()).
                                    getKey()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
