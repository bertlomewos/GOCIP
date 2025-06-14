package com.example.timero.api;

import com.example.timero.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

// This should be an interface, not a class
public interface postApi {

    @POST("api/Post")
    Call<Post> insertPost(@Body Post post);

    @GET("api/Post")
    Call<List<Post>> getAllPost();
}
