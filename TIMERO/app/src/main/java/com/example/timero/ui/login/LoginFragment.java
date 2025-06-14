package com.example.timero.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.timero.databinding.FragmentLoginBinding;
import com.example.timero.ui.main.MainActivity;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        binding.loginButton.setOnClickListener(v -> {
            String username = binding.usernameEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();
            loginViewModel.login(username, password);
        });
        binding.goToSignupText.setOnClickListener(v -> {
            if (getActivity() instanceof LoginActivity) {
                ((LoginActivity) getActivity()).loadFragment(new SignUpFragment());
            }
        });
        loginViewModel.isLoginSuccessful.observe(getViewLifecycleOwner(), isSuccessful -> {
            if (isSuccessful != null && isSuccessful) {
                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                loginViewModel.resetLoginStatus();
            } else if (isSuccessful != null) {
                Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}