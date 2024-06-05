package com.foodmap.app.model.auth;

import android.content.Context;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.foodmap.app.R;

public class Auth0Manager {

    private Auth0 account;
    private Context context;

    public Auth0Manager(Context context){
        this.context = context;
        account = new Auth0(
                context.getString(R.string.com_auth0_client_id),
                context.getString(R.string.com_auth0_domain)
        );
    }

    public void login(Callback<Credentials, AuthenticationException> callback){
        WebAuthProvider
                .login(account)
                .withScheme(context.getString(R.string.com_auth0_scheme))
                .start(context, callback);
    }

    public void logout(Callback<Void, AuthenticationException> callback){
        WebAuthProvider
                .logout(account)
                .withScheme(context.getString(R.string.com_auth0_scheme))
                .start(context, callback);
    }

}
