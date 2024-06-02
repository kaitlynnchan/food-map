package com.foodmap.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.foodmap.app.R;
import com.foodmap.app.model.Auth0Manager;
import com.foodmap.app.ui.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private Auth0Manager auth0Manager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        auth0Manager = new Auth0Manager(getContext());
        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            auth0Manager.logout(new Callback<Void, AuthenticationException>() {

                @Override
                public void onSuccess(Void result) {
                    clearSharedPreferences();

                    // close main activity
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    System.out.println("Exception error: " + e.getMessage());
                }
            });
        });

        return view;
    }

    private void clearSharedPreferences(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHARED_PREFS_USER", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}