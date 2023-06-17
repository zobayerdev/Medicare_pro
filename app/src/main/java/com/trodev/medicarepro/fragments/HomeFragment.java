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
import com.google.firebase.database.Query;
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
    private DatabaseReference reference;
    private String lastKey = "";
    private int pageSize = 20;
    private boolean isLoading = false; // Flag to prevent multiple simultaneous data loads
    private boolean isLastPage = false; // Flag to indicate if all data has been loaded
    private LinearLayoutManager layoutManager;
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
        reference = FirebaseDatabase.getInstance().getReference().child("Medicine");

        layoutManager = new LinearLayoutManager(getContext());
        capsuleRv.setLayoutManager(layoutManager);
        capsuleList = new ArrayList<>();
        adapter = new MedicineUserAdapter(capsuleList, getContext());
        capsuleRv.setAdapter(adapter);

        capsuleRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pageSize) {
                        loadData();
                    }
                }

            }
        });

        loadData();


        return view;
    }

    private void loadData() {

        isLoading = true;

        Query query;

        if (lastKey.isEmpty()) {
            query = reference.orderByKey().limitToFirst(pageSize);
        } else {
            query = reference.orderByKey().startAfter(lastKey).limitToFirst(pageSize);
        }


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 0;

                if (dataSnapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    capsuleRv.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MedicineData data = snapshot.getValue(MedicineData.class);
                        capsuleList.add(data);

                        count++;
                        lastKey = snapshot.getKey();

                    }

                    adapter.notifyDataSetChanged();

                    if (count < pageSize) {
                        isLastPage = true;
                    }

                    isLoading = false;

                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    capsuleRv.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isLoading = false;
            }
        });

    }

}