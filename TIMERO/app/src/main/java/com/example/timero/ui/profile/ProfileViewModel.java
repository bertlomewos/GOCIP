package com.example.timero.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.User;
import com.example.timero.data.repository.PostRepository;

import java.util.List;

public class ProfileViewModel extends ViewModel {

    private final PostRepository repository;
    private final MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> user = _user;
    public final LiveData<List<Post>> myPosts;

    public ProfileViewModel() {
        repository = PostRepository.getInstance();
        myPosts = repository.latestTopics;
        loadProfileData();
    }

    private void loadProfileData() {
        User currentUser = new User("SampleUser", "1.2M", "500K", "url_to_profile_pic");
        _user.setValue(currentUser);
    }
}