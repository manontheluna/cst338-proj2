/* @Author: Manuel Caro
   @Date: December 5, 2025
   @Description: This is the LoginView activity for the Project 2 PAW application.
*/

package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.navigation.IntentFactory;


/**
 * LoginView handles all login-related functionality.
 * Redirects already logged-in users using UserSession.
 * Authenticates user credentials through ROOM (UserDAO).
 * Seeds a default admin user if missing.
 * Provides navigation to SignupActivity for new users.
 */

public class LoginView extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    private PawDatabase db;
    private UserDAO userDao;
    private UserSession userSession;

    /**
     * Initializes the login screen, checks for an active session, seeds admin user,
     * and sets up listeners for login and sign-up actions.
     *
     * @param savedInstanceState Saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        // Initialize user session management
        userSession = new UserSession(this);

        // If user is already logged in, skip login screen and go to MainActivity
        if (userSession.isLoggedIn()){
            Intent intent = IntentFactory.createMain(
                    LoginView.this,
                    userSession.getUsername(),
                    userSession.isAdmin(),
                    userSession.getUserId()
            );
            startActivity(intent);
            finish();
            return;
        }

        db = PawDatabase.getInstance(this);
        userDao = db.userDAO();

        // Seed an admin user if not present
        User testUser = userDao.getUserByUsername("admin1");
        if (testUser == null) {
            User admin = new User("admin1", "admin1", true);  // username, password, isAdmin
            userDao.insert(admin);
        }

        //UI bindings
        usernameEditText = findViewById(R.id.loginUsernameInput);
        passwordEditText = findViewById(R.id.loginPasswordInput);
        loginButton = findViewById(R.id.loginBtn);
        signUpButton = findViewById(R.id.signUpBtn);

        // Login button functionality
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                        LoginView.this,
                        "Please enter both username and password",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Use DAO's login method: validates username + password
            User user = userDao.login(username, password);

            if (user == null) {
                Toast.makeText(
                        LoginView.this,
                        "Invalid credentials or account deactivated",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Save user session so login persists
            userSession.saveUser(user);

            // Success → go to MainActivity (Landing/Dashboard)
            Intent intent = IntentFactory.createMain(
                    LoginView.this,
                    user.getUsername(),
                    user.isAdmin(),
                    user.getUserId()
            );
            startActivity(intent);
            finish();
        });

        // SIGN UP → open SignupActivity
        signUpButton.setOnClickListener(v -> {
            Intent intent = IntentFactory.createSignup(LoginView.this);
            startActivity(intent);
        });

    }
    /**
     * Validates login input fields.
     * Used for unit testing and basic input checks.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if both fields are non-empty (after trimming), false otherwise
     */
    public static boolean isLoginInputValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return !username.trim().isEmpty() && !password.trim().isEmpty();
    }
}

