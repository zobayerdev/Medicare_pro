package com.trodev.medicarepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.models.MedicineData;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewAdapter> {
    private List<MedicineData> list;
    private Context context;
    private String category;

    public MedicineAdapter(List<MedicineData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public MedicineViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.medicine_item_layout, parent, false);

        return new MedicineViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewAdapter holder, int position) {

        MedicineData item = list.get(position);

        // set holder to set text all layout
        holder.name.setText(item.getName());
        holder.shortdescription.setText(item.getDetails());
        holder.indicatorsTv.setText(item.getIndication());
        holder.dosageTv.setText(item.getDosage());
        holder.interactionTv.setText(item.getInteraction());
        holder.effectTv.setText(item.getEffect());
        holder.warningsTv.setText(item.getWarnings());
        holder.conditionTv.setText(item.getConditions());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MedicineViewAdapter extends RecyclerView.ViewHolder {
        private TextView name, shortdescription, indicatorsTv, dosageTv, interactionTv, effectTv,  warningsTv, conditionTv ;
        private MaterialButton update;

        public MedicineViewAdapter(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.nameTv);
            shortdescription = itemView.findViewById(R.id.shortTv);
            indicatorsTv = itemView.findViewById(R.id.indicatorsTv);
            dosageTv = itemView.findViewById(R.id.dosageTv);
            interactionTv = itemView.findViewById(R.id.interactionTv);
            effectTv = itemView.findViewById(R.id.effectTv);
            warningsTv = itemView.findViewById(R.id.warningsTv);
            conditionTv = itemView.findViewById(R.id.conditionTv);


            //init buttons
            update = itemView.findViewById(R.id.updateBtn);
        }
    }
}
