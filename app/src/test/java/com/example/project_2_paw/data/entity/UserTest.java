package com.example.project_2_paw.data.entity;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        user = new User("testUser", "testPassword", false);
    }

    @Override
    protected void tearDown() throws Exception {
        user = null;
        super.tearDown();
    }

    public void testGetUserId() {
        user.setUserId(5);
        assertEquals(5, user.getUserId());
    }

    public void testSetUserId() {
        user.setUserId(5);
        assertEquals(5, user.getUserId());
    }

    public void testGetUsername() {
        assertEquals("testUser", user.getUsername());
    }

    public void testSetUsername() {
        user.setUsername("updatedUsername");
        assertEquals("updatedUsername", user.getUsername());
    }

    public void testGetPassword() {
        assertEquals("testPassword", user.getPassword());
    }

    public void testSetPassword() {
        user.setPassword("updatedPassword");
        assertEquals("updatedPassword", user.getPassword());
    }

    public void testIsAdmin() {
        assertFalse(user.isAdmin());
    }

    public void testSetAdmin() {
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }
}