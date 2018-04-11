package com.ikechukwuakalu.med_manager.user_profile.editor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScoped
public class EditorFragment extends BaseFragment implements EditorContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.editor_name) EditText name;
    @BindView(R.id.editor_profession) EditText profession;
    @BindView(R.id.editor_phone) EditText phone;
    @BindView(R.id.editor_email) EditText email;
    @BindView(R.id.editor_blood_group) Spinner bloodGroup;
    @BindView(R.id.editor_genotype) Spinner genotype;

    @Inject EditorContract.Presenter presenter;

    @Inject
    public EditorFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile_editor, container, false);
        ButterKnife.bind(this, view);
        setUpToolbar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }
// TODO build check if set or else utility function
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile_editor, menu);

        menu.findItem(R.id.action_save_profile)
                .setIcon(getResources().getDrawable(R.drawable.ic_check_white));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_profile) {
            saveUserData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserData() {
        // TODO save from text inputs
    }

    @Override
    public void preFillFormData(User user) {
        name.setText(user.getName());
        profession.setText(user.getProfession());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    @Override
    public void showSaveSuccess() {
        showShortToast(getContext(), "Profile updated successfully");
    }

    @Override
    public void showSaveFailure(String message) {
        showLongToast(getContext(), message);
    }

    @Override
    public void showProfileViewer() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }
}
