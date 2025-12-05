package com.example.project_2_paw.navigation;

import android.content.Context;
import android.content.Intent;

public class IntentFactory {
    public static Intent login(Context context) {
        return new Intent(context, LoginView.class);
    }

    public static Intent signup(Context context) {
        return new Intent(context, SignUp.class);
    }
}
