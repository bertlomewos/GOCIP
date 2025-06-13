package com.example.timero.ui.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timero.data.model.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> _hotTopics = new MutableLiveData<>();
    public LiveData<List<Post>> hotTopics = _hotTopics;

    private final MutableLiveData<List<Post>> _latestTopics = new MutableLiveData<>();
    public LiveData<List<Post>> latestTopics = _latestTopics;

    public HomeViewModel() {
        loadDummyData();
    }

    private void loadDummyData() {
        // Dummy data for Hot Topics
        List<Post> hotTopicsList = new ArrayList<>();
        hotTopicsList.add(new Post("1", "Introduction to Jetpack Compose", "A deep dive into Android's new UI toolkit.", "url_to_image_1", "DOCUMENT", 12000, 560));
        hotTopicsList.add(new Post("2", "MVVM for Beginners", "Understand the most popular architecture pattern.", "url_to_image_2", "VIDEO", 11500, 480));
        hotTopicsList.add(new Post("3", "Android History Quiz", "Test your knowledge of Android's history.", "url_to_image_3", "QUESTION", 9800, 720));
        _hotTopics.setValue(hotTopicsList);

        // Dummy data for Latest Topics
        List<Post> latestTopicsList = new ArrayList<>();
        latestTopicsList.add(new Post("4", "Kotlin Coroutines", "Async programming made easy.", "url_to_image_4", "DOCUMENT", 150, 25));
        latestTopicsList.add(new Post("5", "What's New in Material Design 3", "Exploring the latest design guidelines.", "url_to_image_5", "VIDEO", 230, 45));
        latestTopicsList.add(new Post("6", "State Management in Android", "A comprehensive guide.", "url_to_image_6", "DOCUMENT", 400, 80));
        latestTopicsList.add(new Post("7", "Android 14 Features", "Discover the latest features for developers.", "url_to_image_7", "VIDEO", 500, 95));
        _latestTopics.setValue(latestTopicsList);
    }
}
