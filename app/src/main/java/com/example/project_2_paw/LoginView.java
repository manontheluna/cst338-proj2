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


public class LoginView extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    private PawDatabase db;
    private UserDAO userDao;
    private UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        userSession = new UserSession(this);

        if (userSession.isLoggedIn()) {
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            intent.putExtra("username", userSession.getUsername());
            intent.putExtra("isAdmin", userSession.isAdmin());
            intent.putExtra("userId", userSession.getUserId());
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

        usernameEditText = findViewById(R.id.loginUsernameInput);
        passwordEditText = findViewById(R.id.loginPasswordInput);
        loginButton = findViewById(R.id.loginBtn);
        signUpButton = findViewById(R.id.signUpBtn);

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
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Save user session
            userSession.saveUser(user);

            // Success → go to MainActivity (Landing/Dashboard)
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            intent.putExtra("username", user.getUsername());
            intent.putExtra("isAdmin", user.isAdmin());
            intent.putExtra("userId", user.getUserId());
            startActivity(intent);
            finish();
        });

        // SIGN UP → open SignupActivity
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginView.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
