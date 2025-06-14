package com.example.timero.ui.content;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.timero.databinding.FragmentVideoBinding;
import com.example.timero.data.model.Post;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private ExoPlayer player;
    private PostContentViewModel viewModel;
    private String videoUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PostContentViewModel.class);
        Post currentPost = viewModel.selectedPost.getValue();
        if (currentPost != null && "VIDEO".equalsIgnoreCase(currentPost.getPostType())) {
            videoUrl = currentPost.getImageUrl();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUrl != null && !videoUrl.isEmpty()) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(requireContext()).build();
        binding.videoView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(true);
        player.prepare();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}