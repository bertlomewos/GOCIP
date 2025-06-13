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

public class HotTopicsAdapter extends RecyclerView.Adapter<HotTopicsAdapter.HotTopicViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    // This is the interface that allows the Fragment to listen for clicks
    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    // This is the constructor that was missing. It accepts the listener.
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
        // We pass the post and the listener to the ViewHolder
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

        public HotTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            description = itemView.findViewById(R.id.post_description);
        }

        // The bind method sets the data and attaches the click listener to the item view
        public void bind(final Post post, final OnPostClickListener listener) {
            title.setText(post.getTitle());
            description.setText(post.getDescription());
            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
