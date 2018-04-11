package com.ikechukwuakalu.med_manager.auth;

import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.di.scopes.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AuthFragment authFragment();

    @ActivityScoped
    @Binds
    abstract AuthContract.Presenter presenter(AuthPresenter authPresenter);
}
