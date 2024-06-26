package com.foodmap.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.result.Credentials;
import com.foodmap.app.databinding.ActivityLoginBinding;
import com.foodmap.app.model.auth.Auth0Manager;
import com.foodmap.app.model.List;
import com.foodmap.app.model.User;
import com.foodmap.app.model.database.FirestoreHandler;
import com.foodmap.app.model.database.SharedPreferencesManager;

import org.jetbrains.annotations.Nullable;

/**
 * Login Screen
 * Connect to auth0 webpage and allows users to login.
 */
public final class LoginActivity extends AppCompatActivity {

    private static final String TAG_LOGIN = "Log.Login";

    private ActivityLoginBinding binding;
    private Auth0Manager auth0Manager;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // instantiate variables
        auth0Manager = new Auth0Manager(this);

        binding.startButton.setOnClickListener(v -> {
            auth0Manager.login(new Callback<Credentials, AuthenticationException>() {

                @Override
                public void onSuccess(Credentials credentials) {
                    Log.i(TAG_LOGIN, "Authentication with Auth0 successful");

                    // TODO: authenticate user with Firebase

                    // create user with credentials
                    saveUser(new User(
                            credentials.getUser().getId(),
                            credentials.getUser().getEmail(),
                            credentials.getUser().getPictureURL()
                    ));

                    // open main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    Log.e(TAG_LOGIN, "Authentication with Auth0 failed: " + e.getMessage());
                    Toast.makeText(LoginActivity.this,
                            "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void saveUser(User user){
        // save user ID locally to shared preferences
        SharedPreferencesManager.saveUser(this, user);

        // upload user to firestore
        FirestoreHandler.doesUserExist(user.getId(), new FirestoreHandler.FirestoreCallback() {
            @Override
            public void isUserExist(boolean exist) {
                if(!exist){
                    // upload if user does not exist
                    FirestoreHandler.addNewUserCollection(user);
                    FirestoreHandler.addList(user.getId(), List.defaultList);
                }
            }
        });
    }
}
