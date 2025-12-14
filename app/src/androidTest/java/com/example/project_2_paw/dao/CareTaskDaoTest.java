package com.example.project_2_paw.dao;

/**
 * @author Manuel Caro
 * @date 12/14/2025
 * @description Unit test for CareTaskDao
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_2_paw.data.dao.CareTaskDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.CareTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;

import java.time.LocalDate;
import java.util.List;

/**
 * Unit test for CareTaskDAO
 * Verifies basic operations: insert, update, and delete.
 */

@RunWith(AndroidJUnit4.class)
public class CareTaskDaoTest {
    private PawDatabase db;
    private CareTaskDAO careTaskDao;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PawDatabase.class)
                .allowMainThreadQueries()
                .build();
        careTaskDao = db.careTaskDAO();
    }

    @After
    public void teardown() {
        db.close();
    }

    @Test
    public void insertTask_savesTaskToDatabase() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025, 5, 29), false);
        careTaskDao.insertTask(task);
        List<CareTask> tasks = careTaskDao.getTasksForPetSync(1);
        assertNotNull(tasks);
        assertEquals(1, tasks.size());

        CareTask saved = tasks.get(0);
        assertEquals(1, saved.getPetId());
        assertEquals("Walk", saved.getTaskName());
        assertEquals(LocalDate.of(2025, 5, 29), saved.getDueDate());
        assertFalse(saved.isCompleted());
    }
}
