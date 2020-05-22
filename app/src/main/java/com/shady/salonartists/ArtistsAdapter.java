package com.shady.salonartists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shady.salonartists.pojo.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistsAdapterViewHolder> {
    
    private List<Artist> mArtistsData = new ArrayList<>();


    public class ArtistsAdapterViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mPosterImageView;
        public final TextView mNameTextView;


        public ArtistsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mPosterImageView = itemView.findViewById(R.id.cv_poster_image);
            mNameTextView = itemView.findViewById(R.id.tv_name);

        }
    }

    @NonNull
    @Override
    public ArtistsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.posters_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ArtistsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsAdapterViewHolder holder, int position) {


        String posterURL = mArtistsData.get(position).getImage();

            Picasso.get()
                    .load(posterURL)
                    // To fit image into imageView
                    .fit()
                    // To prevent fade animation
                    .noFade()
                    .into(holder.mPosterImageView);

            holder.mNameTextView.setText(mArtistsData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (null == mArtistsData) return 0;
        return mArtistsData.size();
    }


    /**
     * Cache of the children views for a posters list item.
     */


    public void setArtistData(List<Artist> data) {

        mArtistsData = data;

        notifyDataSetChanged();
    }


}
