package com.ikechukwuakalu.med_manager.data.local;

import com.ikechukwuakalu.med_manager.data.MedicationsDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

@Singleton
public class LocalMedicationsDataSource implements MedicationsDataSource {

    private MedicationDao medicationDao;

    @Inject
    public LocalMedicationsDataSource(AppDatabase appDatabase) {
        medicationDao = appDatabase.medicationDao();
    }

    @Override
    public Completable add(final Medication medication) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                medicationDao.add(medication);
            }
        });
    }

    @Override
    public Observable<Medication> getMedication(int id) {
        return medicationDao.getById(id)
                .toObservable();
    }

    @Override
    public Observable<List<Medication>> getMedications() {
        return medicationDao.getAll()
                .toObservable();
    }

    @Override
    public Observable<List<Medication>> getMedicationsByName(String name) {
        return null;
    }

    @Override
    public Observable<List<Medication>> getMedicationsByMonth(String month) {
        return null;
    }

    @Override
    public Observable<List<Medication>> getMedicationsByInterval(String interval) {
        return null;
    }

    @Override
    public Observable<List<Medication>> getMedicationsByDescription(String description) {
        return null;
    }

    @Override
    public Observable<Medication> edit(long id, Medication updatedMedication) {
       return null;
    }

    @Override
    public boolean remove(Medication medication) {
        medicationDao.remove(medication);
        return true;
    }
}
