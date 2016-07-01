package com.example.kittichot.studentattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class Insert_Alert extends ActionBarActivity {
    private ImageButton btn;
    private int year_x,month_x, day_x;
    private final int DILOG_ID = 0;
    private EditText edtgetdate;
    private EditText edtTitle,edtDetail;
    private String strTitleAlert,strDetailAlert,strDatesaveAlert, strDatesentAlert,strUsernameTeacher;
    private int intStatusAlert;
    private AlertTABLE objAlertTABLE;
    private DateThai objDateThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__alert);
        objAlertTABLE = new AlertTABLE(this);
        objDateThai = new DateThai();
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        edtgetdate = (EditText) findViewById(R.id.edtsetdatealert);
        //objHomeworkTABLE = new HomeworkTABLE(this);
        Bindwidget();
    }

    private void Bindwidget() {
        edtTitle = (EditText) findViewById(R.id.edtTitleAlert);
        edtDetail = (EditText) findViewById(R.id.edtDetailAlert);
        strUsernameTeacher = getIntent().getExtras().getString("teacher");
    }

    public void setdateAlert(View view) {
        btn = (ImageButton) findViewById(R.id.imgbtnAlert);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = 1+monthOfYear;
            day_x = dayOfMonth;
            String s = year_x + "-" + "0" + month_x + "-" + day_x;
            edtgetdate.setText(objDateThai.dateThai(s));
            Toast.makeText(Insert_Alert.this,objDateThai.dateThai(s), Toast.LENGTH_SHORT).show();
        }
    };//dateset

    public void clickaddAlert(View view) {
        final Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = 1+calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        strTitleAlert = edtTitle.getText().toString();
        strDetailAlert = edtDetail.getText().toString();
        strDatesaveAlert = y + "-" +"0"+m+ "-" + d;
        strDatesentAlert = edtgetdate.getText().toString();
        intStatusAlert = 0;


        if (edtTitle.equals("") || edtDetail.equals("") || strDatesaveAlert.equals("") || strDatesentAlert.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(Insert_Alert.this, "กรอกไม่ครบทุกช่อง", "กรุณกรอกให้ครบทุกช่อง");
        } else {
            long l = objAlertTABLE.addValueALERT(strTitleAlert, strDetailAlert,objDateThai.dateThai(strDatesaveAlert), strDatesentAlert,strUsernameTeacher,intStatusAlert);
            onBackPressed();
        }
    }


}
