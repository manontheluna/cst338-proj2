package com.example.project_2_paw;

/*
 * @author Manuel Caro
 * @date 12/11/2025
 * @description UserSession class to manage user login sessions using SharedPreferences.
 */


import android.content.Context;
import android.content.SharedPreferences;

import com.example.project_2_paw.data.entity.User;

public class UserSession {

    private static final String PREF_NAME = "paw_user_session";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_ADMIN = "isAdmin";

    private SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public UserSession(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUser(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putBoolean(KEY_IS_ADMIN, user.isAdmin());
        editor.apply();
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, null);
    }

    public boolean isAdmin() {
        return prefs.getBoolean(KEY_IS_ADMIN, false);
    }
}

