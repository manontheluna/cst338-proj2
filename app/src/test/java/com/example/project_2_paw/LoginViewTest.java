package com.example.project_2_paw;

/*
    * @author Manuel Caro
    * @date 12/13/2025
    * @description Unit tests for LoginView class.
 */

import static org.junit.Assert.*;
import org.junit.Test;

/**
* Unit tests for LoginView class, validating isLoginInputValid method. Ensures
 * correct handling for:
 * valid inputs
 * empty username/password
 * whitespace-only username/password
 * null username/password
*/
public class LoginViewTest {

    @Test
    public void validInput_returnsTrue() {
        assertTrue(LoginView.isLoginInputValid("user", "password"));
    }

    @Test
    public void emptyUsername_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("", "password"));
    }

    @Test
    public void emptyPassword_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("user", ""));
    }

    @Test
    public void bothEmpty_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("", ""));
    }

    @Test
    public void whitespaceUsername_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("   ", "password"));
    }

    @Test
    public void whitespacePassword_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("user", "   "));
    }
    @Test
    public void whitespaceBoth_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("   ", "   "));
    }
    @Test
    public void nullUsername_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid(null, "password"));
    }
    @Test
    public void nullPassword_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid("user", null));
    }
    @Test
    public void nullBoth_returnsFalse() {
        assertFalse(LoginView.isLoginInputValid(null, null));
    }
}