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
    @Test
    public void insertUser_insertsAndCanFetchByUsername() {
        User u = new User("u_insert", "pw", false);
        userDao.insert(u);

        User fetched = userDao.getUserByUsername("u_insert");
        assertNotNull(fetched);
        assertEquals("u_insert", fetched.getUsername());
        assertEquals("pw", fetched.getPassword());
        assertFalse(fetched.isAdmin());
    }

    @Test
    public void updateUser_updatesPassword() {
        User u = new User("u_update", "pw1", false);
        userDao.insert(u);

        User fetched = userDao.getUserByUsername("u_update");
        fetched.setPassword("pw2");
        userDao.update(fetched);

        User updated = userDao.getUserByUsername("u_update");
        assertNotNull(updated);
        assertEquals("pw2", updated.getPassword());
    }

    @Test
    public void deleteUser_deletesAndQueryReturnsNull() {
        User u = new User("u_delete", "pw", false);
        userDao.insert(u);

        User fetched = userDao.getUserByUsername("u_delete");
        userDao.delete(fetched);

        User afterDelete = userDao.getUserByUsername("u_delete");
        assertNull(afterDelete);
    }
}
