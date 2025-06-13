package com.example.timero.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timero.R;
import com.example.timero.data.model.Post;
import java.util.ArrayList;
import java.util.List;

public class LatestTopicsAdapter extends RecyclerView.Adapter<LatestTopicsAdapter.LatestTopicViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    // Interface for handling clicks
    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    // Constructor that accepts the click listener
    public LatestTopicsAdapter(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public LatestTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_topic, parent, false);
        return new LatestTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestTopicViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        // Pass the post and the listener to the ViewHolder
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

    static class LatestTopicViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public LatestTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title_grid);
        }

        // Bind method to set the data and the click listener
        public void bind(final Post post, final OnPostClickListener listener) {
            title.setText(post.getTitle());
            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
