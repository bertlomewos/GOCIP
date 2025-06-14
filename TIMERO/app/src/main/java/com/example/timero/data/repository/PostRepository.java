package com.example.timero.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.timero.data.model.Answer;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PostRepository {

    private static volatile PostRepository INSTANCE;

    private final MutableLiveData<List<Post>> _hotTopics = new MutableLiveData<>();
    public final LiveData<List<Post>> hotTopics = _hotTopics;

    private final MutableLiveData<List<Post>> _latestTopics = new MutableLiveData<>();
    public final LiveData<List<Post>> latestTopics = _latestTopics;

    private PostRepository() {
        loadDummyData();
    }

    public static PostRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (PostRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PostRepository();
                }
            }
        }
        return INSTANCE;
    }

    public void addNewPost(Post post) {
        List<Post> currentList = _latestTopics.getValue();
        if (currentList != null) {
            ArrayList<Post> updatedList = new ArrayList<>(currentList);
            updatedList.add(0, post);
            _latestTopics.setValue(updatedList);
        }
    }

    /**
     * Finds a post by its ID across all lists and increments its like count.
     * In a real app, this would be a single API call.
     * @param postId The ID of the post to like.
     */
    public void incrementLikeCount(String postId) {
        // Update Hot Topics
        List<Post> hotTopicsList = _hotTopics.getValue();
        if (hotTopicsList != null) {
            for (Post post : hotTopicsList) {
                if (Objects.equals(post.getId(), postId)) {
                    post.setLikeCount(post.getLikeCount() + 1);
                    break;
                }
            }
            _hotTopics.setValue(hotTopicsList);
        }

        // Update Latest Topics
        List<Post> latestTopicsList = _latestTopics.getValue();
        if (latestTopicsList != null) {
            for (Post post : latestTopicsList) {
                if (Objects.equals(post.getId(), postId)) {
                    post.setLikeCount(post.getLikeCount() + 1);
                    break;
                }
            }
            _latestTopics.setValue(latestTopicsList);
        }
    }

    private void loadDummyData() {
        List<Question> quizQuestions = new ArrayList<>();
        List<Answer> answers1 = new ArrayList<>(Arrays.asList(new Answer("Cupcake", false), new Answer("Donut", false), new Answer("Eclair", true)));
        quizQuestions.add(new Question("Which version of Android came after Donut?", answers1));

        List<Answer> answers2 = new ArrayList<>(Arrays.asList(new Answer("Andy Rubin", true), new Answer("Steve Wozniak", false), new Answer("Tim Cook", false)));
        quizQuestions.add(new Question("Who is known as the father of Android?", answers2));

        List<Post> hotTopicsList = new ArrayList<>();
        hotTopicsList.add(new Post(UUID.randomUUID().toString(), "Introduction to Jetpack Compose", "A deep dive into Android's new UI toolkit.", "https://images.unsplash.com/photo-1617042375876-a13e36732a04?w=500", "DOCUMENT", 12000, 560, null));
        hotTopicsList.add(new Post(UUID.randomUUID().toString(), "MVVM for Beginners", "Understand the most popular architecture pattern.", "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "VIDEO", 11500, 480, null));
        hotTopicsList.add(new Post(UUID.randomUUID().toString(), "Android History Quiz", "Test your knowledge of Android's history.", "https://images.unsplash.com/photo-1620121684816-7f416b74446b?w=500", "QUESTION", 9800, 720, quizQuestions));
        _hotTopics.setValue(hotTopicsList);

        List<Post> latestTopicsList = new ArrayList<>();
        latestTopicsList.add(new Post(UUID.randomUUID().toString(), "Kotlin Coroutines", "Async programming made easy.", "https://images.unsplash.com/photo-1599507593499-a3f7d7d97667?w=500", "DOCUMENT", 150, 25, null));
        latestTopicsList.add(new Post(UUID.randomUUID().toString(), "What's New in Material Design 3", "Exploring the latest design guidelines.", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4", "VIDEO", 230, 45, null));
        _latestTopics.setValue(latestTopicsList);
    }
}
