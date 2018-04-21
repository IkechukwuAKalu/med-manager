package com.ikechukwuakalu.med_manager.medicationdetails;

import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;
import com.ikechukwuakalu.med_manager.data.local.Medication;

public interface MedicationDetailsContract {

    interface View extends BaseView<Presenter> {

        void showDetails(Medication medication);

        void showDataLoadError(String message);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
