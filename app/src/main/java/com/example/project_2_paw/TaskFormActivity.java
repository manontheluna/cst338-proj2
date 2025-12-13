package com.example.project_2_paw;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.repository.PawRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TaskFormActivity extends AppCompatActivity{
    private EditText editTaskName;
    private EditText editDueDate;
    private Button saveTaskButton;
    private Button cancelTaskButton;

    private int petId;
    private PawRepository repository;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        // Get petId from intent
        petId = getIntent().getIntExtra(PetTasksActivity.EXTRA_PET_ID, -1);

        // Init repository
        repository = new PawRepository(this);

        // Wire views
        editTaskName = findViewById(R.id.editTaskName);
        editDueDate = findViewById(R.id.editDueDate);
        saveTaskButton = findViewById(R.id.saveTaskButton);
        cancelTaskButton = findViewById(R.id.cancelTaskButton);

        // Button listeners
        cancelTaskButton.setOnClickListener(v -> finish());

        saveTaskButton.setOnClickListener(v -> onSaveClicked());
    }
    private void onSaveClicked() {
        String name = editTaskName.getText().toString().trim();
        String dateInput = editDueDate.getText().toString().trim();

        // Basic validation
        if (name.isEmpty()) {
            editTaskName.setError("Task name is required");
            return;
        }

        // Parse date in dd-MM-yyyy or use today if empty
        LocalDate dueDate;
        if (dateInput.isEmpty()) {
            dueDate = LocalDate.now();
        } else {
            try {
                dueDate = LocalDate.parse(dateInput, dateFormatter);
            } catch (Exception e) {
                editDueDate.setError("Use format dd-MM-yyyy");
                return;
            }
        }
        // Create CareTask (new tasks start not completed)
        CareTask task = new CareTask(petId, name, dueDate, false);

        // Insert via repository (runs on background thread inside repository)
        repository.insertTask(task);

        // Close form and return to PetTasksActivity
        finish();
    }
}
