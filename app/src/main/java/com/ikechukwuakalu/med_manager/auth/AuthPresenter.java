package com.ikechukwuakalu.med_manager.auth;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.Logger;
import com.ikechukwuakalu.med_manager.utils.UserSharedPreferenceHelper;

import javax.inject.Inject;

@ActivityScoped
public class AuthPresenter implements AuthContract.Presenter {

    private UserSharedPreferenceHelper spHelper;

    private AuthContract.View view;
    private Context context;

    @Inject
    AuthPresenter(Context context, UserSharedPreferenceHelper spHelper) {
        this.context = context;
        this.spHelper = spHelper;
    }

    @Override
    public void attach(AuthContract.View view) {
        this.view = view;
        checkForActiveAccount();
    }

    private void checkForActiveAccount() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            saveUserDetails(account);
        }
    }

    private void saveUserDetails(GoogleSignInAccount account) {
        String photoUrl = null;
        if (account.getPhotoUrl() != null) photoUrl = account.getPhotoUrl().toString();
        User user = new User(account.getId(),
                account.getDisplayName(),
                null,
                account.getEmail(),
                null,
                null,
                null,
                photoUrl);
        spHelper.signInUser(user);

        showSignInSuccess();
    }

    private void showSignInSuccess() {
        if (view != null) {
            view.showSignInSuccess();
        }
    }

    @Override
    public void detach() {
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            saveUserDetails(account);
        } catch (ApiException e) {
            Logger.error(e.getMessage(), true);
            if (view != null) {
                view.showSignInFailure("Unable to sign in; code:" + e.getStatusCode());
            }
        }
    }
}