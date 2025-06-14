package com.example.timero.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.timero.R;
import com.example.timero.api.RetrofitDatabase;
import com.example.timero.api.postApi;
import com.example.timero.data.model.Post;
import com.example.timero.databinding.ActivityMainBinding;
import com.example.timero.ui.main.home.HomeFragment;
import com.example.timero.ui.post.PostActivity;
import com.example.timero.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    postApi api;
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

        Retrofit retrofit = RetrofitDatabase.getInstance();
        api = retrofit.create(postApi.class);
        api.getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> posts = response.body();
                    Toast.makeText(MainActivity.this, Integer.toString(posts.size()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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