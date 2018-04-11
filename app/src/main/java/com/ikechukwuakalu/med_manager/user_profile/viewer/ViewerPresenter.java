package com.ikechukwuakalu.med_manager.user_profile.viewer;

import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.UserSharedPreferenceHelper;

import javax.inject.Inject;

@ActivityScoped
public class ViewerPresenter implements ViewerContract.Presenter {

    private ViewerContract.View view;
    private UserSharedPreferenceHelper spHelper;

    @Inject
    public ViewerPresenter(UserSharedPreferenceHelper spHelper) {
        this.spHelper = spHelper;
    }

    @Override
    public void attach(ViewerContract.View view) {
        this.view = view;
        fetchUserDetails();
    }

    @Override
    public void detach() {
        if (this.view == null) view = null;
    }

    private void fetchUserDetails() {
        User user = spHelper.getUserDetails();
        if (view != null) view.showUserDetails(user);
    }
}