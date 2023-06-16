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
    private String category;

    public MedicineUserAdapter(List<MedicineData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
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

        /*set data on views*/
        holder.name.setText(item.getName());
        holder.development.setText(item.getIndica());
        holder.types.setText(item.getDosage());
        holder.description.setText(item.getInter());
        holder.url.setText(item.getEffect());
        holder.warning.setText(item.getWarnings());
        holder.condi.setText(item.getCondi());

        boolean isExpandable = list.get(position).isExpandable();
        holder.expandable_layout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        /*animation view with slider*/
        // holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slider));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MedicineViewAdapter extends RecyclerView.ViewHolder {
        private TextView name, development, types, description, url, warning, condi;

        LinearLayout linear_layout;
        RelativeLayout expandable_layout;

        MaterialCardView cardView;

        public MedicineViewAdapter(@NonNull View itemView) {
            super(itemView);

            /*Init all data from views layouts*/
            name = itemView.findViewById(R.id.nameTv);
            development = itemView.findViewById(R.id.indicaTv);
            warning = itemView.findViewById(R.id.warningTv);
            condi = itemView.findViewById(R.id.condiTv);
            types = itemView.findViewById(R.id.dosageTv);
            description = itemView.findViewById(R.id.interTv);
            url = itemView.findViewById(R.id.effectTv);
            cardView = itemView.findViewById(R.id.cardView);


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

