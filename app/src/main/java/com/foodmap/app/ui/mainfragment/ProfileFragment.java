package com.foodmap.app.ui.mainfragment;

import android.content.Intent;
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
import com.foodmap.app.model.auth.Auth0Manager;
import com.foodmap.app.ui.LoginActivity;
import com.foodmap.app.model.database.SharedPreferencesManager;

/**
 * Profile Screen
 * Displays user's profile pic and email. Allows
 * user to logout of app session.
 */
public class ProfileFragment extends Fragment {

    private Auth0Manager auth0Manager;

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // instantiate variables
        auth0Manager = new Auth0Manager(getContext());
        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            auth0Manager.logout(new Callback<Void, AuthenticationException>() {

                @Override
                public void onSuccess(Void result) {
                    System.out.println("Logout successful");
                    SharedPreferencesManager.clearUserPrefs(getContext());

                    // close main activity
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    System.out.println("Logout failed: " + e.getMessage());
                }
            });
        });

        return view;
    }

}