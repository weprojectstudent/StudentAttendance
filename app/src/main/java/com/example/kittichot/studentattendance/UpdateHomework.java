package com.example.kittichot.studentattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class UpdateHomework extends ActionBarActivity {
    private HomeworkTABLE objHomeworkTABLE;
    private String strTitle,strDetail,strDatesave, strDatesent;
    private String getIstrIDHW,getIstrTitle,getIstrDetail,getIstrDatesave, getIstrDatesent;
    private int IntStatus,year_x,month_x, day_x,strIDHW;
    private EditText edtTitle,edtDetail, edtDatesent;
    private ImageButton imageButton;
    private final int DILOG_ID = 0;
    private DateThai objDateThai;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_update_homework);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objDateThai = new DateThai();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

       //BindWidget
        BindWidget();
        //setCalendar
        setCalendar();


    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void ClickupdateValueHomework(View view) {

        strTitle = edtTitle.getText().toString();
        strDetail = edtDetail.getText().toString().trim();
        strDatesave = objDateThai.setDateThai(getIstrDatesave) ;
        strDatesent = edtDatesent.getText().toString();
        if (strTitle.equals("") || strDetail.equals("") || strDatesent.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(UpdateHomework.this, "กรอกไม่ครบทุกช่อง", getIstrIDHW+"กรุณกรอกให้ครบทุกช่อง");
        } else {
            objHomeworkTABLE.updateValueToHomework(Integer.parseInt(getIstrIDHW), strTitle, strDetail, strDatesave, strDatesent, 0);
            finish();
            onBackPressed();
        }



    }

    private void setCalendar() {
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(calendar.YEAR);
        month_x = calendar.get(calendar.MONTH);
        day_x = calendar.get(calendar.DAY_OF_MONTH);
    }

    private void BindWidget() {
        edtTitle = (EditText) findViewById(R.id.edtTitleHWup);
        edtDetail = (EditText) findViewById(R.id.edtDetailHWup);
        edtDatesent = (EditText) findViewById(R.id.edtsetdate);
        imageButton = (ImageButton) findViewById(R.id.imageBtndatesentHWup);
        getIstrIDHW = getIntent().getExtras().getString("IDHW");
        getIstrTitle = getIntent().getExtras().getString("TitleHW");
        getIstrDetail = getIntent().getExtras().getString("DetailHW");
        getIstrDatesave = getIntent().getExtras().getString("DatesaveHW");
        getIstrDatesent = getIntent().getExtras().getString("DatesentHW");
        edtTitle.setText(getIstrTitle);
        edtDetail.setText(getIstrDetail);
        edtDatesent.setText(objDateThai.setDateThai(getIstrDatesent));
    }

    public void setdateup(View view) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID)
        return new DatePickerDialog(this,dpickerListner,year_x, month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = 1+monthOfYear;
            day_x = dayOfMonth;
            String s = year_x + "-" + "0" + month_x + "-" + day_x;
            String s1 = objDateThai.dateThai(s);
            edtDatesent.setText(s1);

        }
    };
}
