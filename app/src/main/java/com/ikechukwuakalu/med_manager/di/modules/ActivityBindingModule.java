package com.ikechukwuakalu.med_manager.di.modules;

import com.ikechukwuakalu.med_manager.auth.AuthActivity;
import com.ikechukwuakalu.med_manager.auth.AuthModule;
import com.ikechukwuakalu.med_manager.createmed.CreateMedicationModule;
import com.ikechukwuakalu.med_manager.createmed.CreateMedicationActivity;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.medicationdetails.MedicationDetailsActivity;
import com.ikechukwuakalu.med_manager.medicationdetails.MedicationDetailsModule;
import com.ikechukwuakalu.med_manager.medications.MedicationsActivity;
import com.ikechukwuakalu.med_manager.medications.MedicationsModule;
import com.ikechukwuakalu.med_manager.user_profile.UserProfileActivity;
import com.ikechukwuakalu.med_manager.user_profile.UserProfileModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {AuthModule.class})
    abstract AuthActivity authActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MedicationsModule.class})
    abstract MedicationsActivity medicationsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {UserProfileModule.class})
    abstract UserProfileActivity userProfileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {CreateMedicationModule.class})
    abstract CreateMedicationActivity createMedicationActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MedicationDetailsModule.class})
    abstract MedicationDetailsActivity medicationDetailsActivity();
}
