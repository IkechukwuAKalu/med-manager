package com.ikechukwuakalu.med_manager.medications;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.data.local.Medication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsAdapter extends RecyclerView.Adapter<MedicationsAdapter.MedicationsViewHolder> {

    private List<Medication> medications;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    MedicationsAdapter(List<Medication> medications, Context context, View.OnClickListener onClickListener) {
        inflater = LayoutInflater.from(context);
        this.medications = medications;
        this.onClickListener = onClickListener;
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

        @BindView(R.id.med_icon_text) TextView medIcon;
        @BindView(R.id.med_name) TextView medName;
        @BindView(R.id.med_desc) TextView medDescription;
        @BindView(R.id.med_interval) TextView medInterval;

        MedicationsViewHolder(View itemView) {
            super(itemView);
            // Initialize Butter knife
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onClickListener);
        }

        /**
         * Binds data to the View
         * @param medication the Medication Object
         */
        void bind(Medication medication) {
            itemView.setTag(medication.getId()); // for use in OnClickListener

            String firstChar = medication.getName().isEmpty() ?
                    "A" : medication.getName().toUpperCase().substring(0, 1);
            medIcon.setText(firstChar);
            medName.setText(medication.getName());
            medDescription.setText(medication.getDescription());
            String interval = getIntervalText(medication.getInterval());
            medInterval.setText(interval);


        }

        private String getIntervalText(String interval) {
            if (interval.contains(",")) {
                // format is 12,45
                String[] args = interval.split(",");
                return "Every " + args[0] + " hours, " + args[1] + " minutes";
            } else {
                // format is 12
                return "Every " + interval + " hours";
            }
        }
    }
}
