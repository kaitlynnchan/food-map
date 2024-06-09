package com.foodmap.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.foodmap.app.databinding.ActivityMainBinding;

import com.foodmap.app.R;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.model.User;
import com.foodmap.app.model.database.FirestoreHandler;
import com.foodmap.app.model.database.SharedPreferencesManager;
import com.foodmap.app.ui.dialog.ListDialog;
import com.foodmap.app.ui.mainfragment.ListFragment;
import com.foodmap.app.ui.mainfragment.MapsFragment;
import com.foodmap.app.ui.mainfragment.ProfileFragment;

/**
 * Main Screen
 * Displays bottom navigation, floating action button, header
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListsManager listsManager;
    private User user;
    private int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // instantiate variables
        listsManager = ListsManager.getInstance();
        user = new User();
        // load user
        FirestoreHandler.getUserCollection(SharedPreferencesManager.getUserId(this), user);
        // connect bottom navigation
        currentFragment = R.layout.fragment_maps;
        loadBottomNavigation();

        binding.addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                ListDialog listDialog = new ListDialog();
                listDialog.show(manager, "");
            }
        });
    }
    
    private void loadBottomNavigation() {
        MapsFragment mapsFragment = new MapsFragment();
        ListFragment listFragment = new ListFragment();
        ProfileFragment profileFragment = ProfileFragment.newInstance();

        startFragment(mapsFragment, R.layout.fragment_maps);
        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case (R.id.location):
                    startFragment(mapsFragment, R.layout.fragment_maps);
                    return true;
                case (R.id.list):
                    startFragment(listFragment, R.layout.fragment_list);
                    return true;
                case (R.id.profile):
                    startFragment(profileFragment, R.layout.fragment_profile);
                    return true;
            }
            return false;
        });
    }

    private void startFragment(Fragment fragment, int fragmentId) {
        currentFragment = fragmentId;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainActivityFragment, fragment)
                .commit();

        if(currentFragment == R.layout.fragment_maps){
            // remove title background
            binding.titleToolbar.setBackground(null);

            // make map full screen
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            params.topToTop = R.id.titleToolbar;
            binding.mainActivityFragment.setLayoutParams(params);

            // hide floating button
            binding.addFloatingButton.hide();
        } else{
            // add title background
            binding.titleToolbar.setBackgroundResource(R.drawable.title_background);

            // set fragment to below header
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            params.topToBottom = R.id.titleToolbar;
            binding.mainActivityFragment.setLayoutParams(params);

            // show floating button
            binding.addFloatingButton.show();
        }
    }

}