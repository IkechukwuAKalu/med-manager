package com.ikechukwuakalu.med_manager.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ikechukwuakalu.med_manager.data.local.AppDatabase;
import com.ikechukwuakalu.med_manager.data.local.LocalMedicationsDataSource;
import com.ikechukwuakalu.med_manager.data.qualifiers.Local;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MedicationsRepositoryModule {

    @Singleton
    @Provides
    static AppDatabase appDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "medications-db")
                .build();
    }

    @Singleton
    @Provides
    @Local
    static MedicationsDataSource localMedDataSource(AppDatabase appDatabase) {
        return new LocalMedicationsDataSource(appDatabase);
    }
}
