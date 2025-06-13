package com.example.timero.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Load the initial fragment
        if (savedInstanceState == null) {
            loadFragment(new LoginFragment());
        }
    }

    // Method to switch between Login and SignUp fragments
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.login_frame_layout, fragment);
        // Add to back stack to allow navigating back to login from signup
        if (!(fragment instanceof LoginFragment)) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
