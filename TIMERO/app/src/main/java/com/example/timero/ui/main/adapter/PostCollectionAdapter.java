package com.example.timero.ui.main.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.timero.R;
import com.example.timero.data.model.Post;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PostCollectionAdapter extends RecyclerView.Adapter<PostCollectionAdapter.PostViewHolder> {

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_collection, parent, false);
        return new PostViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.title.setText(currentPost.getTitle());
        holder.description.setText(currentPost.getDescription());

        String imageUrl = currentPost.getImageUrl();
        Log.d("PostCollectionAdapter", "Raw imageUrl: " + imageUrl);

        if (imageUrl == null || imageUrl.isEmpty()) {
            holder.image.setImageResource(R.drawable.ic_upload_file);
            return;
        }

        Uri uri = Uri.parse(imageUrl);

        // For new photo picker URIs, do NOT convert
        if ("content".equals(uri.getScheme())) {
            // You can check for authority if needed, but just load as is
            Log.d("PostCollectionAdapter", "Loading content URI directly: " + uri);
        }

        Glide.with(holder.itemView.getContext())
                .load(uri)
                .placeholder(R.drawable.ic_upload_file)
                .error(R.drawable.ic_upload_file)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Load failed for URI: " + model, e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("GlideSuccess", "Loaded image URI: " + model);
                        return false;
                    }
                })
                .into(holder.image);
    }







    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final ImageView image;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            description = itemView.findViewById(R.id.post_description);
            image = itemView.findViewById(R.id.post_image);
        }
    }
}
