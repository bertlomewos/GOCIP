package com.example.gocip.Activity.PostActivities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocip.R;
import com.example.gocip.databinding.ActivityPostBinding;
import com.google.android.material.navigation.NavigationBarView;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarPost);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Create Post");
            // getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Optional: if you want a back arrow
        }

        // Set initial fragment
        if (savedInstanceState == null) {
            loadFragment(new fragment_post_youtube()); // Load YouTube fragment by default
        }

        // Handle BottomNavigationView item clicks
        binding.bottomNavigationViewPost.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.nav_post_youtube) {
                    selectedFragment = new fragment_post_youtube();
                } else if (itemId == R.id.nav_post_pdf) {
                    selectedFragment = new fragment_post_pdf();
                } else if (itemId == R.id.nav_post_question) {
                    selectedFragment = new fragment_post_Questions();
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });
    }

    // Method to load fragments into the FrameLayout
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_post_container, fragment);
        // fragmentTransaction.addToBackStack(null); // Optional: if you want to add to back stack
        fragmentTransaction.commit();
    }

    // Inflate the options menu for the Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_post_activity, menu);
        return true;
    }

    // Handle options menu item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_submit_post_main) {
            // Handle submission from the currently active fragment
            // This might involve getting a reference to the current fragment
            // and calling a public method on it to trigger submission.
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_post_container);
            if (currentFragment instanceof fragment_post_youtube) {
                ((fragment_post_youtube) currentFragment).submitPost();
            } else if (currentFragment instanceof fragment_post_pdf) {
                ((fragment_post_pdf) currentFragment).submitPost();
            } else if (currentFragment instanceof fragment_post_Questions) {
                ((fragment_post_Questions) currentFragment).submitPost();
            }
            Toast.makeText(this, "Submit (from Activity)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_clear_input_post) {
            // Handle clearing input in the currently active fragment
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_post_container);
            if (currentFragment instanceof fragment_post_youtube) {
                ((fragment_post_youtube) currentFragment).clearInputs();
            } else if (currentFragment instanceof fragment_post_pdf) {
                ((fragment_post_pdf) currentFragment).clearInputs();
            } else if (currentFragment instanceof fragment_post_Questions) {
                ((fragment_post_Questions) currentFragment).clearInputs();
            }
            Toast.makeText(this, "Clear Input (from Activity)", Toast.LENGTH_SHORT).show();
            return true;
        }
        // Handle Toolbar back arrow click if set up
        // if (item.getItemId() == android.R.id.home) {
        //     onBackPressed();
        //     return true;
        // }
        return super.onOptionsItemSelected(item);
    }
}