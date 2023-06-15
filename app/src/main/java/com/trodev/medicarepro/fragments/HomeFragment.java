package com.trodev.medicarepro.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.activities.AllMedicineActivity;
import com.trodev.medicarepro.adapter.MedicineUserAdapter;
import com.trodev.medicarepro.models.MedicineData;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView capsuleRv;
    private List<MedicineData> capsuleList;
    private ProgressBar progressBar;
    private MedicineUserAdapter adapter;
    private DatabaseReference reference, dbRef;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init recycler views
        capsuleRv = view.findViewById(R.id.capsuleRv);
        progressBar = view.findViewById(R.id.progressBar);

        // get data from firebase database
        reference = FirebaseDatabase.getInstance().getReference().child("Medicines");

        /*load recyclerview*/
        CapsuleData();
        return view;
    }

    // ############################################################################################
    // ############################### Capsule Department ########################################
    // ############################################################################################
    private void CapsuleData() {

        /*database name set on database reference*/
        dbRef = reference.child("Capsules");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                capsuleList = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    progressBar.setVisibility(View.VISIBLE);
                    capsuleRv.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    capsuleRv.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MedicineData data = snapshot.getValue(MedicineData.class);
                        capsuleList.add(data);
                    }
                    capsuleRv.setHasFixedSize(true);
                    capsuleRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MedicineUserAdapter(capsuleList, getContext(), "Capsules");
                    capsuleRv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}