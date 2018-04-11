package com.ikechukwuakalu.med_manager.di;

import android.app.Application;

import com.ikechukwuakalu.med_manager.BaseApplication;
import com.ikechukwuakalu.med_manager.data.MedicationsRepository;
import com.ikechukwuakalu.med_manager.data.MedicationsRepositoryModule;
import com.ikechukwuakalu.med_manager.di.modules.ActivityBindingModule;
import com.ikechukwuakalu.med_manager.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = { ActivityBindingModule.class,
        MedicationsRepositoryModule.class,
        ApplicationModule.class,
        AndroidSupportInjectionModule.class })
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication baseApplication);

    MedicationsRepository medicationsRepository();

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder applicationBuilder(Application application);

        AppComponent build();
    }
}
