package com.foodmap.app.model.database;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.foodmap.app.model.User;

/**
 * Shared Preferences Handler
 * Handles shared preferences calls
 */
public class SharedPreferencesManager {
    public static final String PREFS_USER = "SHARED_PREFS_USER";
    public static final String KEY_USER_ID = "KEY_USER_ID";

    private static SharedPreferences getSharedPrefs(Context context, String prefs){
        return context.getSharedPreferences(prefs, MODE_PRIVATE);
    }

    public static void saveUser(Context context, User user){
        SharedPreferences sharedPreferences = getSharedPrefs(context, PREFS_USER);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, user.getId());
        editor.apply();
    }

    public static String getUserId(Context context){
        SharedPreferences sharedPreferences = getSharedPrefs(context, PREFS_USER);
        String userID = sharedPreferences.getString(KEY_USER_ID, null);
        return userID;
    }

    public static void clearUserPrefs(Context context){
        SharedPreferences sharedPreferences = getSharedPrefs(context, PREFS_USER);
        sharedPreferences.edit().clear().apply();
    }
}
