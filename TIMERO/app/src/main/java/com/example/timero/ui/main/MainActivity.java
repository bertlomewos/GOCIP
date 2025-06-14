package com.example.timero.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.timero.R;
import com.example.timero.databinding.ActivityMainBinding;
import com.example.timero.ui.main.home.HomeFragment;
import com.example.timero.ui.post.PostActivity;
import com.example.timero.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigation.setOnItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(binding.mainFrameLayout.getId(),
                    new HomeFragment()).commit();
        }
    }

    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                    loadFragment(selectedFragment);
                    return true;
                } else if (itemId == R.id.navigation_post) {
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                    return false;
                } else if (itemId == R.id.navigation_profile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return false;
                }

                return false;
            };

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(binding.mainFrameLayout.getId(), fragment);
            transaction.commit();
        }
    }
}