package com.example.timero.ui.profile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.timero.R;
import com.example.timero.data.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyPostViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    public MyPostsAdapter(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_post, parent, false);
        return new MyPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.bind(currentPost, onPostClickListener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    static class MyPostViewHolder extends RecyclerView.ViewHolder {
        private final TextView viewCountText;
        private final ImageView image;

        public MyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCountText = itemView.findViewById(R.id.view_count_text);
            image = itemView.findViewById(R.id.my_post_image);
        }

        public void bind(final Post post, final OnPostClickListener listener) {
            viewCountText.setText(String.format(Locale.US, "%d", post.getViewCount()));

            // Use Glide to load the image from the URL
            Glide.with(itemView.getContext())
                    .load(post.getImageUrl())
                    .centerCrop()
                    .into(image);

            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
