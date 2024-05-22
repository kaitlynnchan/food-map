package com.foodmap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.foodmap.databinding.ActivityMainBinding;

import com.foodmap.R;
import com.foodmap.ui.main.ListFragment;
import com.foodmap.ui.main.MapsFragment;
import com.foodmap.ui.main.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Auth0 account;

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

//        account = new Auth0(this);
//        Button logoutBtn = findViewById(R.id.signoutBtn);
//        logoutBtn.setOnClickListener(v -> {
//            WebAuthProvider
//                    .logout(account)
//                    .withScheme(getString(R.string.com_auth0_scheme))
//                    .start(this, new Callback<Void, AuthenticationException>() {
//
//                        @Override
//                        public void onSuccess(Void result) {
//                            // close main activity
//                            MainActivity.this.finish();
//                        }
//
//                        @Override
//                        public void onFailure(@NonNull AuthenticationException e) {
//                            System.out.println("Exception error: " + e.getMessage());
//                        }
//                    });
//        });
    }

}