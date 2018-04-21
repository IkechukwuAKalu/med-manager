package com.ikechukwuakalu.med_manager.user_profile.viewer;

import android.net.Uri;
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
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.user_profile.UserProfileActivity;
import com.ikechukwuakalu.med_manager.utils.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScoped
public class ViewerFragment extends BaseFragment implements ViewerContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.user_photo) SimpleDraweeView userPhoto;
    @BindView(R.id.user_name) TextView userName;
    @BindView(R.id.user_profession) TextView userProfession;
    @BindView(R.id.user_phone) TextView userPhone;
    @BindView(R.id.user_email) TextView userEmail;
    @BindView(R.id.user_blood_group) TextView userBloodGroup;
    @BindView(R.id.user_genotype) TextView userGenoType;

    @Inject ViewerContract.Presenter presenter;

    @Inject
    public ViewerFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile_viewer, container, false);
        ButterKnife.bind(this, view);
        setUpToolbar(toolbar);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile_viewer, menu);

        menu.findItem(R.id.action_edit_profile)
                .setIcon(getResources().getDrawable(R.drawable.ic_edit_white));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        if (id == R.id.action_edit_profile) {
            UserProfileActivity activity = (UserProfileActivity) getActivity();
            if (activity != null) activity.showEditorFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserDetails(User user) {
        String name = user.getName();
        String profession = checkIfEmpty(user.getProfession(), "Profession not set");
        String phone = "Phone:  " + checkIfEmpty(user.getPhone(), "Not set");
        String email = "Email:  " + checkIfEmpty(user.getEmail(), "Not set");
        String bloodGroup = "Blood Group:   " + checkIfEmpty(user.getBloodGroup(), "Unknown");
        String genoType = "Genotype:    " + checkIfEmpty(user.getGenoType(), "Unknown");

        userName.setText(name);
        userProfession.setText(profession);
        userPhone.setText(phone);
        userEmail.setText(email);
        userBloodGroup.setText(bloodGroup);
        userGenoType.setText(genoType);

        showUserPhoto(user.getPhotoUri());
    }

    /**
     * This checks if a given String is empty and returns a default value if it is
     * @param data the String data to check for emptiness
     * @param defValue the default value to return if empty
     * @return the String if not empty else the default value
     */
    private String checkIfEmpty(String data, String defValue) {
        return data.equals("") ? defValue : data;
    }

    private void showUserPhoto(String photoUri) {
        if (photoUri != null) {
            Logger.debug(photoUri);
            Uri uri = Uri.parse(photoUri);
            userPhoto.setImageURI(uri);
        }
    }
}