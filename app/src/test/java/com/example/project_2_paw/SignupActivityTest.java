package com.example.project_2_paw;
/*
 * @author Manuel Caro
 * @date 12/11/2025
 * @description Unit tests for SignupActivity input validation.
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for SignupActivity input validation logic.
 * Tests include:
 * Valid signup inputs
 * Empty fields
 * Password mismatch
 */


public class SignupActivityTest {
    @Test
    public void testValidSignup() {
        assertTrue(SignupActivity.isSignupValid("user", "password", "password"));
    }

    @Test
    public void testEmptyFields() {
        assertFalse(SignupActivity.isSignupValid("", "password", "password"));
        assertFalse(SignupActivity.isSignupValid("user", "", "password"));
        assertFalse(SignupActivity.isSignupValid("user", "password", ""));
    }

    @Test
    public void testPasswordMismatch() {
        assertFalse(SignupActivity.isSignupValid("user", "password1", "password2"));
    }
}