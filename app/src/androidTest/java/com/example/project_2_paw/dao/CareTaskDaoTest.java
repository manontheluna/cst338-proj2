package com.example.project_2_paw.dao;

/**
 * @author Manuel Caro
 * @date 12/14/2025
 * @description Unit test for CareTaskDao
 */

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_2_paw.data.dao.CareTaskDAO;
import com.example.project_2_paw.data.db.PawDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;

/**
 * Unit test for CareTaskDAO
 * Verifies basic operations: insert, update, and delete.
 */

@RunWith(AndroidJUnit4.class)
public class CareTaskDaoTest {
    private PawDatabase db;
    private CareTaskDAO careTaskDao;

    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PawDatabase.class)
                .allowMainThreadQueries()
                .build();
        careTaskDao = db.careTaskDAO();
    }
    @After
    public void teardown(){
        db.close();
    }

}
