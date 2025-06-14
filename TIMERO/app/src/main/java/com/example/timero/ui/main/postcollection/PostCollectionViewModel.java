package com.example.timero.ui.main.postcollection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import com.example.timero.data.repository.PostRepository;

import java.util.List;

public class PostCollectionViewModel extends ViewModel {

    private final PostRepository repository;
    public final LiveData<List<Post>> allPosts;

    public PostCollectionViewModel() {
        repository = PostRepository.getInstance();
        allPosts = repository.latestTopics;
    }
}
