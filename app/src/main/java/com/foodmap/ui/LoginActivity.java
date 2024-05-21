package com.foodmap.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.foodmap.R;
import com.foodmap.databinding.ActivityLoginBinding;

import org.jetbrains.annotations.Nullable;


public final class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Auth0 account;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        account = new Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain)
        );

        binding.startButton.setOnClickListener(v -> login());
    }

    public void login(){
        WebAuthProvider
            .login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, new Callback<Credentials, AuthenticationException>() {

                @Override
                public void onSuccess(Credentials credentials) {
                    // open main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    System.out.println("didnt work!");
                }
            });
    }

    public void logout(Activity activity){
        WebAuthProvider
            .logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, new Callback<Void, AuthenticationException>() {

                @Override
                public void onSuccess(Void result) {
                    // close main activity
                    activity.finish();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    System.out.println("Exception error: " + e.getMessage());
                }
            });
    }
}
