package com.ikechukwuakalu.med_manager.createmed;

import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.di.scopes.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CreateMedicationModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CreateMedicationFragment fragment();

    @ActivityScoped
    @Binds
    abstract CreateMedicationContract.Presenter presenter(CreateMedicationPresenter presenter);
}
