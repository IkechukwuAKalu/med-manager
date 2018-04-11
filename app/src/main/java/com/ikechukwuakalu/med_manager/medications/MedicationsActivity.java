package com.ikechukwuakalu.med_manager.medications;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseAppCompatActivity;

import javax.inject.Inject;

public class MedicationsActivity extends BaseAppCompatActivity {

    @Inject MedicationsFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        addFragment(fragment, R.id.medications_container);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = fragment.getDrawer();
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
