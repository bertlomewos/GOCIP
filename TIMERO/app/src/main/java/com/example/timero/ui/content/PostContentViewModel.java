package com.example.timero.ui.content;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;

public class PostContentViewModel extends ViewModel {

    private final MutableLiveData<Post> _selectedPost = new MutableLiveData<>();
    public LiveData<Post> selectedPost = _selectedPost;

    public void setSelectedPost(Post post) {
        _selectedPost.setValue(post);
    }
}
