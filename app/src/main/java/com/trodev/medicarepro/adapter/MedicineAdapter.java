package com.trodev.medicarepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
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

        /*set adapter with layout views that get all kind of data*/
        View view = LayoutInflater.from(context).inflate(R.layout.medicine_item_layout, parent, false);

        return new MedicineViewAdapter(view);
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


/*        try {
            Picasso.get().load(item.getImage()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
        return list.size();
    }

    public class MedicineViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, development, types, description, url, warning, condi;
        private ImageView image;
        private Button update;


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

            // apps image
            // image = itemView.findViewById(R.id.studentImage);
            update = itemView.findViewById(R.id.updateBtn);
        }
    }
}
