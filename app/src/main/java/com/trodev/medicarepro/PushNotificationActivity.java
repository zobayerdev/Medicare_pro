package com.trodev.medicarepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trodev.medicarepro.activities.AddMedicineActivity;
import com.trodev.medicarepro.models.MedicineData;
import com.trodev.medicarepro.models.ModelNotification;

public class PushNotificationActivity extends AppCompatActivity {

    /*declare all packages*/
    private EditText titleET, desET, dateET;
    private MaterialButton uploadBtn;
    private Spinner AddCategory;
    private String category;
    private String title, des, date;
    private DatabaseReference reference, dbRef;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        //set action bar title
        getSupportActionBar().setTitle("Push Notification");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*init all views*/
        titleET = findViewById(R.id.titleET);
        desET = findViewById(R.id.desET);
        dateET = findViewById(R.id.dateET);
        uploadBtn = findViewById(R.id.uploadBtn);
        AddCategory = findViewById(R.id.AddCategory);

        /*Progress Dialog are init*/
        progressDialog = new ProgressDialog(this);


        /*firebase database init*/
        reference = FirebaseDatabase.getInstance().getReference().child("Notification");
        // storageReference = FirebaseStorage.getInstance().getReference();

        /*category selected data*/
        String[] items = new String[]{"Select Category", "Alert", "Notification"};
        AddCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)); //default spinner dropdown menu

        AddCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                category = AddCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(PushNotificationActivity.this, "Not selected", Toast.LENGTH_SHORT).show();
            }
        });


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validInfo();
            }
        });
    }

    private void validInfo() {

        title = titleET.getText().toString().trim();
        des = desET.getText().toString().trim();
        date = dateET.getText().toString().trim();

        if (title.isEmpty()) {
            titleET.setError("Title is required");
            titleET.requestFocus();
        } else if (des.isEmpty()) {
            desET.setError("Description is required");
            desET.requestFocus();
        } else if (date.isEmpty()) {
            dateET.setError("Date is required");
            dateET.requestFocus();
        } else if (category.equals("Select Category")) {
            Toast.makeText(this, "Please provides category", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Uploading Details");
            progressDialog.show();
            insertData();
        }
    }


    // #####################################################################################################
    // ##################################### Insert Data Code ###############################################
    // ####################################################################################################

    private void insertData() {
        /*Inserted data on firebase database*/
        // don't change this because it's our category based database
        dbRef = reference.child(category);
        final String uniquekey = dbRef.push().getKey();

        // #########################
        // change Data class to get data from this activity
        ModelNotification modelNotification = new ModelNotification(title, des, date, uniquekey);

        dbRef.child(uniquekey).setValue(modelNotification).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(PushNotificationActivity.this, "Details upload Successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(PushNotificationActivity.this, "Details upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}