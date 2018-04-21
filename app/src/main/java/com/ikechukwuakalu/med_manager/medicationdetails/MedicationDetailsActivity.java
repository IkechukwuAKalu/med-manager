package com.ikechukwuakalu.med_manager.medicationdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseAppCompatActivity;

import javax.inject.Inject;

public class MedicationDetailsActivity extends BaseAppCompatActivity{

    public static final String MEDICATION_ID = "medication_id";

    @Inject MedicationDetailsFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_details);

        addFragment(fragment, R.id.med_details_container);
    }
}
