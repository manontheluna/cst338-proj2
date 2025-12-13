package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.data.repository.PawRepository;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read what LoginView already sends
        int userId = getIntent().getIntExtra("userId", -1);
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        String username = getIntent().getStringExtra("username");

        // If something went wrong, return to login
        if (userId == -1) {
            startActivity(new Intent(this, LoginView.class));
            finish();
            return;
        }
        // Optional safety: confirm user exists in DB
        PawRepository repo = new PawRepository(getApplicationContext());
        User user = repo.getUserById(userId);
        if (user == null) {
            startActivity(new Intent(this, LoginView.class));
            finish();
            return;
        }
        // Route
        Intent next;
        if (isAdmin) {
            next = new Intent(this, AdminPageActivity.class);
        } else {
            next = new Intent(this, MainActivity.class);
        }

        // Forward extras so Main/Admin screens can still use them
        next.putExtra("username", username);
        next.putExtra("isAdmin", isAdmin);
        next.putExtra("userId", userId);

        startActivity(next);
        finish();
    }
}
