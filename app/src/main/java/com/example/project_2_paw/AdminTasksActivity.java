package com.example.project_2_paw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.adapters.AdminTaskAdapter;
import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.repository.PawRepository;

import java.util.List;

public class AdminTasksActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tasks);

        findViewById(R.id.btnBackAdminTasks).setOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.adminTasksRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        PawRepository repo = new PawRepository(getApplicationContext());
        List<CareTask> tasks = repo.getAllTasks();

        rv.setAdapter(new AdminTaskAdapter(tasks));
    }
}
