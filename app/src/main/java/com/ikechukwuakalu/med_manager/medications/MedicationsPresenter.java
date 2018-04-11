package com.ikechukwuakalu.med_manager.medications;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ikechukwuakalu.med_manager.data.MedicationsRepository;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.data.models.User;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.Logger;
import com.ikechukwuakalu.med_manager.utils.UserSharedPreferenceHelper;
import com.ikechukwuakalu.med_manager.utils.espresso.EspressoIdlingResource;
import com.ikechukwuakalu.med_manager.utils.rx.RxScheduler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@ActivityScoped
public class MedicationsPresenter implements MedicationsContract.Presenter{

    private MedicationsRepository medicationsRepo;
    private GoogleSignInClient signInClient;
    private UserSharedPreferenceHelper spHelper;
    private RxScheduler rxScheduler;
    private CompositeDisposable disposables = new CompositeDisposable();
    private MedicationsContract.View view;

    @Inject
    MedicationsPresenter(MedicationsRepository medicationsRepo, GoogleSignInClient signInClient,
                         UserSharedPreferenceHelper spHelper, RxScheduler rxScheduler) {
        this.medicationsRepo = medicationsRepo;
        this.signInClient = signInClient;
        this.spHelper = spHelper;
        this.rxScheduler = rxScheduler;
    }

    @Override
    public void attach(MedicationsContract.View view) {
        this.view = view;
        checkUserSignedIn();
        fetchUserDetails();
    }

    private void checkUserSignedIn() {
        if (! spHelper.isUserSignedIn()) {
            view.showAuthScreen();
        }
    }

    @Override
    public void detach() {
        if (view != null) {
            view = null;
        }
        disposables.clear();
    }

    @Override
    public void signOutUser(Activity activity) {
        signInClient.signOut().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    spHelper.signUserOut();
                    if (view != null) view.showSignOutSuccess();
                } else {
                    if (view != null) view.showSignOutFailure("Unable to complete Sign out");
                }
            }
        });
    }

    private void fetchUserDetails() {
        User user = spHelper.getUserDetails();
        if (view != null) {
            view.showUserDetails(user);
        }
    }

    @Override
    public void fetchMedications() {
        EspressoIdlingResource.increment();
        Disposable disposable = medicationsRepo.getMedications()
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.ui())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (! EspressoIdlingResource.getIdlingResource().isIdleNow()) EspressoIdlingResource.decrement();
                    }
                })
                .subscribe(new Consumer<List<Medication>>() {
                    @Override
                    public void accept(List<Medication> medications) throws Exception {
                        if (view != null) {
                            if (medications.size() > 0) view.showMedications(medications);
                            else view.showNoMedicationFound();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.error(throwable.getMessage(), true);
                    }
                });
        disposables.add(disposable);
    }
}