package com.example.kittichot.studentattendance;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.Calendar;


public class Menu extends ActionBarActivity implements DialogInterface.OnClickListener {
    private String putExtraNAme, putExtraUSer;
    private HomeworkTABLE objHomeworkTABLE;
    private int d, m, y;
    private String strdate,strnum[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        objHomeworkTABLE = new HomeworkTABLE(this);
        setArray();

    }//onCreate

    private void setArray() {
        Calendar calendar = Calendar.getInstance();
        d = calendar.get(Calendar.DAY_OF_MONTH);
        m = calendar.get(Calendar.MONTH);
        y = calendar.get(Calendar.YEAR);
        strdate = y + "-0" + m + "-" + d;
        strnum=objHomeworkTABLE.listDatesentHW(strdate);
        MyAlertDialog myAlertDialog = new MyAlertDialog();
        myAlertDialog.errorDiaLog(this,strdate,strdate);
        if (strnum.length == 1) {
            setService();
        }
    }

    private void setService() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification(android.R.drawable.btn_star_big_on,
                "New notification", System.currentTimeMillis());

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        String Title = "New mesage!";
        String Message = "This is the your braking time.";

        Intent intent = new Intent(this, Homework_manament.class);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, Title, Message, activity);
        notification.number += 1;
        notification.defaults = Notification.DEFAULT_SOUND; // Sound
        notification.defaults = Notification.DEFAULT_VIBRATE; // Vibrate

        notificationManager.notify(11, notification);

    }


    public void onClick(View v) {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraNAme = strTextShow;
        putExtraUSer = strTextShowUser;
        Intent intent = new Intent(Menu.this, AddSubjectActivity.class);
        intent.putExtra("Name", putExtraNAme);
        intent.putExtra("Username", putExtraUSer);
        startActivity(intent);

    }

    public void onclickQRCode(View view) {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraNAme = strTextShow;
        putExtraUSer = strTextShowUser;
        Intent intent2 = new Intent(Menu.this, QrcodeActivity.class);
        intent2.putExtra("Name", putExtraNAme);
        intent2.putExtra("Username", putExtraUSer);
        startActivity(intent2);

    }

    public void AddTerm(View view) {

    }

    public void AddHomeWork(View view) {

    }


    public void toselectedStudents(View view) {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraNAme = strTextShow;
        putExtraUSer = strTextShowUser;
        Intent intent4 = new Intent(Menu.this, MenuActivity.class);
        intent4.putExtra("Name", putExtraNAme);
        intent4.putExtra("Username", putExtraUSer);
        startActivity(intent4);

    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}//mainclass
