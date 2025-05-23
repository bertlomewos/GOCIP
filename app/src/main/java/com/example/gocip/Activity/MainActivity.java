package com.example.gocip.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocip.Activity.Login_SignUp.Login;
import com.example.gocip.R;
import com.example.gocip.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
     Button SignInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new Login());
    }
 void replacefragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Login_Frame_layout , fragment);
        fragmentTransaction.commit();
    }
}