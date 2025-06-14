// TIMERO/app/src/main/java/com/example/timero/api/ApiService.java
package com.example.timero.api;

import com.example.timero.data.model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

// This interface defines the API endpoints and their corresponding HTTP methods.
public interface ApiService {

    // Defines a GET request to the "api/posts/latest" endpoint.
    // It's expected to return a list of Post objects.
    @GET("api/posts/latest")
    Call<List<Post>> getLatestPosts();

    // You would add other API calls here as needed, e.g.:
    // @POST("api/users/login")
    // Call<User> loginUser(@Body LoginRequest request);
}
    