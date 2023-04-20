package com.trodev.medicarepro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.adapter.MedicineAdapter;
import com.trodev.medicarepro.models.MedicineData;

import java.util.ArrayList;
import java.util.List;

public class AllMedicineActivity extends AppCompatActivity {
    private RecyclerView capsuleRv;
    private List<MedicineData> capsuleList;
    private ProgressDialog progressDialog;
    private MedicineAdapter adapter;
    private DatabaseReference reference, dbRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_medicine);

        // action bar title
        getSupportActionBar().setTitle("All Medicine");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //init recycler views
        capsuleRv = findViewById(R.id.capsuleRv);

        // progress bar
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait for sometimes");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        // get data from firebase database
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");

        CapsuleData();

    }

    // ############################################################################################
    // ############################### Medicine Department ########################################
    // ############################################################################################
    private void CapsuleData() {

        progressDialog.setMessage("Data Fetching");
        progressDialog.show();

        dbRef = reference.child("Capsules");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                capsuleList = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    progressDialog.show();
                    capsuleRv.setVisibility(View.GONE); // change
                } else {
                    progressDialog.hide();
                    capsuleRv.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MedicineData data = snapshot.getValue(MedicineData.class);
                        capsuleList.add(data);
                    }
                    progressDialog.hide();
                    capsuleRv.setHasFixedSize(true);
                    capsuleRv.setLayoutManager(new LinearLayoutManager(AllMedicineActivity.this));
                    adapter = new MedicineAdapter(capsuleList, AllMedicineActivity.this, "Capsules");
                    capsuleRv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.hide();
                Toast.makeText(AllMedicineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}