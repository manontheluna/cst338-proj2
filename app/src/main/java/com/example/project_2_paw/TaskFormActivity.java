package com.example.project_2_paw;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.repository.PawRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TaskFormActivity extends AppCompatActivity {
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;
    private EditText editTaskName;
    private EditText editDueDate;
    private Button saveTaskButton;
    private Button cancelTaskButton;

    private int petId;
    private PawRepository repository;

    private String pendingTaskName;

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

        pendingTaskName = name;

        // Request notification permission if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    REQUEST_NOTIFICATION_PERMISSION);
        } else {
            // Permission already granted, schedule reminder immediately
            scheduleReminder(name);
        }
        // Close form and return to PetTasksActivity
        finish();
    }

    private void scheduleReminder(String taskName) {
        long delayMillis = 5_000; // 30 seconds for demo
        Data data = new Data.Builder()
                .putString("title", "Task Reminder")
                .putString("message", "Don't forget: " + taskName)
                .build();

        OneTimeWorkRequest reminderWork = new OneTimeWorkRequest.Builder(ReminderWorker.class)
                .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build();

        WorkManager.getInstance(this).enqueue(reminderWork);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted → schedule reminder
                scheduleReminder(pendingTaskName);
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Cannot show reminders without permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
