package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.adapters.PetAdapter;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.repository.PawRepository;

import java.util.List;

public class AdminPetsActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pets);

        findViewById(R.id.btnBackAdminPets).setOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.adminPetsRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        PawRepository repo = new PawRepository(getApplicationContext());
        List<Pet> pets = repo.getAllPets();

        PetAdapter adapter = new PetAdapter();
        adapter.setPets(pets);
        rv.setAdapter(adapter);
    }
}
