package com.example.project_2_paw.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Represents a user in the P.A.W. application.
 * This entity stores authentication information
 * and identifies whether the user is an admin.
 */

@Entity(tableName = "user_table")
public class User {

    // Primary key for the User table (auto-generated).
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isActive = true;

    /**
     * Constructor used by the application to create a new user.
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getter and Setter methods for Room and application logic.
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
