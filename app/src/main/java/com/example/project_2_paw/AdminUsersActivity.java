package com.example.project_2_paw;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.adapters.AdminUserAdapter;
import com.example.project_2_paw.data.repository.PawRepository;
import com.example.project_2_paw.data.entity.User;

import java.util.List;

public class AdminUsersActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        RecyclerView rv = findViewById(R.id.adminUsersRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        PawRepository repo = new PawRepository(getApplicationContext());
        List<User> users = repo.getAllUsers();

        rv.setAdapter(new AdminUserAdapter(users));
    }
}
