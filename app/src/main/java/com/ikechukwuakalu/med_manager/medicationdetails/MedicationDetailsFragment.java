package com.ikechukwuakalu.med_manager.medicationdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;
import com.ikechukwuakalu.med_manager.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScoped
public class MedicationDetailsFragment extends BaseFragment implements MedicationDetailsContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.med_detail_name) TextView name;
    @BindView(R.id.med_detail_complete) TextView complete;
    @BindView(R.id.med_details_start) TextView start;
    @BindView(R.id.med_details_end) TextView end;
    @BindView(R.id.med_details_interval) TextView interval;
    @BindView(R.id.med_details_next) TextView next;
    @BindView(R.id.med_details_desc) TextView description;

    @Inject MedicationDetailsContract.Presenter presenter;

    @Inject
    public MedicationDetailsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_med_details, container, false);
        ButterKnife.bind(this, view);
        setUpToolbar(toolbar);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    public void showDetails(Medication medication) {
        name.setText(medication.getName());
        complete.setText(calculateCompletePercent(medication.getStartDate(), medication.getEndDate()));
        start.setText(getReadableDate(medication.getStartDate()));
        end.setText(getReadableDate(medication.getEndDate()));
        interval.setText(medication.getInterval());
        next.setText("8:00");
        description.setText(medication.getDescription());
    }

    private String calculateCompletePercent(long start, long end) {
        Date date = Calendar.getInstance().getTime();
        String formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US)
                .format(date);
        String[] sArgs = formatter.split("/");
        Date sDate = new Date(Integer.valueOf(sArgs[2]), Integer.valueOf(sArgs[1]), Integer.valueOf(sArgs[0]));
        long currentDate = sDate.getTime();
        long diff = end - start;
        long result = (currentDate / end) * 100;
        Logger.debug(currentDate + " ++ " + end);
        return String.valueOf(result);
    }

    private String getReadableDate(long date) {
        Date date1 = new Date(date);
        return new SimpleDateFormat("dd/MM/yyyy", Locale.US)
                .format(date1);
    }

    @Override
    public void showDataLoadError(String message) {
        showLongToast(getContext(), message);
    }
}