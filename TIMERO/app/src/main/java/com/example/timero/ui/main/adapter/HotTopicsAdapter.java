package com.example.timero.ui.main.adapter;

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


public class HotTopicsAdapter extends RecyclerView.Adapter<HotTopicsAdapter.HotTopicViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    public HotTopicsAdapter(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public HotTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_topic, parent, false);
        return new HotTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotTopicViewHolder holder, int position) {
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

    static class HotTopicViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final ImageView image;

        public HotTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            description = itemView.findViewById(R.id.post_description);
            image = itemView.findViewById(R.id.post_image);
        }

        public void bind(final Post post, final OnPostClickListener listener) {
            title.setText(post.getTitle());
            description.setText(post.getDescription());

            // Use Glide to load the image
            Glide.with(itemView.getContext())
                    .load(post.getImageUrl())
                    .centerCrop()
                    .into(image);

            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
