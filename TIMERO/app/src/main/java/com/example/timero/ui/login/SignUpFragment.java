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

import com.example.timero.R;
import com.example.timero.ui.main.MainActivity;

public class SignUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button signUpButton = view.findViewById(R.id.signup_button);
        TextView goToLoginText = view.findViewById(R.id.go_to_login_text);

        signUpButton.setOnClickListener(v -> {
            // For now, we will just navigate to MainActivity to show the flow.
            // A real app would have validation and user creation logic here.
            Toast.makeText(getContext(), "Sign Up Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        goToLoginText.setOnClickListener(v -> {
            // Use the FragmentManager to pop the back stack, returning to LoginFragment
            getParentFragmentManager().popBackStack();
        });
    }
}
