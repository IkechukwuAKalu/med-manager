package com.ikechukwuakalu.med_manager.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

@Singleton
@Database(entities = { Medication.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();
}
