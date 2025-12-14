package com.example.project_2_paw.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_2_paw.data.entity.Pet;

import java.util.List;

/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Data Access Object (DAO) for performing database
 * operations on the Pet table.
 */

@Dao
public interface PetDAO {

    // Inserts a new pet into the database.
    @Insert
    void insert(Pet pet);
    @Update
    void update(Pet pet);

    // Deletes a pet from the database.
    @Delete
    void delete(Pet pet);

    // Returns all pets that belong to a specific user.
    @Query("SELECT * FROM pet_table WHERE ownerId = :ownerId")
    List<Pet> getPetsByOwnerId(int ownerId);
}
