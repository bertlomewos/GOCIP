package com.example.timero.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.timero.R;
import com.example.timero.ui.main.home.HomeFragment;
import com.example.timero.ui.post.PostActivity;
import com.example.timero.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        // Load the default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,
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
                    // Start PostActivity instead of loading a fragment
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                    return false; // Return false so the item is not selected
                } else if (itemId == R.id.navigation_profile) {
                    // Start ProfileActivity instead of loading a fragment
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return false; // Return false so the item is not selected
                }

                return false;
            };

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame_layout, fragment);
            transaction.commit();
        }
    }
}
