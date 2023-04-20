package com.trodev.medicarepro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.trodev.medicarepro.R;

import java.util.Objects;

public class AdminActivity extends AppCompatActivity {

    private MaterialCardView addMedicine ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Admin Profile");

        addMedicine = findViewById(R.id.addMedicine);


        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AddMedicineActivity.class));
                Toast.makeText(AdminActivity.this, "Add Medicine", Toast.LENGTH_SHORT).show();
            }
        });

    }
}