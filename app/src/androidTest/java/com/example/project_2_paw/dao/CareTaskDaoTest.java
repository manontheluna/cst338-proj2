package com.example.project_2_paw.dao;

/**
 * @author Manuel Caro
 * @date 12/14/2025
 * @description Database tests for CareTaskDao
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_2_paw.data.dao.CareTaskDAO;
import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.entity.Pet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;

import java.time.LocalDate;
import java.util.List;

/**
 * Unit test for CareTaskDAO
 * Verifies basic operations: insert, update, and delete operations and
 * ensures foreign key constraints with the Pet entity are respected.
 */

@RunWith(AndroidJUnit4.class)
public class CareTaskDaoTest {
    private PawDatabase db;
    private CareTaskDAO careTaskDao;
    private PetDAO petDao;

    /**
     * Creates a new in-memory Room database before each test,
     * and initializes both CareTAskDAO and PetDao.
     */

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PawDatabase.class)
                .allowMainThreadQueries()
                .build();
        careTaskDao = db.careTaskDAO();
        petDao = db.petDAO();
    }

    /**
     * Closes the in-memory database after each test.
     */
    @After
    public void teardown() {
        db.close();
    }

    /**
     * Tests the insertion of a CareTask and verifies that the
     * task is stored in the database, petId is correctly linked,
     * and all fields match expected values.
     */
    @Test
    public void insertTask_savesTaskToDatabase() {

        Pet pet = new Pet(1, "Luna", "Dog", 14);
        petDao.insert(pet);

        int petId = petDao.getPetsByOwnerId(1).get(0).getPetId();

        CareTask task = new CareTask(petId, "Walk", LocalDate.of(2025, 5, 29), false);
        careTaskDao.insertTask(task);

        List<CareTask> tasks = careTaskDao.getTasksForPetSync(petId);
        assertNotNull(tasks);
        assertEquals(1, tasks.size());

        CareTask saved = tasks.get(0);
        assertEquals(petId, saved.getPetId());
        assertEquals("Walk", saved.getTaskName());
        assertEquals(LocalDate.of(2025, 5, 29), saved.getDueDate());
        assertFalse(saved.isCompleted());
    }

    /**
     * Tests updating a CareTask by modifying its firlds and confirming that
     * the updates are correctly reported when the task is reloaded.
     */
    @Test
    public void updateTask_updateExistingTask() {

        Pet pet = new Pet(1, "Luna", "Dog", 14);
        petDao.insert(pet);
        int petId = petDao.getPetsByOwnerId(1).get(0).getPetId();

        CareTask task = new CareTask(petId, "Walk", LocalDate.of(2025, 5, 29), false);
        careTaskDao.insertTask(task);

        List<CareTask> tasks = careTaskDao.getTasksForPetSync(1);
        assertEquals(1, tasks.size());
        CareTask saved = tasks.get(0);

        saved.setTaskName("Vet appointment");
        saved.setCompleted(true);
        LocalDate newDate = LocalDate.of(2025, 4, 2);
        saved.setDueDate(newDate);

        careTaskDao.updateTask(saved);

        List<CareTask> updatedTasks = careTaskDao.getTasksForPetSync(1);
        assertEquals(1, updatedTasks.size());
        CareTask updated = updatedTasks.get(0);

        assertEquals("Vet appointment", updated.getTaskName());
        assertTrue(updated.isCompleted());
        assertEquals(newDate, updated.getDueDate());
    }

    /**
     * Tests the deletion of a CareTask and ensures that the task is
     * removed from the database, no remaining tasks exists for the given
     * petId.
     */
    @Test
    public void deleteTask_removesTaskForPet() {

        Pet pet = new Pet(1, "Luna", "Dog", 14);
        petDao.insert(pet);
        int petId = petDao.getPetsByOwnerId(1).get(0).getPetId();

        CareTask task = new CareTask(petId, "Walk", LocalDate.of(2025, 5, 29), false);
        careTaskDao.insertTask(task);

        List<CareTask> tasks = careTaskDao.getTasksForPetSync(1);
        assertEquals(1, tasks.size());
        CareTask saved = tasks.get(0);
        assertNotNull(saved);

        careTaskDao.deleteTask(saved);

        List<CareTask> tasksAfterDelete = careTaskDao.getTasksForPetSync(1);
        assertTrue(tasksAfterDelete.isEmpty());
    }
}
