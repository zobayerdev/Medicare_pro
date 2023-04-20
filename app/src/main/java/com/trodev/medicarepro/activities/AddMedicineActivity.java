package com.trodev.medicarepro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.models.MedicineData;

public class AddMedicineActivity extends AppCompatActivity {
    private EditText nameET, indicaET, dosageET, interET, effectET, warningsET, condiET;
    private Spinner AddCategory;
    private Button uploadBtn;
    private String category;
    private ProgressDialog progressDialog;
    private String name, indica, dosage, inter, effect, warnings, condi;
    private DatabaseReference reference, dbRef;

/*    private String downloadUrl = "";
    private final int REQ = 1;
    private Bitmap bitmap = null;
    private ImageView appsIV;
    private StorageReference storageReference;*/

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        //set action bar title
        getSupportActionBar().setTitle("Add Medicine");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // finding all textview and button
        // appsIV = findViewById(R.id.appsIV);

        //edittext init
        nameET = findViewById(R.id.nameET);
        indicaET = findViewById(R.id.indicaET);
        dosageET = findViewById(R.id.dosageET);
        interET = findViewById(R.id.interET);
        effectET = findViewById(R.id.effectET);
        condiET = findViewById(R.id.condiET);
        warningsET = findViewById(R.id.warningsET);

        // button init
        AddCategory = findViewById(R.id.AddCategory);
        uploadBtn = findViewById(R.id.uploadBtn);

        /*Progress Dialog are init*/
        progressDialog = new ProgressDialog(this);

        /*firebase database init*/
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");
       // storageReference = FirebaseStorage.getInstance().getReference();

        /*category selected data*/
        String[] items = new String[]{"Select Category", "Powder for suspension", "Capsules", "Tablet", "Ointment", "Inhaler", "Drops", "Suppositories", "Injection"};
        AddCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)); //default spinner dropdown menu

        AddCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                category = AddCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddMedicineActivity.this, "Not selected", Toast.LENGTH_SHORT).show();
            }
        });


/*        appsIV.setOnClickListener(new View.OnClickListener() { // eikhane Change ache AddStudentImage hobe
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });*/


        uploadBtn.setOnClickListener(new View.OnClickListener() { // eikhane change ache AddStudentBtn
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

    }


    private void checkValidation() {
        // get text from edittext
        name = nameET.getText().toString().trim();
        indica = indicaET.getText().toString().trim();
        dosage = dosageET.getText().toString().trim();
        inter = interET.getText().toString().trim();
        effect = effectET.getText().toString().trim();
        warnings = warningsET.getText().toString().trim();
        condi = condiET.getText().toString().trim();


        if (name.isEmpty()) {
            nameET.setError("Medicine name is required");
            nameET.requestFocus();
        } else if (indica.isEmpty()) {
            indicaET.setError("Medicine Indication is required");
            indicaET.requestFocus();
        } else if (dosage.isEmpty()) {
            dosageET.setError("Medicine Dosage & Administration is required");
            dosageET.requestFocus();
        } else if (inter.isEmpty()) {
            interET.setError("Medicine Interaction is required");
            interET.requestFocus();
        } else if (effect.isEmpty()) {
            effectET.setError("Medicine Side Effects is required");
            effectET.requestFocus();
        } else if (warnings.isEmpty()) {
            warningsET.setError("Medicine Precautions Warnings is required");
            warningsET.requestFocus();
        } else if (condi.isEmpty()) {
            condiET.setError("Medicine Storage Condition is required");
            condiET.requestFocus();
        } else if (category.equals("Select Category")) {
            Toast.makeText(this, "Please provides medicine category", Toast.LENGTH_SHORT).show();
        } /*else if (bitmap == null) {
            insertData();
        }*/ else {
            progressDialog.setMessage("Uploading Details");
            progressDialog.show();
            // uploadImage();
            insertData();
        }

    }


    // #######################################################################################################
    // ##################################### Upload image Code ##############################################
    // #####################################################################################################
/*
    private void uploadImage() {

        progressDialog.setMessage("Uploading");
        progressDialog.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] finalimage = baos.toByteArray();


        final StorageReference filepath;
        filepath = storageReference.child("Trodev").child(finalimage + "png");
        final UploadTask uploadTask = filepath.putBytes(finalimage);
        uploadTask.addOnCompleteListener(AddMedicineActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() { // change AddStudent
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertData();
                                }
                            });
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(AddMedicineActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show(); // change AddStudent
                }
            }
        });
    }
*/

    // #####################################################################################################
    // ##################################### Insert Data Code ###############################################
    // ####################################################################################################

    private void insertData() {
        /*Inserted data on firebase database*/
        dbRef = reference.child(category); // don't change this because it's our category based database
        final String uniquekey = dbRef.push().getKey();

        // #########################
        // change Data class to get data from this activity
        MedicineData studentData = new MedicineData(name, indica, dosage, inter, effect, warnings, condi, uniquekey); //downloadUrl,
        dbRef.child(uniquekey).setValue(studentData).addOnSuccessListener(new OnSuccessListener<Void>() {
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


    // #######################################################################################################
    // ##################################### Open Gallery Code ###############################################
    // #####################################################################################################
/*    private void openGallery() {

        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appsIV.setImageBitmap(bitmap);
        }

    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicine_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.nav_medicine) {
            startActivity(new Intent(AddMedicineActivity.this, AllMedicineActivity.class));
            Toast.makeText(this, "View all medicine", Toast.LENGTH_SHORT).show();
        }

        if (itemId == R.id.nav_upload) {
            checkValidation();
            Toast.makeText(this, "Uploading", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}