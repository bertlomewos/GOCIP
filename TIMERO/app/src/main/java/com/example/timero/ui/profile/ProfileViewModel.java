package com.example.timero.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.User; // Import the User model

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    // LiveData for the User's profile information
    private final MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> user = _user;

    // LiveData for the list of posts by the user
    private final MutableLiveData<List<Post>> _myPosts = new MutableLiveData<>();
    public LiveData<List<Post>> myPosts = _myPosts;

    public ProfileViewModel() {
        loadProfileData();
    }

    private void loadProfileData() {
        // --- This is the missing part ---
        // Create a dummy user and set its value
        User currentUser = new User("SampleUser", "1.2M", "500K", "url_to_profile_pic");
        _user.setValue(currentUser);
        // --------------------------------

        // Load the user's posts
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("20", "My First Post", "", "url", "DOCUMENT", 102, 15));
        postList.add(new Post("21", "My Second Post", "", "url", "VIDEO", 205, 30));
        postList.add(new Post("22", "My Third Post", "", "url", "QUESTION", 510, 80));
        _myPosts.setValue(postList);
    }
}
