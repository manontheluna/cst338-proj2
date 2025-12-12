package com.example.project_2_paw.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.project_2_paw.LoginView;
import com.example.project_2_paw.MainActivity;

public class IntentFactory {
    // centralized keys to avoid duplicates
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_IS_ADMIN = "isAdmin";
    public static final String EXTRA_USER_ID = "userId";

    public static Intent createMain(Context context, String username, boolean isAdmin, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_IS_ADMIN, isAdmin);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

//    public static Intent signup(Context context) {
//        return new Intent(context, SignUpView.class);
//    }

//    public static Intent dashboard(Context context) {
//        return new Intent(context, Dashboard.class);
//    }

//    public static Intent petView(Context context) {it b
//        return new Intent(context, PetView.class);
//    }
}
