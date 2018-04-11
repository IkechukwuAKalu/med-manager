package com.ikechukwuakalu.med_manager.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MedicationDao {

    @Insert
    void add(Medication medication);

    @Query("SELECT * FROM medication")
    Flowable<List<Medication>> getAll();

//    @Update
//    void edit(Medication oldMed, Medication newMed);

    @Delete
    void remove(Medication medication);
}
