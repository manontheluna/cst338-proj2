package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.adapters.PetAdapter;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.repository.PawRepository;
import com.example.project_2_paw.navigation.IntentFactory;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView welcome;
    private TextView createPet;

    private Button logoutButton; // Added by Manuel for logout function
    private String username;
    private int currentUserId;

    private PawRepository repository;

    private UserSession userSession; // Added by Manuel for logout function
    private Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSession = new UserSession(this); // Initialize UserSession. Added by Manuel for logout function

        welcome = findViewById(R.id.welcome);
        createPet = findViewById(R.id.createPetMain);
        logoutButton = findViewById(R.id.logoutButton); // Initialize logout button. Added by Manuel for logout function
        currentUserId = getIntent().getIntExtra("userId", -1);
        username = getIntent().getStringExtra("username");
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        btnAdmin = findViewById(R.id.btnAdmin);

        if (isAdmin) {
            btnAdmin.setVisibility(View.VISIBLE);
            btnAdmin.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, AdminPageActivity.class)));
        } else {
            btnAdmin.setVisibility(View.GONE);
        }

        if (username != null) {
            welcome.setText("Welcome, " + username + "!");
        }

        repository = new PawRepository(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.petListContainer, PetListFragment.newInstance(currentUserId))
                    .commit();
        }



        // create pet logic
        createPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // route to pet creation on click of button in main activity
                Intent intent = IntentFactory.createPet(MainActivity.this, currentUserId);
                startActivity(intent);
            }
        });
        // logout logic. Added by Manuel for logout function
        logoutButton.setOnClickListener(v -> {
            userSession.logout(); // Clear session data
            Intent intent = new Intent(MainActivity.this, LoginView.class);
            startActivity(intent);
            finish(); // Close MainActivity
        });
    }

}