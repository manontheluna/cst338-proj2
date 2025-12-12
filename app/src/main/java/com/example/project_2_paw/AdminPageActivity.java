package com.example.project_2_paw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class AdminPageActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_page);
            findViewById(R.id.btnBackAdmin).setOnClickListener(v -> finish());
        }
}
