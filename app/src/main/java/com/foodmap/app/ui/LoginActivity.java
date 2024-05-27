package com.foodmap.app.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        binding.startButton.setOnClickListener(v -> auth0Manager.login());
    }

}
