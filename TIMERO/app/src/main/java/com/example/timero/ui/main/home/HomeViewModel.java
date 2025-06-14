package com.example.timero.ui.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import com.example.timero.data.repository.PostRepository;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final PostRepository postRepository;

    // These LiveData objects now point directly to the repository's LiveData
    public final LiveData<List<Post>> hotTopics;
    public final LiveData<List<Post>> latestTopics;

    public HomeViewModel() {
        // Get the singleton instance of the repository
        postRepository = PostRepository.getInstance();
        hotTopics = postRepository.hotTopics;
        latestTopics = postRepository.latestTopics;
    }
}
