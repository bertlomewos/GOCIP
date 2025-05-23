package com.example.gocip.Activity.Login_SignUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocip.Activity.Home.HomeActivity;
import com.example.gocip.R;
import com.example.gocip.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    FragmentLoginBinding binding;

    TextView signUp;
    TextView ForgotPassBtn;

    Button SignInBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUp = binding.SignUpTextBtn;
        ForgotPassBtn = binding.forgotPass;
        SignInBtn = binding.SignInBtn;

        SignInBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
        });

        ForgotPassBtn.setOnClickListener(v ->{
            Toast.makeText(getContext(), "FORGOT YOUR PASSWORD OH WELL CRY ABOUT IT", Toast.LENGTH_SHORT).show();
        });
        signUp.setOnClickListener(v ->{
            Toast.makeText(getContext(), "SIGN UP TIME BABY", Toast.LENGTH_SHORT).show();

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.Login_Frame_layout, new SignUp());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}