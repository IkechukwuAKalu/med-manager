package com.ikechukwuakalu.med_manager.createmed;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.medications.MedicationsActivity;
import com.ikechukwuakalu.med_manager.utils.Logger;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.debug(intent.getStringExtra(CreateMedicationFragment.MEDICATION_DATA_LABEL));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Medication Reminder")
                .setContentText("Come take your medication");
        Intent intentToTrigger = new Intent(context, MedicationsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentToTrigger, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat.from(context)
                .notify((int) System.currentTimeMillis(), builder.build());
    }
}
