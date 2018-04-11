package com.ikechukwuakalu.med_manager.auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;

public interface AuthContract {

    interface View extends BaseView<Presenter> {

        void showSignInSuccess();

        void showSignInFailure(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void handleSignInResult(Task<GoogleSignInAccount> task);
    }
}
