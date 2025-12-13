package com.example.project_2_paw.dao;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    private PawDatabase db;
    private UserDAO userDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(context, PawDatabase.class)
                .allowMainThreadQueries()
                .build();

        userDao = db.userDAO();

        userDao.insert(new User("testuser", "password", false));
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void loginWithCorrectCredentials_returnsUser() {
        User user = userDao.login("testuser", "password");
        assertNotNull(user);
    }

    @Test
    public void loginWithWrongPassword_returnsNull() {
        User user = userDao.login("testuser", "wrongpassword");
        assertNull(user);
    }
}
