package com.example.kittichot.studentattendance;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kittichot.studentattendance.broadcast_receivers.NotificationEventReceiver;
import com.example.kittichot.studentattendance.broadcast_receivers_alert.NotificationEventReceiverAlert;

import java.util.Calendar;

public class setting extends ActionBarActivity {
    private Switch Switchsyncro, Switchalert;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String namesp = "setting";
    private TextView txt71, txt72;
    private ImageButton imageButton;
    private DateThai dateThai;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Switchsyncro = (Switch) findViewById(R.id.switch1);
        Switchalert = (Switch) findViewById(R.id.switch2);
        sp = getSharedPreferences(namesp, Context.MODE_PRIVATE);
        dateThai = new DateThai();
        setSwitch();
        setSharedPreferences();
        settingswitchsyncro();
        settingswitchalert();
        txt71 = (TextView) findViewById(R.id.textView71);
        txt71.setText(dateThai.timesetAlert(sp.getString("Hour", "")));
        txt72 = (TextView) findViewById(R.id.textView72);
        txt72.setText(dateThai.timesetMinuteAlert(sp.getString("minute","")));
        imageButton = (ImageButton) findViewById(R.id.imageButton5);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(imageButton);
            }
        });
        //txt71.setText(sp.getInt("Hour",Integer.valueOf("")));
       // txt72.setText(sp.getInt("minute",Integer.valueOf("")));

    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        int callCount = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int h = hourOfDay;
            int m = minute;

            editor.putString( "Hour", String.valueOf(h));
            //txt72.setText(m);
            editor.putString( "minute", String.valueOf(m));
            editor.putString("alert","true");
            editor.apply();
            txt71.setText(dateThai.timesetAlert(sp.getString("Hour", "")));
            txt72.setText(dateThai.timesetMinuteAlert(sp.getString("minute","")));
            Switchalert.setChecked(Boolean.parseBoolean(sp.getString("alert","")));
            NotificationEventReceiverAlert.setupAlarmalert(getApplicationContext());
        }
    }

    private void setSharedPreferences() {
        editor = sp.edit();
        editor.putString("alert","true");
        editor.apply();
    }

    private void setSwitch() {
        Switchsyncro.setChecked(Boolean.parseBoolean(sp.getString("syncro","")));
        if (Switchsyncro.isChecked()) {
            Switchsyncro.setText("เปิด");
        } else {
            Switchsyncro.setText("ปิด");
        }
        Switchalert.setChecked(Boolean.parseBoolean(sp.getString("alert","")));
        if (Switchalert.isChecked()) {
            Switchalert.setText("เปิด");
        } else {
            Switchalert.setText("ปิด");
        }
    }


    private void settingswitchsyncro() {
        Switchsyncro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switchsyncro.isChecked()) {
                    editor = sp.edit();
                    editor.putString("syncro","true");
                    editor.apply();
                    Switchsyncro.setText("เปิด");
                    NotificationEventReceiver.setupAlarm(getApplicationContext());
                } else {
                    editor = sp.edit();
                    editor.putString("syncro","false");
                    editor.apply();
                    Switchsyncro.setText("ปิด");
                    NotificationEventReceiver.cancelAlarm(getApplicationContext());
                }


            }
        });
    }

    private void settingswitchalert() {
        Switchalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switchalert.isChecked()) {
                    editor = sp.edit();
                    editor.putString("alert","true");
                    editor.apply();
                    Switchalert.setText("เปิด");
                    NotificationEventReceiverAlert.setupAlarmalert(getApplicationContext());
                } else {
                    editor = sp.edit();
                    editor.putString("alert","false");
                    editor.apply();
                    Switchalert.setText("ปิด");
                    NotificationEventReceiverAlert.cancelAlarmalert(getApplicationContext());
                }
            }
        });

    }


}
