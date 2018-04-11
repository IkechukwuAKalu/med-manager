package com.ikechukwuakalu.med_manager.medications;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.data.local.Medication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsAdapter extends RecyclerView.Adapter<MedicationsAdapter.MedicationsViewHolder> {

    private List<Medication> medications;
    private LayoutInflater inflater;
    private Context context;

    MedicationsAdapter(List<Medication> medications, Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.medications = medications;
    }

    @NonNull
    @Override
    public MedicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_medication_item, parent, false);
        return new MedicationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationsViewHolder holder, int position) {
        Medication medication = medications.get(position);
        holder.bind(medication);
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    class MedicationsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.med_icon) ImageView medIcon;
        @BindView(R.id.med_name) TextView medName;
        @BindView(R.id.med_desc) TextView medDescription;
        @BindView(R.id.med_interval) TextView medInterval;

        MedicationsViewHolder(View itemView) {
            super(itemView);
            // Initialize Butter knife
            ButterKnife.bind(this, itemView);
        }

        /**
         * Binds data to the View
         * @param medication the Medication Object
         */
        void bind(Medication medication) {
            medIcon.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_launcher_background)
            );
            medName.setText(medication.getName());
            medDescription.setText(medication.getDescription());
            medInterval.setText(medication.getInterval());
        }
    }
}
