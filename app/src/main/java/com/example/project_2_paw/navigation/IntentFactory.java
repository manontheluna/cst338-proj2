package com.example.project_2_paw.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.project_2_paw.AdminPetsActivity;
import com.example.project_2_paw.LoginView;
import com.example.project_2_paw.MainActivity;
import com.example.project_2_paw.PetCreation;
import com.example.project_2_paw.SignupActivity;

public class IntentFactory {
    // centralized keys to avoid duplicates
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_IS_ADMIN = "isAdmin";
    public static final String EXTRA_USER_ID = "userId";
    public static final String EXTRA_OWNER_ID = "ownerId";

    public static Intent createMain(Context context, String username, boolean isAdmin, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_IS_ADMIN, isAdmin);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    public static Intent createSignup(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    public static Intent createPet(Context context, int ownerId) {
        Intent intent = new Intent(context, PetCreation.class);
        intent.putExtra(EXTRA_OWNER_ID, ownerId);
        return intent;
    }
    public static Intent createAdminPets(Context context) {
        return new Intent(context, AdminPetsActivity.class);
    }
}
