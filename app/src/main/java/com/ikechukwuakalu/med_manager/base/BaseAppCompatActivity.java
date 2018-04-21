package com.ikechukwuakalu.med_manager.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;

import com.ikechukwuakalu.med_manager.BaseApplication;
import com.ikechukwuakalu.med_manager.utils.espresso.EspressoIdlingResource;

import dagger.android.support.DaggerAppCompatActivity;

@SuppressLint("Registered")
public class BaseAppCompatActivity extends DaggerAppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getApplication()).getRefWatcher()
                .watch(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    protected void addFragment(Fragment fragment, int fragmentId) {
        getSupportFragmentManager().beginTransaction()
                .add(fragmentId, fragment)
                .commit();
    }

    protected void replaceFragment(Fragment fragment, int fragmentId, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentId, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
