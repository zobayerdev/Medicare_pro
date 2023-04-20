package com.trodev.medicarepro.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.medicarepro.models.MedicineModels;

import java.util.List;

public class MedicineAdapters extends RecyclerView.Adapter<MedicineAdapters.MedicineViewHolder> {

    // eikhnae amra list declear korbo kon seikhaner list er Data ta check korte hobe
    private List<MedicineModels> list;
    private Context context;
    private String category;

    public MedicineAdapters(List<MedicineModels> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public MedicineAdapters.MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapters.MedicineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {
        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
