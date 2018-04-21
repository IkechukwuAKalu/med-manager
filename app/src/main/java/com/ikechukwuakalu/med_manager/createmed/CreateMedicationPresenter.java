package com.ikechukwuakalu.med_manager.createmed;

import com.ikechukwuakalu.med_manager.data.MedicationsRepository;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.Logger;
import com.ikechukwuakalu.med_manager.utils.espresso.EspressoIdlingResource;
import com.ikechukwuakalu.med_manager.utils.rx.RxScheduler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@ActivityScoped
public class CreateMedicationPresenter implements CreateMedicationContract.Presenter {

    private CreateMedicationContract.View view;
    private MedicationsRepository repository;
    private RxScheduler rxScheduler;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    CreateMedicationPresenter(MedicationsRepository repository, RxScheduler rxScheduler) {
        this.repository = repository;
        this.rxScheduler = rxScheduler;
    }

    @Override
    public void attach(CreateMedicationContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        if (view != null) view = null;
        disposables.clear();
    }

    @Override
    public void saveMedication(Medication medication, boolean edit) {
        if (isObjectValid(medication)) {
            if (! areDatesValid(medication.getStartDate(), medication.getEndDate())) {
                if (view != null) view.showSaveFailure("Invalid dates. Start date cannot be greater than End date");
                return;
            }
            if (! isIntervalValid(medication.getInterval())) {
                if (view != null) view.showSaveFailure("Invalid interval. Example format is 09,00 for 9 hrs, 00min");
                return;
            }

            saveData(medication);
        } else {
            if (view != null) view.showSaveFailure("Please complete all the fields");
        }
    }

    private boolean isObjectValid(Medication medication) {
        return ! medication.getName().equals("") &&
                medication.getStartDate() != 0 &&
                medication.getEndDate() != 0 &&
                ! medication.getInterval().equals("") &&
                ! medication.getDescription().equals("");
    }

    private boolean areDatesValid(long start, long end) {
        return start <= end;
    }

    private boolean isIntervalValid(String interval) {
        String pattern = "^(\\d\\d)(|,\\d\\d)$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(interval);
        return matcher.find();
    }

    private void saveData(Medication medication) {
        EspressoIdlingResource.increment();
        Disposable disposable = repository.add(medication)
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.ui())
                .doFinally(new Action() {
                    @Override
                    public void run() {
                        if (! EspressoIdlingResource.getIdlingResource().isIdleNow()) EspressoIdlingResource.decrement();
                    }
                })
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        if (view != null) view.showSaveSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Logger.error(throwable.getMessage());
                        if (view != null) view.showSaveFailure(throwable.getMessage());
                    }
                });
        disposables.add(disposable);
    }
}
