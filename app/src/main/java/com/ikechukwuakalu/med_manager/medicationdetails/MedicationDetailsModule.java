package com.ikechukwuakalu.med_manager.medicationdetails;

import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.di.scopes.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MedicationDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MedicationDetailsFragment fragment();

    @ActivityScoped
    @Binds
    abstract MedicationDetailsContract.Presenter presenter(MedicationDetailsPresenter presenter);

    @ActivityScoped
    @Provides
    @MedicationId
    static String provideMedicationId(MedicationDetailsActivity activity) {
        return activity.getIntent()
                .getStringExtra(MedicationDetailsActivity.MEDICATION_ID);
    }
}
