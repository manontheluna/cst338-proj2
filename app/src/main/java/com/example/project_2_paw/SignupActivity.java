/*
 * @author: Manuel Caro
 * @date: December 5, 2025
 * @description: This is the SignupActivity for the Project 2 PAW application.
 */

package com.example.project_2_paw;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.User;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button submitButton;

    private PawDatabase db;
    private UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = PawDatabase.getInstance(this);
        userDao = db.userDAO();

        usernameInput = findViewById(R.id.signupUsername);
        passwordInput = findViewById(R.id.signupPassword);
        confirmPasswordInput = findViewById(R.id.signupConfirmPassword);
        submitButton = findViewById(R.id.signupSubmitBtn);

        submitButton.setOnClickListener(v -> {

            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();


            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            User existing = db.userDAO().getUserByUsername(username);
            if (existing != null) {
                Toast.makeText(SignupActivity.this, "Username already exist", Toast.LENGTH_SHORT).show();
            }
         User user = new User(username, password, false);
            userDao.insert(user);

            Toast.makeText(SignupActivity.this, "Account created! Please log in.", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}

