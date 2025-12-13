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

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView welcome;
    private TextView createPet;
    private String username;
    private int currentUserId;

    private RecyclerView petRecyclerView;
    private PetAdapter petAdapter;
    private PawRepository repository;

    private Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);
        createPet = findViewById(R.id.createPetMain);
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
        petRecyclerView = findViewById(R.id.petRecycler);
        petRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        petAdapter = new PetAdapter();
        petRecyclerView.setAdapter(petAdapter);

        loadPets();
        // create pet logic
        createPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // route to pet creation on click of button in main activity
                Intent intent = new Intent(MainActivity.this, PetCreation.class);
                intent.putExtra("ownerId", currentUserId);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadPets();
    }
    private void loadPets() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Pet> pets = repository.getPetsByOwnerId(currentUserId);
            runOnUiThread(() -> petAdapter.setPets(pets));
        });
    }
}