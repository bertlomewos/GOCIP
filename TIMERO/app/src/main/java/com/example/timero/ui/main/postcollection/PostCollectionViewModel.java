package com.example.timero.ui.main.postcollection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import java.util.ArrayList;
import java.util.List;

public class PostCollectionViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> _posts = new MutableLiveData<>();
    public LiveData<List<Post>> posts = _posts;

    public void loadPostsForTopic(String topicId) {
        // In a real app, you would fetch this from a repository based on the topicId
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("10", "Advanced Coroutines", "Exploring channels and flows.", "url", "DOCUMENT", 560, 110));
        postList.add(new Post("11", "Testing ViewModels", "Best practices for unit testing.", "url", "VIDEO", 830, 150));
        postList.add(new Post("12", "Dependency Injection with Hilt", "Getting started with Hilt.", "url", "DOCUMENT", 1200, 250));
        postList.add(new Post("13", "Navigation Component Deep Dive", "Nested graphs and custom transitions.", "url", "QUESTION", 780, 90));
        postList.add(new Post("14", "Building Custom Views", "How to create your own views.", "url", "VIDEO", 950, 180));
        _posts.setValue(postList);
    }
}
