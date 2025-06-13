package com.example.timero.ui.profile.adapter;

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
import java.util.Locale;

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyPostViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    // 1. Interface for handling clicks
    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    // 2. Constructor that accepts the click listener from the fragment
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
        // 3. Pass the post and listener to the ViewHolder to be bound
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

        public MyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCountText = itemView.findViewById(R.id.view_count_text);
        }

        // 4. Bind method sets data and attaches the click listener
        public void bind(final Post post, final OnPostClickListener listener) {
            viewCountText.setText(String.format(Locale.US, "%d", post.getViewCount()));
            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
