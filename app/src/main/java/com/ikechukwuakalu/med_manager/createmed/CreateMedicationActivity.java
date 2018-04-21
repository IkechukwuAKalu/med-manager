package com.ikechukwuakalu.med_manager.createmed;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseAppCompatActivity;

import javax.inject.Inject;

public class CreateMedicationActivity extends BaseAppCompatActivity {

    @Inject CreateMedicationFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_med);

        addFragment(fragment, R.id.create_med_container);
    }
}
