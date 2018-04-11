package com.ikechukwuakalu.med_manager.medications;

import android.app.Activity;

import com.ikechukwuakalu.med_manager.base.BasePresenter;
import com.ikechukwuakalu.med_manager.base.BaseView;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.data.models.User;

import java.util.List;

public interface MedicationsContract {
// TODO make progress views
    interface View extends BaseView<Presenter> {

        void showSignOutSuccess();

        void showSignOutFailure(String message);

        void showAuthScreen();

        void showUserDetails(User user);

        void showNoMedicationFound();

        void showMedications(List<Medication> medications);
    }

    interface Presenter extends BasePresenter<View> {

        void signOutUser(Activity activity);

        void fetchMedications();
    }
}
