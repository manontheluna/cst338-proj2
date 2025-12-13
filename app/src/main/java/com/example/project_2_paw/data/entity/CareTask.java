package com.example.project_2_paw.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "caretask",
        foreignKeys = @ForeignKey(
                entity = Pet.class,
                parentColumns = "petId",
                childColumns = "petId",
                onDelete = ForeignKey.CASCADE
        ))

public class CareTask {
    @PrimaryKey(autoGenerate = true)
    private int taskId;

    private int petId;
    private String taskName;
    private LocalDate dueDate;
    private boolean isCompleted;

    // Constructor(s)
    public CareTask(int petId, String taskName, LocalDate dueDate, boolean isCompleted) {
        this.petId = petId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Getters and setters
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
