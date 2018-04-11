package com.ikechukwuakalu.med_manager.medications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.auth.AuthActivity;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.user_profile.UserProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ActivityScoped
public class MedicationsFragment extends BaseFragment implements MedicationsContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    private ImageView navPhoto;
    private TextView navHeaderText;
    private TextView navHeaderSubtext;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.medication_items) RecyclerView medicationsList;

    @Inject MedicationsContract.Presenter presenter;

    @Inject
    public MedicationsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medications, container, false);
        ButterKnife.bind(this, view);
        // Use the Toolbar as the Action Bar
        MedicationsActivity activity = (MedicationsActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        setUpNavigation();
        setUpViews();
        // Notify activity this will inflate the menu
        setHasOptionsMenu(true);
        return view;
    }

    /**
     * This initializes and configures components for the Navigation Drawer
     */
    private void setUpNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Add listeners for item clicks
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpViews() {
        View navHeader = navigationView.getHeaderView(0);
        navPhoto = navHeader.findViewById(R.id.nav_photo);
        navHeaderText = navHeader.findViewById(R.id.nav_header_text);
        navHeaderSubtext = navHeader.findViewById(R.id.nav_header_subtext);

        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
        presenter.fetchMedications();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detach();
    }

    /**
     * This exposes the DrawerLayout Object so that the Activity can have access to it for back button presses
     * @return the Drawer Layout Object for the Fragment
     */
    public DrawerLayout getDrawer() {
        return drawer;
    }

    /**
     * TODO("Handle FAB to create new medication")
     */
    @OnClick(R.id.add_new_med)
    public void addNewMedication() {

    }

    @Override
    public void showSignOutSuccess() {
        showShortToast(getContext(), "Sign out successful");
        showAuthScreen();
    }

    @Override
    public void showSignOutFailure(String message) {
        showShortToast(getContext(), message);
    }

    @Override
    public void showAuthScreen() {
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
        Activity activity = getActivity();
        if (activity != null) activity.finish();
    }

    @Override
    public void showUserDetails(User user) {
        if (user.getPhotoUri() != null) {
            Picasso.get()
                    .load(user.getPhotoUri())
                    .placeholder(R.drawable.ic_account_box_black)
                    .into(navPhoto);
        }
        navHeaderText.setText(user.getName());
        navHeaderSubtext.setText(user.getEmail());
    }

    @Override
    public void showMedications(List<Medication> medications) {
        MedicationsAdapter adapter = new MedicationsAdapter(medications, getContext());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        medicationsList.setLayoutManager(llm);
        medicationsList.setAdapter(adapter);
    }

    @Override
    public void showNoMedicationFound() {
        showLongToast(getContext(), "No medication found");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_medications, menu);
        menu.findItem(R.id.action_search)
                .setIcon(getResources().getDrawable(R.drawable.ic_search_white));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            showShortToast(getContext(), "Search is enabled");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_medications:
                break;
            case R.id.nav_analyze:

                break;
            case R.id.nav_settings:

                break;
            case R.id.nav_sign_out:
                Activity activity = getActivity();
                if (activity != null) presenter.signOutUser(activity);
                else showShortToast(getContext(), "Activity is null");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}