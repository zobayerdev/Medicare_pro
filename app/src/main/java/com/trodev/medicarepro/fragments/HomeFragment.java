package com.trodev.medicarepro.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.activities.AllMedicineActivity;
import com.trodev.medicarepro.adapter.MedicineAdapter;
import com.trodev.medicarepro.models.MedicineData;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView capsuleRv;
    private List<MedicineData> capsuleList;
    private ProgressDialog progressDialog;
    private MedicineAdapter adapter;
    private DatabaseReference reference, dbRef;
    private Context mContext;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {

        mContext = context;
        super.onAttach(context);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        // get data from firebase database
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");

        //init recycler views
        capsuleRv = view.findViewById(R.id.capsuleRv);
        // progress bar
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait for sometimes");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        CapsuleData();

        return view;
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
                    capsuleRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MedicineAdapter(capsuleList, getContext(), "Capsules");
                    capsuleRv.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.hide();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}