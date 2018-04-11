package com.ikechukwuakalu.med_manager.user_profile;

import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.di.scopes.FragmentScoped;
import com.ikechukwuakalu.med_manager.user_profile.editor.EditorContract;
import com.ikechukwuakalu.med_manager.user_profile.editor.EditorFragment;
import com.ikechukwuakalu.med_manager.user_profile.editor.EditorPresenter;
import com.ikechukwuakalu.med_manager.user_profile.viewer.ViewerContract;
import com.ikechukwuakalu.med_manager.user_profile.viewer.ViewerFragment;
import com.ikechukwuakalu.med_manager.user_profile.viewer.ViewerPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UserProfileModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ViewerFragment viewerFragment();

    @ActivityScoped
    @Binds
    abstract ViewerContract.Presenter viewerPresenter(ViewerPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract EditorFragment editorFragment();

    @ActivityScoped
    @Binds
    abstract EditorContract.Presenter editorPresenter(EditorPresenter presenter);
}
