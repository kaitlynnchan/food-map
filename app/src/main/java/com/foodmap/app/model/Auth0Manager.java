package com.foodmap.app.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.foodmap.app.R;

public class Auth0Manager {

    private Auth0 account;
    private Context context;
    private Activity activity;
    private Class newClass;

    public Auth0Manager(Context context, Activity activity, Class newClass){
        this.context = context;
        this.activity = activity;
        this.newClass = newClass;

        account = new Auth0(
                context.getString(R.string.com_auth0_client_id),
                context.getString(R.string.com_auth0_domain)
        );
    }

    public void login(){
        WebAuthProvider
                .login(account)
                .withScheme(context.getString(R.string.com_auth0_scheme))
                .start(context, new Callback<Credentials, AuthenticationException>() {

                    @Override
                    public void onSuccess(Credentials credentials) {
                        credentials.getUser().getId();
                        System.out.println("login success");

                        // open main activity
                        Intent intent = new Intent(context, newClass);
                        context.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void onFailure(@NonNull AuthenticationException e) {
                        System.out.println("didnt work!");
                        Toast.makeText(context,
                                "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void logout(){
        WebAuthProvider
                .logout(account)
                .withScheme(context.getString(R.string.com_auth0_scheme))
                .start(context, new Callback<Void, AuthenticationException>() {

                    @Override
                    public void onSuccess(Void result) {
                        // close main activity
                        Intent intent = new Intent(context, newClass);
                        context.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void onFailure(@NonNull AuthenticationException e) {
                        System.out.println("Exception error: " + e.getMessage());
                    }
                });
    }
}
