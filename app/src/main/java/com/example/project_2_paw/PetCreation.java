package com.example.project_2_paw;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.repository.PawRepository;

public class PetCreation extends AppCompatActivity {

    private EditText petName;
    private EditText petAge;
    private Spinner petSpecies;
    private Button savePetBtn;

    private PawRepository repository;
    private int ownerId = 1;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_creation);

        repository = new PawRepository(this);

        petName = findViewById(R.id.petNameInput);
        petAge = findViewById(R.id.petAgeInput);
        petSpecies = findViewById(R.id.petSpeciesSpinner);
        savePetBtn = findViewById(R.id.savePetButton);

        // get ownerId
        ownerId = getIntent().getIntExtra("ownerId", -1);

        if (ownerId == -1) {
            Toast.makeText(this, "Error: no owner found", Toast.LENGTH_SHORT).show();
        }

        String[] speciesOptions = { "Dog", "Cat" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                speciesOptions
        );
        petSpecies.setAdapter(adapter);

        savePetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePet();
            }
        });
    }

    private void savePet() {
        String name = petName.getText().toString().trim();
        String ageStr = petAge.getText().toString().trim();
        String species = petSpecies.getSelectedItem().toString();

        if (name.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Pet using constructor or setters
        Pet pet = new Pet(ownerId, name, species, age);

        // Insert pet via repository
        repository.insertPet(pet);

        Toast.makeText(this, "Pet saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}