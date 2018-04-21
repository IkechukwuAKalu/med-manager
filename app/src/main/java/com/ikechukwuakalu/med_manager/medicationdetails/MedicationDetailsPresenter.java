package com.ikechukwuakalu.med_manager.medicationdetails;

import com.ikechukwuakalu.med_manager.data.MedicationsRepository;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.Logger;
import com.ikechukwuakalu.med_manager.utils.espresso.EspressoIdlingResource;
import com.ikechukwuakalu.med_manager.utils.rx.RxScheduler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@ActivityScoped
public class MedicationDetailsPresenter implements MedicationDetailsContract.Presenter {

    private MedicationDetailsContract.View view;
    private String medicationId;
    private MedicationsRepository medicationsRepo;
    private RxScheduler rxScheduler;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    MedicationDetailsPresenter(@MedicationId String medicationId, MedicationsRepository medicationsRepo, RxScheduler rxScheduler) {
        this.medicationId = medicationId;
        this.medicationsRepo = medicationsRepo;
        this.rxScheduler = rxScheduler;
    }
    @Override
    public void attach(MedicationDetailsContract.View view) {
        this.view = view;
        fetchMedication();
    }

    private void fetchMedication() {
        EspressoIdlingResource.increment();
        Disposable disposable = medicationsRepo.getMedication(Integer.valueOf(medicationId))
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.ui())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (! EspressoIdlingResource.getIdlingResource().isIdleNow()) EspressoIdlingResource.decrement();
                    }
                })
                .subscribe(new Consumer<Medication>() {
                    @Override
                    public void accept(Medication medication) {
                        if (view != null) {
                            if (medication != null) view.showDetails(medication);
                            else view.showDataLoadError("Medication not found");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Logger.error(throwable.getMessage());
                        if (view != null) view.showDataLoadError(throwable.getMessage());
                    }
                });
        disposables.add(disposable);
    }

    @Override
    public void detach() {
        if (view != null) view = null;
        disposables.clear();
    }
}
