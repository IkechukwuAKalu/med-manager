package com.ikechukwuakalu.med_manager.medications;

import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.di.scopes.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MedicationsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MedicationsFragment fragment();

    @ActivityScoped
    @Binds
    abstract MedicationsContract.Presenter presenter(MedicationsPresenter presenter);
}
