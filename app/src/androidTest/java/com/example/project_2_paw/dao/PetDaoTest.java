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
    public void insertPet_insertsAndCanFetchByOwner() {
        int ownerId = 101;
        Pet pet = new Pet(ownerId, "Buddy", "Dog", 3); // constructor matches your entity :contentReference[oaicite:5]{index=5}
        petDao.insert(pet);

        List<Pet> pets = petDao.getPetsByOwnerId(ownerId);
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
    }

    @Test
    public void updatePet_updatesName() {
        int ownerId = 202;
        Pet pet = new Pet(ownerId, "OldName", "Cat", 2);
        petDao.insert(pet);

        Pet fetched = petDao.getPetsByOwnerId(ownerId).get(0);
        fetched.setName("NewName");
        petDao.update(fetched);

        Pet updated = petDao.getPetsByOwnerId(ownerId).get(0);
        assertEquals("NewName", updated.getName());
    }


    @Test
    public void deletePet_deletesAndNoLongerReturned() {
        int ownerId = 303;
        Pet pet = new Pet(ownerId, "ToDelete", "Dog", 5);
        petDao.insert(pet);

        Pet fetched = petDao.getPetsByOwnerId(ownerId).get(0);
        petDao.delete(fetched);

        List<Pet> after = petDao.getPetsByOwnerId(ownerId);
        assertEquals(0, after.size());
    }
}
