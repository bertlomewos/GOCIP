package com.example.timero.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    // Using MutableLiveData to notify observers of changes.
    private final MutableLiveData<Boolean> _isLoginSuccessful = new MutableLiveData<>();
    public LiveData<Boolean> isLoginSuccessful = _isLoginSuccessful;

    /**
     * A dummy login function.
     * In a real app, this would involve network requests, validation, etc.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void login(String username, String password) {
        // Dummy validation: just check if fields are not empty.
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Simulate a successful login
            _isLoginSuccessful.setValue(true);
        } else {
            _isLoginSuccessful.setValue(false);
        }
    }

    /**
     * Resets the login status, e.g., after the user has navigated away.
     */
    public void resetLoginStatus() {
        _isLoginSuccessful.setValue(null);
    }
}
