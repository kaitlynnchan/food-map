package com.foodmap.app.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.foodmap.app.databinding.ActivityMainBinding;

import com.foodmap.app.R;
import com.foodmap.app.model.List;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.ui.main.ListDialog;
import com.foodmap.app.ui.main.ListFragment;
import com.foodmap.app.ui.main.MapsFragment;
import com.foodmap.app.ui.main.ProfileFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListsManager listsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listsManager = new ListsManager();

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

        binding.addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                ListDialog listDialog = new ListDialog();
                listDialog.show(manager, "");
            }
        });
    }

    public static void addList(List list){
    }

}