package com.trodev.medicarepro.activities;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.adapter.MedicineUserAdapter;
import com.trodev.medicarepro.models.MedicineData;

import java.util.ArrayList;
import java.util.List;

public class SpecifiqSearchActivity extends AppCompatActivity {

    EditText resultTv;
    String data;
    RecyclerView dataRv;
    private List<MedicineData> dataList, filterDataList;
    private MedicineUserAdapter adapter;
    private DatabaseReference reference;
    private ProgressBar progressBar;
    private String lastKey = "";
    private LinearLayoutManager layoutManager;

    private int pageSize = 10;
    private boolean isLoading = false; // Flag to prevent multiple simultaneous data loads
    private boolean isLastPage = false; // Flag to indicate if all data has been loaded

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifiq_search);

        resultTv = findViewById(R.id.resultTv);
        dataRv = findViewById(R.id.dataRv);
        searchView = findViewById(R.id.searchView);

        progressBar = findViewById(R.id.progressBar);

        data = getIntent().getStringExtra("data");

        resultTv.setText(data);

        // get data from firebase database
        reference = FirebaseDatabase.getInstance().getReference().child("Medicine");
        //


        /*set recyclerview on linearlayout*/
        layoutManager = new LinearLayoutManager(this);
        dataRv.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        filterDataList = new ArrayList<>();

        // change data list, set filterDataList
        adapter = new MedicineUserAdapter(filterDataList, this);
        dataRv.setAdapter(adapter);


        /*create function*/
        loadData();

        //  String searchQuery =  data.split("(\r\n|\r|\n)", -1)[0].trim();

        Intent intent = getIntent();
        String searchQuery = intent.getStringExtra("data").split("(\r\n|\r|\n)", 1)[0].trim();

        // Set the search query in the SearchView
        searchView.setQuery(searchQuery, false);



        //   implement search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                performSearch(searchQuery);

                return false;
            }
        });

    }

    private void performSearch(String searchQuery) {

        filterData(searchQuery);
        adapter.notifyDataSetChanged();

    }


/*    private void loadData() {

      Query query =  reference.orderByChild("brand")
                .startAt("Celofen")
                .endAt("Celofen"+"\uf8ff");

      query.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {

              if(snapshot.exists()){

                  for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                      MedicineData data = dataSnapshot.getValue(MedicineData.class);
                      dataList.add(data);
                  }

                  adapter.notifyDataSetChanged();;
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
                //.endAt(searchQuery + "\uf8ff");
*//*
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MedicineData data = snapshot.getValue(MedicineData.class);
                        dataList.add(data);

                    }

                    adapter.notifyDataSetChanged();

                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*//*

    }*/

/*    private void performSearch(String newText) {

        filterData(newText);
        adapter.notifyDataSetChanged();

    }*/

    private void filterData(String newText) {

        filterDataList.clear();

        if (TextUtils.isEmpty(newText)) {
            filterDataList.addAll(dataList);
        } else {
            for (MedicineData medicineData : dataList) {
                if (medicineData.getBrand().toLowerCase().contains(newText.toLowerCase())) {
                    filterDataList.add(medicineData);
                }
            }
        }

    }


    private void loadData() {

        /*is loading true*/
        isLoading = true;

        /*init query*/
        Query query;

        if (lastKey.isEmpty()) {
            query = reference.orderByKey().limitToFirst(pageSize);
        } else {
            query = reference.orderByKey().startAfter(lastKey).limitToFirst(pageSize);
        }

        /*listener on query*/
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 0;

                if (dataSnapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    dataRv.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MedicineData data = snapshot.getValue(MedicineData.class);
                        dataList.add(data);

                        count++;
                        lastKey = snapshot.getKey();

                    }

                    filterData(null);

                    adapter.notifyDataSetChanged();

                    if (count < pageSize) {
                        isLastPage = true;
                    }

                    isLoading = false;

                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    dataRv.setVisibility(View.GONE);
                    isLoading = false;
                    // reference.keepSynced(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isLoading = false;
            }
        });

    }

}