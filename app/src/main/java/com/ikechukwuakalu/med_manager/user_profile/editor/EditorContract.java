package com.ikechukwuakalu.med_manager.user_profile.editor;

import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;
import com.ikechukwuakalu.med_manager.data.models.User;

public interface EditorContract {

    interface View extends BaseView<Presenter> {

        void preFillFormData(User user);

        void showSaveSuccess();

        void showSaveFailure(String message);

        void showProfileViewer();
    }

    interface Presenter extends BasePresenter<View> {

        void updateUserDetails(User user);
    }
}
