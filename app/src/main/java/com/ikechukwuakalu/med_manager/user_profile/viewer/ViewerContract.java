package com.ikechukwuakalu.med_manager.user_profile.viewer;

import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;
import com.ikechukwuakalu.med_manager.data.models.User;

public interface ViewerContract {

    interface View extends BaseView<Presenter> {

        void showUserDetails(User user);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
