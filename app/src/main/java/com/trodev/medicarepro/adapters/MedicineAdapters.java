package com.trodev.medicarepro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.medicarepro.R;
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

        View view = LayoutInflater.from(context).inflate(R.layout.medicine_item_layout, parent, false);
        return new MedicineViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapters.MedicineViewHolder holder, int position) {
        // medicine model get this list
        MedicineModels item = list.get(position);

        // set holder to set text all layout
        holder.name.setText(item.getName());
        holder.shortdescription.setText(item.getDetails());


/*        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateStudentActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("roll",item.getRoll());
                intent.putExtra("result",item.getResult());
                intent.putExtra("image",item.getImage());
                intent.putExtra("key",item.getKey());
                intent.putExtra("category",category);

                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {

        private TextView name, shortdescription, types, description, url;
        private ImageView image;
        private Button update;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTv);
            shortdescription = itemView.findViewById(R.id.shortTv);
            types = itemView.findViewById(R.id.typesTv);
            description = itemView.findViewById(R.id.descriptionTv);
            url = itemView.findViewById(R.id.urlTv);

            //init buttons
            update = itemView.findViewById(R.id.updateBtn);
        }
    }
}
