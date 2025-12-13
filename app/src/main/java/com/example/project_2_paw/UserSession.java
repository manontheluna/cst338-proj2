package com.example.project_2_paw;

/*
 * @author Manuel Caro
 * @date 12/11/2025
 * @description UserSession class to manage user login sessions using SharedPreferences.
 */


import android.content.Context;
import android.content.SharedPreferences;

import com.example.project_2_paw.data.entity.User;

/**
 * UserSession handles the storage and retrieval of login session data.
 * It allows the application to remember the logged-in user across launches.
 * using SharedPreferences.
 */
public class UserSession {

    private static final String PREF_NAME = "paw_user_session";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_ADMIN = "isAdmin";

    private SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    /**
     * Constructs a UserSession instance and loads SharedPreferences.
     * @param context The application context used to access SharedPreferences.
     */
    public UserSession(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /**
     * Saves login state and user information to SharedPreferences.
     * @param user The authenticated user whose data is to be saved.
     */
    public void saveUser(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putBoolean(KEY_IS_ADMIN, user.isAdmin());
        editor.apply();
    }

    /**
     * Clears all stored session data, logging out the user.
     */
    public void logout() {
        editor.clear();
        editor.apply();
    }

    /**
     * Checks if a user is currently logged in.
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /**
     * Retrieves the logged-in user's ID.
     * @return userID, or -1 if no user is logged in.
     */
    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }

    /**
     * Retrieves the logged-in user's username.
     * @return username, or null if no user is logged in.
     */
    public String getUsername() {
        return prefs.getString(KEY_USERNAME, null);
    }

    /**
     * Checks if the logged-in user has admin privileges.
     * @return true if the user is an admin, false otherwise.
     */
    public boolean isAdmin() {
        return prefs.getBoolean(KEY_IS_ADMIN, false);
    }
}

