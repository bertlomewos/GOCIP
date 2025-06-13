package com.example.timero.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;
import com.example.timero.ui.main.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the ViewModel from the parent Activity
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        // Find views
        usernameEditText = view.findViewById(R.id.username_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        Button loginButton = view.findViewById(R.id.login_button);
        TextView goToSignUpText = view.findViewById(R.id.go_to_signup_text);

        // Set up click listeners
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            loginViewModel.login(username, password);
        });

        goToSignUpText.setOnClickListener(v -> {
            // Tell the parent activity to switch to the SignUpFragment
            if (getActivity() instanceof LoginActivity) {
                ((LoginActivity) getActivity()).loadFragment(new SignUpFragment());
            }
        });

        // Observe the login status from the ViewModel
        loginViewModel.isLoginSuccessful.observe(getViewLifecycleOwner(), isSuccessful -> {
            if (isSuccessful != null && isSuccessful) {
                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                // Navigate to MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                // Finish LoginActivity so the user can't go back to it
                getActivity().finish();
                // Reset the status in the ViewModel
                loginViewModel.resetLoginStatus();
            } else if (isSuccessful != null) {
                // Show an error message if login fails
                Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
