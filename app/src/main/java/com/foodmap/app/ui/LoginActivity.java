package com.foodmap.app.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.result.Credentials;
import com.foodmap.app.databinding.ActivityLoginBinding;
import com.foodmap.app.model.Auth0Manager;
import com.foodmap.app.model.User;
import com.foodmap.app.model.database.Firestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;


public final class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Auth0Manager auth0Manager;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth0Manager = new Auth0Manager(this);

        binding.startButton.setOnClickListener(v -> {
            auth0Manager.login(new Callback<Credentials, AuthenticationException>() {

                @Override
                public void onSuccess(Credentials credentials) {
                    System.out.println("Authentication with Auth0 successful");
                    // TODO: authenticate user with Firebase

                    // create user with credentials
                    User user = new User(
                            credentials.getUser().getId(),
                            credentials.getUser().getEmail(),
                            credentials.getUser().getPictureURL()
                    );
                    saveUser(user);

                    // open main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    System.out.println("Authentication with Auth0 failed");
                    Toast.makeText(LoginActivity.this,
                            "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

//    private void signInWithFirebase(String idToken) {
//        firebaseAuth.signInWithCustomToken(idToken)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            System.out.println("authentication with firebase successful");
//                        } else {
//                            System.out.println("authentication with firebase failed");
//                        }
//                    }
//                });
//    }

    private void saveUser(User user){
        // save user locally to shared preferences
//        SharedPreferences sharedPreferences = this.getSharedPreferences("SHARED_PREFS_USER", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        editor.putString("EDITOR_USER", json);
//        editor.apply();

        // save user ID locally to shared preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences("SHARED_PREFS_USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EDITOR_USER_ID", user.getId());
        editor.apply();

        // upload user to firestore
        Firestore firestore = new Firestore(user.getId());
        firestore.addNewUserCollection(user);
    }
}
