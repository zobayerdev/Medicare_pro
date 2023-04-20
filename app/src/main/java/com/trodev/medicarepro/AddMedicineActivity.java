package com.trodev.medicarepro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class AddMedicineActivity extends AppCompatActivity {

    private ImageView appsIV;
    private EditText nameEt, shortDesEt, indicatorsEt, dosagesEt, interactionEt, effectEt, warningsEt, conditionEt;

    private Spinner AddCategory;
    private Button uploadBtn;
    private final int REQ = 1;
    private Bitmap bitmap = null;
    private String category;
    private ProgressDialog progressDialog;
    private String name, description, indicators, dosages, interaction, effect, warnings, conditions; // kono edittext er valu baranor jonno eikhane nite hobe
    private String downloadUrl = "";
    private StorageReference storageReference;
    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Medicine");

        nameEt = findViewById(R.id.nameEt);
        shortDesEt = findViewById(R.id.shortEt);
        indicatorsEt = findViewById(R.id.indicatorEt);
        dosagesEt = findViewById(R.id.doseEt);
        interactionEt = findViewById(R.id.interactionEt);
        effectEt = findViewById(R.id.effectEt);
        warningsEt = findViewById(R.id.warningsEt);
        conditionEt = findViewById(R.id.conditionEt);
        AddCategory = findViewById(R.id.AddCategory);

        progressDialog = new ProgressDialog(this);

        // firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");
        storageReference = FirebaseStorage.getInstance().getReference();


        String[] items = new String[]{"Select Category", "Powder for suspension", "Capsules ", "Tablet", "Ointment", "Inhaler", "Drops", "Suppositories", "Injection "};
        AddCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));


        AddCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // eikhane change ache AddStudentCategory
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                category = AddCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}