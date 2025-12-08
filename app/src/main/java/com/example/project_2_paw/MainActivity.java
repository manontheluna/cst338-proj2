package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView welcome;
    private TextView createPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);
        createPet = findViewById(R.id.createPetMain);

        String username = getIntent().getStringExtra("username");

        if (username != null) {
            welcome.setText("Welcome, " + username + "!");
        }

        // create pet logic
        createPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // route to pet creation on click of button in main activity
                Intent intent = new Intent(MainActivity.this, PetCreation.class);
                startActivity(intent);
            }
        });
    }
}