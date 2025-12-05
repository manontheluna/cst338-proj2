package com.example.project_2_paw.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Represents a pet owned by a user in the P.A.W. application.
 * Each pet is linked to a specific user (ownerId).
 */

@Entity(tableName = "pet_table")
public class Pet {

    // Primary key for the Pet table (auto-generated).
    @PrimaryKey(autoGenerate = true)
    private int petId;

    private int ownerId;

    private String name;
    private String species;
    private int age;

    /**
     * Constructor used to create a new Pet object.
     */
    public Pet(int ownerId, String name, String species, int age) {
        this.ownerId = ownerId;
        this.name = name;
        this.species = species;
        this.age = age;
    }

    // Getter and Setter methods for Room and application logic.
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
