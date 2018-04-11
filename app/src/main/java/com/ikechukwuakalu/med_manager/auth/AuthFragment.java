package com.ikechukwuakalu.med_manager.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.medications.MedicationsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@ActivityScoped
public class AuthFragment extends BaseFragment implements AuthContract.View {

    private int SIGN_IN_CODE = 101;

    @Inject GoogleSignInClient signInClient;
    @Inject AuthContract.Presenter presenter;

    @BindView(R.id.sign_in_button) SignInButton signInButton;

    private Unbinder unbinder;

    @Inject
    public AuthFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        // Expose ButterKnife to the View
        unbinder = ButterKnife.bind(this, view);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleSignInResult(task);
        }
    }

    @Override
    public void showSignInSuccess() {
        showShortToast(getContext(), "Sign in successful");
        Intent intent = new Intent(getActivity(), MedicationsActivity.class);
        startActivity(intent);
        Activity activity = getActivity();
        if (activity != null) activity.finish();
    }

    @Override
    public void showSignInFailure(String message) {
        showLongToast(getContext(), message+" Unable to sign in");
    }
}