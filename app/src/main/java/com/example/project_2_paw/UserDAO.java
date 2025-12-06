package com.example.project_2_paw;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Insert
    long insert(User user);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User findByUsername(String username);
}
