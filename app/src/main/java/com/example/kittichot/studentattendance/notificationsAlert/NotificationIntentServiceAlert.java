package com.example.kittichot.studentattendance.notificationsAlert;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.kittichot.studentattendance.AlertTABLE;
import com.example.kittichot.studentattendance.DateThai;
import com.example.kittichot.studentattendance.HomeworkTABLE;
import com.example.kittichot.studentattendance.MainActivity;
import com.example.kittichot.studentattendance.R;
import com.example.kittichot.studentattendance.broadcast_receivers_alert.NotificationEventReceiverAlert;

import java.util.Calendar;

/**
 * Created by klogi
 *
 *
 */
public class NotificationIntentServiceAlert extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";
    private AlertTABLE alertTABLE;
    private HomeworkTABLE homeworkTABLE;
    private String getstr;
    private String[] strDateAlert,strDatesent;
    private int d, m, y,b;
    private DateThai objDateThai;

    public NotificationIntentServiceAlert() {
        super(NotificationIntentServiceAlert.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentServiceAlert.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentServiceAlert.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?
        alertTABLE = new AlertTABLE(this);
        homeworkTABLE = new HomeworkTABLE(this);
        objDateThai = new DateThai();
        Calendar calendar = Calendar.getInstance();
        d = calendar.get(Calendar.DAY_OF_MONTH);
        m = 1+calendar.get(Calendar.MONTH);
        y = calendar.get(Calendar.YEAR);
        getstr = y + "-" + m + "-"+d;
        strDateAlert = alertTABLE.listDatealertAlert(objDateThai.dateThaiSelect(getstr));
        strDatesent = homeworkTABLE.listDatesentHW(objDateThai.dateThaiSelect(getstr));
        if (strDateAlert.length == 1 && strDatesent.length == 0) {
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("แจ้งเตือน")
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setContentText("มีบันทึกของวันนี้")
                    .setSmallIcon(R.drawable.logo2);

            Intent mainIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    NOTIFICATION_ID,
                    mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(NotificationEventReceiverAlert.getDeleteIntent(this));

            final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, builder.build());
        } else if (strDateAlert.length == 0 && strDatesent.length == 1) {
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("แจ้งเตือน")
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setContentText("มีการบ้านที่มีกำหนดส่งวันนี้")
                    .setSmallIcon(R.drawable.logo2);

            Intent mainIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    NOTIFICATION_ID,
                    mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(NotificationEventReceiverAlert.getDeleteIntent(this));

            final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, builder.build());
        } else if (strDateAlert.length == 1 && strDateAlert.length == 1) {
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("แจ้งเตือน")
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setContentText("มีการบ้านที่มีกำหนดส่งและบันทึกของวันนี้")
                    .setSmallIcon(R.drawable.logo2);

            Intent mainIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    NOTIFICATION_ID,
                    mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(NotificationEventReceiverAlert.getDeleteIntent(this));

            final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, builder.build());

        } else {



        }

        }

    }
