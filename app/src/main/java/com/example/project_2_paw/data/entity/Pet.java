package com.example.project_2_paw.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "pet_table")
public class Pet {
    @PrimaryKey(autoGenerate = true)
    private int petId;

    private int ownerId; // FK to User.userId
    private String name;
    private String species;
    private int age;

    public Pet(int ownerId, String name, String species, int age) {
        this.ownerId = ownerId;
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
