package com.ikechukwuakalu.med_manager.data;

import com.ikechukwuakalu.med_manager.data.local.Medication;

import java.util.List;

import io.reactivex.Observable;

public interface MedicationsDataSource {

    /**
     * Creates a new Medication entry
     * @param medication the Medication Object
     * @return true if it was successful, else false
     */
    boolean add(Medication medication);

    /**
     * Fetches Medications by ID
     * @param id the Medication id
     * @return the Medication Object if found, else null
     */
    Observable<Medication> getMedication(long id);

    /**
     * Fetches all Medications
     * @return a List of Medications if found, else an empty list
     */
    Observable<List<Medication>> getMedications();

    /**
     * Fetches Medications by Name
     * @param name the Medication name
     * @return a List of Medications if found, else an empty list
     */
    Observable<List<Medication>> getMedicationsByName(String name);

    /**
     * Fetches Medications by Month
     * @param month the Medication month
     * @return a List of Medications if found, else an empty list
     */
    Observable<List<Medication>> getMedicationsByMonth(String month);

    /**
     * Fetches Medications by Interval of in-take
     * @param interval the Medication interval
     * @return a List of Medications if found, else an empty list
     */
    Observable<List<Medication>> getMedicationsByInterval(String interval);

    /**
     * Fetches Medications by Description
     * @param description the Medication Description
     * @return a List of Medications if found, else an empty list
     */
    Observable<List<Medication>> getMedicationsByDescription(String description);

    /**
     * Modifies the content of a Medication entry
     * @param id the Medication ID
     * @param updatedMedication the new Medication Object
     * @return the updated Medication Object
     */
    Observable<Medication> edit(long id, Medication updatedMedication);

    /**
     * Removes a Medication entry
     * @param medication the Medication Object. Passing null removes EVERY entry
     * @return true if removal was successful, else false
     */
    boolean remove(Medication medication);
}