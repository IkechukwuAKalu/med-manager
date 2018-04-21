package com.ikechukwuakalu.med_manager.createmed;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseFragment;
import com.ikechukwuakalu.med_manager.data.local.Medication;
import com.ikechukwuakalu.med_manager.di.scopes.ActivityScoped;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ActivityScoped
public class CreateMedicationFragment extends BaseFragment implements CreateMedicationContract.View {

    public static final String MEDICATION_DATA_LABEL = "medication_data_label";

    private final Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.US);

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.med_creator_name) EditText name;
    @BindView(R.id.med_creator_start) EditText start;
    @BindView(R.id.med_creator_end) EditText end;
    @BindView(R.id.med_creator_interval) EditText interval;
    @BindView(R.id.med_creator_desc) EditText description;

    @Inject CreateMedicationContract.Presenter presenter;

    @Inject
    public CreateMedicationFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_med, container, false);
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
    public void populateFields(Medication medication) {
        name.setText(medication.getName());
        start.setText(String.valueOf(medication.getStartDate()));
        end.setText(String.valueOf(medication.getEndDate()));
        interval.setText(medication.getInterval());
        description.setText(medication.getDescription());
    }

    @Override
    public void showSaveSuccess() {
        showShortToast(getContext(), "Medication Saved");
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void showSaveFailure(String message) {
        showLongToast(getContext(), message);
    }

    @OnClick(R.id.med_creator_start)
    public void setStartDate() {
        Context context = getContext();
        if (context != null) {
            new DatePickerDialog(context,
                    getDateListener(),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        }
        start.setText(dateFormat.format(calendar.getTime()));
    }

    private DatePickerDialog.OnDateSetListener getDateListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
    }

    @OnClick(R.id.med_creator_end)
    public void setEndDate() {
        Context context = getContext();
        if (context != null) {
            new DatePickerDialog(context,
                    getDateListener(),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        }
        end.setText(dateFormat.format(calendar.getTime()));
    }

    @OnClick(R.id.med_creator_save)
    public void saveMedication() {
        if (start.getText().toString().contains("/") && end.getText().toString().contains("/")) {
            String[] sArgs = start.getText().toString().split("/");
            Date sDate = new Date(Integer.valueOf(sArgs[2]), Integer.valueOf(sArgs[1]), Integer.valueOf(sArgs[0]));
            String startDate = String.valueOf(sDate.getTime());

            String[] eArgs = end.getText().toString().split("/");
            Date eDate = new Date(Integer.valueOf(eArgs[2]), Integer.valueOf(eArgs[1]), Integer.valueOf(eArgs[0]));
            String endDate = String.valueOf(eDate.getTime());

            Medication medication = new Medication(name.getText().toString(),
                    Long.valueOf(startDate),
                    Long.valueOf(endDate),
                    interval.getText().toString(),
                    description.getText().toString());
            presenter.saveMedication(medication, false);
            setAlarm(medication);
        } else {
            showLongToast(getContext(), "Start and End dates cannot be empty");
        }
    }

    private void setAlarm(Medication medication) {
        Intent intent = new Intent(getContext(), ReminderBroadcastReceiver.class);
        intent.putExtra(MEDICATION_DATA_LABEL, medication.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Activity activity = getActivity();
        if (activity != null) {
            AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Activity.ALARM_SERVICE);
            if (alarmManager != null) {
                long timeMs = 100;
                if (Build.VERSION.SDK_INT < 19) alarmManager.set(AlarmManager.RTC_WAKEUP, timeMs, pendingIntent);
                else alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeMs, pendingIntent);
            }
        }
    }
}
