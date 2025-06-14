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
import com.example.timero.databinding.FragmentSignUpBinding;
import com.example.timero.ui.main.MainActivity;

public class SignUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSignUpBinding binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSignUpBinding binding = FragmentSignUpBinding.bind(view);
        binding.signupButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Sign Up Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        binding.goToLoginText.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }
}