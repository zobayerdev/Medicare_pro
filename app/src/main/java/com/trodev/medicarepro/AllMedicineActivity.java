package com.trodev.medicarepro;

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
import com.trodev.medicarepro.adapter.MedicineAdapter;
import com.trodev.medicarepro.models.MedicineData;

import java.util.ArrayList;
import java.util.List;

public class AllMedicineActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private RecyclerView capsulesRv;
    private List<MedicineData> list1;
    private MedicineAdapter adapter;
    private DatabaseReference reference, dbRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_medicine);


        // action bar title
        getSupportActionBar().setTitle("All Application");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait for sometimes");
        progressDialog.show();
       // progressDialog.setCanceledOnTouchOutside(false);

        // Recycler view finding
        capsulesRv = findViewById(R.id.capsulesRv);
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");
        Application();
    }

    // ############################################################################################
    // ############################### Application Department ######################################
    // ############################################################################################
    private void Application() {

        progressDialog.setMessage("Data Fetching");
     //   progressDialog.show();

        // category changes are here
        dbRef = reference.child("Capsules");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                   // progressDialog.show();
                    capsulesRv.setVisibility(View.GONE); // change
                } else {
                   // progressDialog.hide();
                    capsulesRv.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        MedicineData data = snapshot.getValue(MedicineData.class);

                        list1.add(data);

                        Toast.makeText(AllMedicineActivity.this, "All Medicine are here...!", Toast.LENGTH_SHORT).show();
                    }

                   //  progressDialog.hide();
                    capsulesRv.setHasFixedSize(true);
                    capsulesRv.setLayoutManager(new LinearLayoutManager(AllMedicineActivity.this));
                    adapter = new MedicineAdapter(list1, AllMedicineActivity.this, "Capsules");
                    capsulesRv.setAdapter(adapter);
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