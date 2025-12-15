package com.example.project_2_paw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.project_2_paw.navigation.IntentFactory;

public class AdminPageActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_page);
            findViewById(R.id.btnBackAdmin).setOnClickListener(v -> finish());

            findViewById(R.id.btnManageUsers).setOnClickListener(v -> {
                startActivity(new Intent(this, AdminUsersActivity.class));
            });

            findViewById(R.id.btnManageTasks).setOnClickListener(v -> {
                startActivity(new Intent(this, AdminTasksActivity.class));
            });
            findViewById(R.id.btnManagePets).setOnClickListener(v -> {
                startActivity(IntentFactory.createAdminPets(this));
            });
        }
}
