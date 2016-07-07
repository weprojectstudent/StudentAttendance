package com.example.kittichot.studentattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateAlert extends ActionBarActivity {
    private String getIDAlert,getTitleAlert,getDetailAlert,getDATEsaveAlert,getDateAlert, getTEACHERAlert;
    private String strIDAlert,strTitleAlert,strDetailAlert,strDATEsaveAlert,strDateAlert, strTEACHERAlert;
    private int Yearx,monthx,dayx;
    private final int DILOG_ID = 0;
    private ImageButton btn;
    private EditText edtgetdate;
    private EditText edtTitle,edtDetail;
    private AlertTABLE objAlertTABLE;
    private DateThai objDateThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alert);
        final Calendar calendar = Calendar.getInstance();
        Yearx = calendar.get(Calendar.YEAR);
        monthx = calendar.get(Calendar.MONTH);
        dayx = calendar.get(Calendar.DAY_OF_MONTH);
        objAlertTABLE = new AlertTABLE(this);
        objDateThai = new DateThai();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setAllArray();
        bindwidget();
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void bindwidget() {
        edtgetdate = (EditText) findViewById(R.id.edtsetdatealertupdate);
        edtgetdate.setText(getDateAlert);
        edtTitle = (EditText) findViewById(R.id.edtTitleAlertUPdate);
        edtTitle.setText(getTitleAlert);
        edtDetail = (EditText) findViewById(R.id.edtDetailAlertupdate);
        edtDetail.setText(getDetailAlert);

    }

    private void setAllArray() {
        getIDAlert = getIntent().getExtras().getString("ID");
        getTitleAlert= getIntent().getExtras().getString("TITLE");
        getDetailAlert= getIntent().getExtras().getString("DETAIL");
        getDATEsaveAlert= getIntent().getExtras().getString("DATESAVE");
        getDateAlert= getIntent().getExtras().getString("DATEALERT");
        getTEACHERAlert=  getIntent().getExtras().getString("TEACHER");
    }

    public void clickupdateAlert(View view){
        strIDAlert = getIDAlert;
      strDateAlert =   edtgetdate.getText().toString();
       strTitleAlert= edtTitle.getText().toString();
       strDetailAlert=  edtDetail.getText().toString();
        strDATEsaveAlert = getDATEsaveAlert;
        strTEACHERAlert = getTEACHERAlert;
        if (strDateAlert.equals("")||strTitleAlert.equals("")||strDetailAlert.equals("")||strIDAlert.equals("")
                ||strDATEsaveAlert.equals("")||strTEACHERAlert.equals("")){
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(UpdateAlert.this, "กรอกไม่ครบทุกช่อง","กรุณกรอกให้ครบทุกช่อง");

        }else {

            objAlertTABLE.updateValueALERT(Integer.parseInt(strIDAlert), strTitleAlert, strDetailAlert, strDATEsaveAlert, strDateAlert, strTEACHERAlert, 0);
            onBackPressed();
        }

    }


    public void setdateAlertUpdate(View view) {
        btn = (ImageButton) findViewById(R.id.imgbtnAlertupdate);

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
            return new DatePickerDialog(this, dpickerListner, Yearx, monthx, dayx);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Yearx = year;
            monthx = 1+monthOfYear;
            dayx = dayOfMonth;
            String s = Yearx + "-" + "0" + monthx + "-" + dayx;
            edtgetdate.setText(objDateThai.dateThai(s));
            Toast.makeText(UpdateAlert.this, objDateThai.dateThai(s), Toast.LENGTH_SHORT).show();
        }
    };//dateset
}
