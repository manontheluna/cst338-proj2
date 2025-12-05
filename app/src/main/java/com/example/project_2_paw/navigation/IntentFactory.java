package com.example.project_2_paw.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.project_2_paw.LoginView;

public class IntentFactory {
    public static Intent login(Context context) {
        return new Intent(context, LoginView.class);
    }

    public static Intent signup(Context context) {
        return new Intent(context, SignUpView.class);
    }

    public static Intent dashboard(Context context) {
        return new Intent(context, Dashboard.class);
    }

    public static Intent petView(Context context) {
        return new Intent(context, PetView.class);
    }
}
