package com.foodmap.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.foodmap.databinding.ActivityMainBinding;

import com.foodmap.R;
import com.foodmap.ui.main.ListFragment;
import com.foodmap.ui.main.MapsFragment;
import com.foodmap.ui.main.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // connect bottom navigation
        MapsFragment mapsFragment = new MapsFragment();
        ListFragment listFragment = new ListFragment();
        ProfileFragment profileFragment = new ProfileFragment();

        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case (R.id.location):
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainActivityFragment, mapsFragment)
                            .commit();
                    return true;
                case (R.id.list):
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainActivityFragment, listFragment)
                            .commit();
                    return true;
                case (R.id.profile):
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainActivityFragment, profileFragment)
                            .commit();
                    return true;
            }
            return false;
        });
    }

}