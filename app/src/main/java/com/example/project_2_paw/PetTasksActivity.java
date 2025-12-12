package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project_2_paw.adapters.CareTaskAdapter;
import com.example.project_2_paw.data.entity.CareTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetTasksActivity extends AppCompatActivity{
    public static final String EXTRA_PET_ID = "petId";
    public static final String EXTRA_PET_NAME = "petName";

    private int petId;
    private TextView petNameTitle;
    private RecyclerView taskRecycler;
    private Button showCompletedButton;
    private Button addTaskButton;
    private Button backFromTasksButton;
    private CareTaskAdapter taskAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_tasks);

        Intent intent = getIntent();
        petId = intent.getIntExtra(EXTRA_PET_ID, -1);
        String petName = intent.getStringExtra(EXTRA_PET_NAME);

        petNameTitle = findViewById(R.id.petNameTitle);
        taskRecycler = findViewById(R.id.taskRecycler);
        showCompletedButton = findViewById(R.id.showCompletedButton);
        addTaskButton = findViewById(R.id.addTaskButton);
        backFromTasksButton = findViewById(R.id.backFromTasksButton);

        if (petName != null) {
            petNameTitle.setText(petName);
        }

        // Basic RecyclerView setup (will plug in a real adapter later)
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new CareTaskAdapter((task, isChecked) -> {
            // TODO: later update task.isCompleted in DB using repository
            task.setCompleted(isChecked);
        });
        taskRecycler.setAdapter(taskAdapter);

        loadDummyTasks();

        // 5. Buttons – only navigation placeholders for now
        backFromTasksButton.setOnClickListener(v -> finish());

        addTaskButton.setOnClickListener(v -> {
            // TODO: start Tasks Form Activity when you implement it
            // Intent formIntent = new Intent(this, TaskFormActivity.class);
            // formIntent.putExtra(EXTRA_PET_ID, petId);
            // startActivity(formIntent);
        });

        showCompletedButton.setOnClickListener(v -> {
            // TODO: toggle between to-do vs completed lists later
        });
    }
    private void loadDummyTasks() {
        List<CareTask> demoTasks = new ArrayList<>();

        // Use today's date for now; we'll filter properly later
        LocalDate today = LocalDate.now();

        demoTasks.add(new CareTask(petId, "Feed",   today, false));
        demoTasks.add(new CareTask(petId, "Shower", today, true));
        demoTasks.add(new CareTask(petId, "Walk",   today, false));


        taskAdapter.setTasks(demoTasks);
    }
}
