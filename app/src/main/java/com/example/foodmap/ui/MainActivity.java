package com.example.foodmap.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodmap.databinding.ActivityMainBinding;

import com.example.foodmap.R;
import com.example.foodmap.ui.main.ListFragment;
import com.example.foodmap.ui.main.MapsFragment;
import com.example.foodmap.ui.main.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
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
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
        });

    }

}