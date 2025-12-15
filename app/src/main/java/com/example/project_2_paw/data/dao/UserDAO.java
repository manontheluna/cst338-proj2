package com.example.project_2_paw.data.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.project_2_paw.data.entity.User;

import java.util.List;

/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Data Access Object (DAO) for performing database
 * operations on the User table.
 */
@Dao
public interface UserDAO {
    // Inserts a new user into the database.
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    // Retrieves a user by username.
    @Query("SELECT * FROM user_table WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    List<User> getAllUsers();

    // Validates login credentials.
    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password  AND isActive = 1 LIMIT 1")
    User login(String username, String password);

    @Query("UPDATE user_table SET isActive = 0 WHERE userId = :userId")
    void deactivateUser(int userId);

    // Retrieves a user by ID.
    @Query("SELECT * FROM user_table WHERE userId = :id LIMIT 1")
    User getUserById(int id);
}
