package com.example.project_2_paw.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.project_2_paw.data.entity.Pet;

import java.util.List;

@Dao
public interface PetDAO {
    @Insert
    void insert(Pet pet);

    @Query("SELECT * FROM pet_table WHERE ownerId = :ownerId")
    List<Pet> getPetsByOwnerId(int ownerId);

    @Delete
    void delete(Pet pet);
}
