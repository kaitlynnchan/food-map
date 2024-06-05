package com.foodmap.app.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.foodmap.app.databinding.ActivityMainBinding;

import com.foodmap.app.R;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.model.User;
import com.foodmap.app.model.database.Firestore;
import com.foodmap.app.ui.dialog.ListDialog;
import com.foodmap.app.ui.mainfragment.ListFragment;
import com.foodmap.app.ui.mainfragment.MapsFragment;
import com.foodmap.app.ui.mainfragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public ListsManager listsManager;
    private User user;
    public static Firestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listsManager = ListsManager.getInstance();
        user = new User();
        loadUser();

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

    private void loadUser(){
        String userID = SharedPreferencesHelper.getUserId(this);
        firestore = new Firestore(userID);
        firestore.getUserCollection(user);
    }

}