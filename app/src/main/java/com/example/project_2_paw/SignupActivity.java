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

public class SignupActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button submitButton;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = AppDatabase.getInstance(this);

        usernameInput = findViewById(R.id.signupUsername);
        passwordInput = findViewById(R.id.signupPassword);
        confirmPasswordInput = findViewById(R.id.signupConfirmPassword);
        submitButton = findViewById(R.id.signupSubmitBtn);

        submitButton.setOnClickListener(v -> {

            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();


            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            User existing = db.userDAO().findByUsername(username);
            if (existing != null) {
                Toast.makeText(this, "Username already exist", Toast.LENGTH_SHORT).show();
            }
            User user = new User();
            user.username = username;
            user.password = password;
            user.isAdmin = false;

            db.userDAO().insert(user);

            Toast.makeText(SignupActivity.this, "Account created! Please log in.", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}

