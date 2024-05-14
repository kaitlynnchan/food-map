package com.example.foodmap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodmap.R;

import org.jetbrains.annotations.Nullable;

public final class LoginActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button startBtn = findViewById(R.id.startButton);
        startBtn.setOnClickListener(v -> {
            // TODO: setup OAuth
            // link to OAuth login/signup page

            // open main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
