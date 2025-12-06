package com.example.project_2_paw;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        usernameEditText = findViewById(R.id.loginUsernameInput);

        passwordEditText = findViewById(R.id.loginPasswordInput);

        loginButton = findViewById(R.id.loginBtn);

        signUpButton = findViewById(R.id.signUpBtn);

        loginButton.setOnClickListener(v ->

        {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                String message = " You entered Username: " + username + "\n Password: " + password;
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });

// What happens when SIGN UP is clicked
        signUpButton.setOnClickListener(v ->

        {
            Toast.makeText(LoginView.this,
                    "Signup screen will go here",
                    Toast.LENGTH_SHORT).show();
            // Later we'll open SignupActivity here with an Intent
        });
    }
}