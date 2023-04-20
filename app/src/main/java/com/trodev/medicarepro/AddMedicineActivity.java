package com.trodev.medicarepro;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.trodev.medicarepro.models.MedicineModels;

import java.util.Objects;

public class AddMedicineActivity extends AppCompatActivity {
    private EditText nameEt, shortDesEt, indicatorsEt, dosagesEt, interactionEt, effectEt, warningsEt, conditionEt;
    private Spinner AddCategory;
    private Button uploadBtn;
    private final int REQ = 1;
    private Bitmap bitmap = null;
    private String category;
    private ProgressDialog progressDialog;
    private String name, description, indicators, dosages, interaction, effect, warnings, conditions;
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
        uploadBtn = findViewById(R.id.uploadBtn);

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

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {
        name = nameEt.getText().toString().trim();
        description = shortDesEt.getText().toString().trim();
        indicators = indicatorsEt.getText().toString().trim();
        dosages = dosagesEt.getText().toString().trim();
        interaction = interactionEt.getText().toString().trim();
        effect = effectEt.getText().toString().trim();
        warnings = warningsEt.getText().toString().trim();
        conditions = conditionEt.getText().toString().trim();

        if (name.isEmpty()) {
            nameEt.setError("Empty");
            nameEt.requestFocus();
        } else if (description.isEmpty()) {
            shortDesEt.setError("Empty");
            shortDesEt.requestFocus();
        } else if (indicators.isEmpty()) {
            indicatorsEt.setError("Empty");
            indicatorsEt.requestFocus();
        } else if (dosages.isEmpty()) {
            dosagesEt.setError("Empty");
            dosagesEt.requestFocus();
        } else if (interaction.isEmpty()) {
            interactionEt.setError("Empty");
            interactionEt.requestFocus();
        } else if (effect.isEmpty()) {
            effectEt.setError("Empty");
            effectEt.requestFocus();
        } else if (warnings.isEmpty()) {
            warningsEt.setError("Empty");
            warningsEt.requestFocus();
        } else if (conditions.isEmpty()) {
            conditionEt.setError("Empty");
            conditionEt.requestFocus();
        } else if (category.equals("Select Category")) {
            Toast.makeText(this, "Please provides teacher category", Toast.LENGTH_SHORT).show();
        } else if (bitmap == null) {
            insertData();
        } else {
            progressDialog.setMessage("Uploading Details");
            progressDialog.show();
            //   uploadImage();
        }
    }

    // #######################################################################################################
    // ##################################### Insert Data Code #################################################
    // ######################################################################################################

    private void insertData() {

        progressDialog.setMessage("Uploading details");
        progressDialog.show();

        dbRef = reference.child(category); // dont change this
        final String uniquekey = dbRef.push().getKey();

        // #########################
        //  upload data on database and use model data getter and setter method.
        MedicineModels medicineModels = new MedicineModels(name, description, indicators, dosages, interaction, warnings, conditions, effect, uniquekey);
        dbRef.child(uniquekey).setValue(medicineModels).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(AddMedicineActivity.this, "Details upload Successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AddMedicineActivity.this, "Details upload Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}