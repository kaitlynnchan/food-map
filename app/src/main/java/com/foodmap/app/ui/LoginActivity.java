package com.foodmap.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.foodmap.app.R;
import com.foodmap.app.databinding.ActivityLoginBinding;
import com.foodmap.app.model.Auth0Manager;

import org.jetbrains.annotations.Nullable;


public final class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Auth0Manager auth0Manager;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth0Manager = new Auth0Manager(this, LoginActivity.this, MainActivity.class);
//        account = new Auth0(
//                getString(R.string.com_auth0_client_id),
//                getString(R.string.com_auth0_domain)
//        );

        binding.startButton.setOnClickListener(v -> auth0Manager.login());
    }

//    private void login(){
//        WebAuthProvider
//            .login(account)
//            .withScheme(getString(R.string.com_auth0_scheme))
//            .start(this, new Callback<Credentials, AuthenticationException>() {
//
//                @Override
//                public void onSuccess(Credentials credentials) {
//                    credentials.getUser().getId();
//                    System.out.println("login success");
//
//                    // open main activity
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//                @Override
//                public void onFailure(@NonNull AuthenticationException e) {
//                    System.out.println("didnt work!");
//                    Toast.makeText(LoginActivity.this,
//                            "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//    }
}
