package com.trodev.medicarepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.models.MedicineData;

import java.util.List;

public class MedicineUserAdapter extends RecyclerView.Adapter<MedicineUserAdapter.MedicineViewAdapter> {
    private List<MedicineData> list;
    private Context context;

    public MedicineUserAdapter(List<MedicineData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineUserAdapter.MedicineViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*set adapter with layout views that get all kind of data*/
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicine_list, parent, false);

        return new MedicineUserAdapter.MedicineViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewAdapter holder, int position) {

        /*set model to adapter to set data on views*/
        MedicineData item = list.get(position);

        /*set data set on views*/
        holder.typeTv.setText(item.getType());
        holder.nameTv.setText(item.getBrand());
        holder.strengthTv.setText(item.getStrength());
        holder.formTv.setText(item.getForm());
        holder.genericTv.setText(item.getGeneric());
        holder.manufacturerTv.setText(item.getManufacturer());
        holder.priceTv.setText(item.getPrice());
        holder.indicationTv.setText(item.getIndication());
        holder.dosageTv.setText(item.getDosage());
        holder.precautionsTv.setText(item.getPrecautions());
        holder.pregnancyTv.setText(item.getPregnancy());
        holder.side_effectsTv.setText(item.getSide_effects());
        holder.storageTv.setText(item.getStorage());

        boolean isExpandable = list.get(position).isExpandable();
        holder.expandable_layout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MedicineViewAdapter extends RecyclerView.ViewHolder {

        private TextView nameTv, typeTv, strengthTv, formTv, genericTv, manufacturerTv, priceTv, indicationTv,
                dosageTv, precautionsTv, pregnancyTv, side_effectsTv, storageTv;

        LinearLayout linear_layout;
        RelativeLayout expandable_layout;

        MaterialCardView cardView;

        public MedicineViewAdapter(@NonNull View itemView) {
            super(itemView);

            typeTv = itemView.findViewById(R.id.typeTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            strengthTv = itemView.findViewById(R.id.strengthTv);
            formTv = itemView.findViewById(R.id.formTv);
            genericTv = itemView.findViewById(R.id.genericTv);
            manufacturerTv = itemView.findViewById(R.id.manufacturerTv);
            priceTv = itemView.findViewById(R.id.priceTv);
            indicationTv = itemView.findViewById(R.id.indicationTv);
            dosageTv = itemView.findViewById(R.id.dosageTv);
            precautionsTv = itemView.findViewById(R.id.precautionsTv);
            pregnancyTv = itemView.findViewById(R.id.pregnancyTv);
            side_effectsTv = itemView.findViewById(R.id.side_effectsTv);
            storageTv = itemView.findViewById(R.id.storageTv);


            linear_layout = itemView.findViewById(R.id.linear_layout);
            expandable_layout = itemView.findViewById(R.id.expandable_layout);

            //here is expandable code
            linear_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MedicineData medicineData = list.get(getAdapterPosition());
                    medicineData.setExpandable(!medicineData.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}

