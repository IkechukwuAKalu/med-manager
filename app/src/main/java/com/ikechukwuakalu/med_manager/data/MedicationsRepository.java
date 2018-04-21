package com.ikechukwuakalu.med_manager.data;

import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.data.qualifiers.Local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Singleton
public class MedicationsRepository implements MedicationsDataSource {

    private MedicationsDataSource localMedDataSource;

    @Inject
    public MedicationsRepository(@Local MedicationsDataSource medDataSource) {
        localMedDataSource = medDataSource;
    }

    @Override
    public Completable add(Medication medication) {
        return localMedDataSource.add(medication);
    }

    @Override
    public Observable<Medication> getMedication(int id) {
        return localMedDataSource.getMedication(id);
    }

    @Override
    public Observable<List<Medication>> getMedications() {
        return localMedDataSource.getMedications();
    }

    @Override
    public Observable<List<Medication>> getMedicationsByName(String name) {
        return localMedDataSource.getMedicationsByName(name);
    }

    @Override
    public Observable<List<Medication>> getMedicationsByMonth(String month) {
        return localMedDataSource.getMedicationsByMonth(month);
    }

    @Override
    public Observable<List<Medication>> getMedicationsByInterval(String interval) {
        return localMedDataSource.getMedicationsByInterval(interval);
    }

    @Override
    public Observable<List<Medication>> getMedicationsByDescription(String description) {
        return localMedDataSource.getMedicationsByDescription(description);
    }

    @Override
    public Observable<Medication> edit(long id, Medication updatedMedication) {
        return localMedDataSource.edit(id, updatedMedication);
    }

    @Override
    public boolean remove(Medication medication) {
        return localMedDataSource.remove(medication);
    }
}