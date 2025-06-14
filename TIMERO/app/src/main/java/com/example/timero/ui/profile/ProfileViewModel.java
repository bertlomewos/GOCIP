package com.example.timero.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> user = _user;

    private final MutableLiveData<List<Post>> _myPosts = new MutableLiveData<>();
    public LiveData<List<Post>> myPosts = _myPosts;

    public ProfileViewModel() {
        loadProfileData();
    }

    private void loadProfileData() {
        User currentUser = new User("SampleUser", "1.2M", "500K", "url_to_profile_pic");
        _user.setValue(currentUser);

        // Load the user's posts
        List<Post> postList = new ArrayList<>();
        // Updated all Post constructor calls to include the 8th argument (questions list)
        postList.add(new Post("20", "My First Post", "", "url", "DOCUMENT", 102, 15, null));
        postList.add(new Post("21", "My Second Post", "", "url", "VIDEO", 205, 30, null));
        postList.add(new Post("22", "My Third Post", "", "url", "QUESTION", 510, 80, new ArrayList<>()));
        _myPosts.setValue(postList);
    }
}
