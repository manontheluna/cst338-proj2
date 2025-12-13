package com.example.project_2_paw.dao;
import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.Pet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PetDaoTest {
    private PawDatabase db;
    private PetDAO petDao;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PawDatabase.class)
                .allowMainThreadQueries()
                .build();
        petDao = db.petDAO();
    }

    @After
    public void teardown() {
        db.close();
    }
    @Test
    public void insertPet_andGetPetsByOwnerId_returnsInsertedPet() {
        int ownerId = 42;

        Pet pet = new Pet(ownerId, "Buddy", "Dog", 3);
        petDao.insert(pet);

        List<Pet> result = petDao.getPetsByOwnerId(ownerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Buddy", result.get(0).getName());
        assertEquals("Dog", result.get(0).getSpecies());
        assertEquals(3, result.get(0).getAge());
        assertEquals(ownerId, result.get(0).getOwnerId());
    }
}
