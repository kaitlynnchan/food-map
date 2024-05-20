package com.example.foodmap.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodmap.databinding.ActivityMainBinding;

import com.example.foodmap.R;
import com.example.foodmap.ui.main.ListFragment;
import com.example.foodmap.ui.main.MapsFragment;
import com.example.foodmap.ui.main.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapsFragment mapsFragment = new MapsFragment();
        ListFragment listFragment = new ListFragment();
        ProfileFragment profileFragment = new ProfileFragment();

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnItemSelectedListener(menuItem -> {
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