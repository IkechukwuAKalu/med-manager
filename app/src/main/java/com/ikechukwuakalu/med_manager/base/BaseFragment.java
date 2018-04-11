package com.ikechukwuakalu.med_manager.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class BaseFragment extends Fragment {

    protected void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show();
    }

    protected void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
                .show();
    }

    protected void setUpToolbar(Toolbar toolbar) {
        BaseAppCompatActivity activity = (BaseAppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
