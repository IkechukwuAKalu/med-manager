package com.ikechukwuakalu.med_manager.user_profile.editor;

import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.UserSharedPreferenceHelper;

import javax.inject.Inject;

@ActivityScoped
public class EditorPresenter implements EditorContract.Presenter {

    private EditorContract.View view;
    private UserSharedPreferenceHelper spHelper;

    @Inject
    EditorPresenter(UserSharedPreferenceHelper spHelper) {
        this.spHelper = spHelper;
    }

    @Override
    public void attach(EditorContract.View view) {
        this.view = view;
        fetchUserData();
    }

    private void fetchUserData() {
        User user = spHelper.getUserDetails();
        if (view != null) view.preFillFormData(user);
    }

    @Override
    public void detach() {
        if (view != null) view = null;
    }

    @Override
    public void updateUserDetails(User user) {
        if (! isNameOrEmailEmpty(user)) {
            spHelper.signInUser(user);
            if (view != null) {
                view.showSaveSuccess();
                view.showProfileViewer();
            }
        } else {
            if (view != null) view.showSaveFailure("Name or Email cannot be empty");
        }
    }

    private boolean isNameOrEmailEmpty(User user) {
        return user.getName().isEmpty() || user.getEmail().isEmpty();
    }
}
