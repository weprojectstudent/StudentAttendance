package com.example.kittichot.studentattendance.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by klogi
 *
 * Broadcast receiver for: BOOT_COMPLETED, TIMEZONE_CHANGED, and TIME_SET events. Sets Alarm Manager for notification;
 */
public final class NotificationServiceStarterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String namesp = "setting";
        SharedPreferences sp = context.getSharedPreferences(namesp, Context.MODE_PRIVATE);
        if (Boolean.parseBoolean(sp.getString("syncro", ""))) {

            NotificationEventReceiver.setupAlarm(context);
            }
    }
}