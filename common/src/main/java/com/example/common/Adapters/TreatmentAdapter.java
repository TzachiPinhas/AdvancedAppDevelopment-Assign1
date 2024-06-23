package com.example.common.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Treatment;
import com.example.common.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder> {

    private Context context;
    private ArrayList<Treatment> treatments;


    public TreatmentAdapter(Context context, ArrayList<Treatment> treatments) {
        this.context = context;
        this.treatments = treatments;
    }


    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horisontal_treatment_item, parent, false);
        return new TreatmentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        Treatment treatment = getItem(position);
        holder.treatment_name.setText(treatment.getName());
        holder.treatment_date.setText(treatment.getDate());
        holder.treatment_description.setText(treatment.getDescription());
        holder.treatment_price.setText(treatment.getPrice());
    }


    @Override
    public int getItemCount() {
        if (treatments == null)
            return 0;
        return treatments.size();
    }

    public Treatment getItem(int position) {
        return treatments.get(position);
    }

    public class TreatmentViewHolder extends RecyclerView.ViewHolder {

          private MaterialTextView treatment_name;
            private MaterialTextView treatment_date;
            private MaterialTextView treatment_description;
            private MaterialTextView treatment_price;

            public TreatmentViewHolder(@NonNull View itemView) {
                super(itemView);
                treatment_name = itemView.findViewById(R.id.name_Txt);
                treatment_date = itemView.findViewById(R.id.treat_date);
                treatment_description = itemView.findViewById(R.id.treat_info_Text);
                treatment_price = itemView.findViewById(R.id.price_Txt);
            }
        }
}

