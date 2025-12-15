package com.example.project_2_paw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.repository.PawRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskFormActivity extends AppCompatActivity {

    String CHANNEL_ID = "reminder_channel_" + System.currentTimeMillis();

    private EditText editTaskName;
    private EditText editDueDate;
    private Button saveTaskButton;
    private Button cancelTaskButton;

    private int petId;
    private PawRepository repository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

        cancelTaskButton.setOnClickListener(v -> finish());
        saveTaskButton.setOnClickListener(v -> onSaveClicked());

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    101);
        }

        // Create the notification channel once
        createNotificationChannel();
    }

    private void onSaveClicked() {
        String name = editTaskName.getText().toString().trim();
        String dateInput = editDueDate.getText().toString().trim();

        if (name.isEmpty()) {
            editTaskName.setError("Task name is required");
            return;
        }

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

        CareTask task = new CareTask(petId, name, dueDate, false);
        repository.insertTask(task);

        // Schedule notification after 30 seconds
        scheduleNotification(this, "Task Reminder", "Don't forget: " + name, 30_000);

        finish();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager == null) return;

            NotificationChannel existing = manager.getNotificationChannel(CHANNEL_ID);
            if (existing == null) { // only create if it doesn't exist
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        "Task Reminders",
                        NotificationManager.IMPORTANCE_HIGH
                );
                channel.enableVibration(true);
                channel.setDescription("Reminders for your tasks");
                manager.createNotificationChannel(channel);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void scheduleNotification(Context context, String title, String message, long delayMillis) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

            try {
                NotificationManagerCompat.from(context)
                        .notify((int) System.currentTimeMillis(), builder.build());
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }, delayMillis);
    }
}
