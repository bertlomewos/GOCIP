package com.example.gocip;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocip.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    BottomNavigationView bottomnav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new HomeFragment());
        bottomnav = binding.bottomNav;

        bottomnav.setOnItemSelectedListener(item->{
            if(item.getItemId() == R.id.home)
            {
                replacefragment(new HomeFragment());
            } else if(item.getItemId() == R.id.profile)
            {
                replacefragment(new ProfileFragment());
            } else if(item.getItemId() == R.id.post)
            {
                replacefragment(new PostFragment());
            }
            return true;
        });

    }

    void replacefragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Home_frame_layout , fragment);
        fragmentTransaction.commit();
    }
}