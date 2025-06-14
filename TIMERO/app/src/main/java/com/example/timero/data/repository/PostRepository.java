package com.example.timero.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.timero.data.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;

public class PostRepository {
    private static PostRepository instance;
    private final PostApiService apiService;
    private final MutableLiveData<List<Post>> _latestTopics = new MutableLiveData<>();
    public LiveData<List<Post>> latestTopics = _latestTopics;

    private PostRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.221:5219/") // Replace with your API URL, e.g., http://localhost:5000/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(PostApiService.class);
        fetchLatestPosts();
    }

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    private void fetchLatestPosts() {
        apiService.getLatestPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    _latestTopics.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Log error or notify UI
            }
        });
    }

    public void addNewPost(Post post) {
        apiService.createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    fetchLatestPosts(); // Refresh posts
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Log error
            }
        });
    }

    interface PostApiService {
        @GET("api/posts/latest")
        Call<List<Post>> getLatestPosts();

        @POST("api/posts")
        Call<Post> createPost(@Body Post post);
    }
}