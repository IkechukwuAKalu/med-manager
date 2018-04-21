package com.ikechukwuakalu.med_manager.createmed;

import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;
import com.ikechukwuakalu.med_manager.data.local.Medication;

public interface CreateMedicationContract {

    interface View extends BaseView<Presenter> {

        void populateFields(Medication medication);

        void showSaveSuccess();

        void showSaveFailure(String message);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Saves a new medication or edits an existing one
         * @param medication the Medication Object
         * @param edit true if this operation should be an update and not a create
         */
        void saveMedication(Medication medication, boolean edit);
    }
}
