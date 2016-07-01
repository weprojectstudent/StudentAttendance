package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by kittichot on 4/2/2016.
 */
public class MyAlertDialog {

    AlertDialog.Builder objAlert;
    public void errorDiaLog(Context context, String strTitle ,String strMessage) {
        objAlert = new AlertDialog.Builder(context);
        objAlert.setIcon(R.drawable.alerticon);
        objAlert.setTitle(strTitle);
        objAlert.setMessage(strMessage);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }//errorDialog
}//mainClass
