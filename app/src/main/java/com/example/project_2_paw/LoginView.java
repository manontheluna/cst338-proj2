/* @Author: Manuel Caro
   @Date: December 5, 2025
   @Description: This is the LoginView activity for the Project 2 PAW application.
*/

package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        db = PawDatabase.getInstance(this);
        userDao = db.userDAO();

        User testUser = userDao.getUserByUsername("admin1");
        if (testUser == null){
            User admin = new User("admin1", "admin1", true);
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
                Toast.makeText(this, "Please enter both username and password",
                        Toast.LENGTH_SHORT).show();

                return;
            }
            User user = userDao.login(username, password);

            if (user == null){
                Toast.makeText(
                        LoginView.this,
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Success → go to MainActivity (we'll turn that into Landing later)
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            intent.putExtra("username", user.getUsername());
            intent.putExtra("isAdmin", user.isAdmin());
            startActivity(intent);
            finish();
        });

// What happens when SIGN UP is clicked?
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginView.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}