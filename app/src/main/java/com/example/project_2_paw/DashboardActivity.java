package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_paw.data.entity.User;
public class DashboardActivity extends AppCompatActivity {
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int userId = getIntent().getIntExtra(EXTRA_USER_ID, -1);

        // Temporary: if missing, fall back to login
        if (userId == -1) {
            startActivity(new Intent(this, LoginView.class));
            finish();
            return;
        }
        // TODO (next step): load user from DB by id and route
    }
}
