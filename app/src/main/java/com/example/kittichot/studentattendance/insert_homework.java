package com.example.kittichot.studentattendance;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;

public class insert_homework extends ActionBarActivity{
    private ImageButton btn;
    private int year_x,month_x, day_x;
    private final int DILOG_ID = 0;
    private EditText edtgetdate;
    private HomeworkTABLE objHomeworkTABLE;
    private EditText edtTitle,edtDetail;
    private String strTitleHW,strDetailHW,strDatesave, strDatesent;
    private int intStatus;
    private DateThai objDateThai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_homework);
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        edtgetdate = (EditText) findViewById(R.id.edtsetdate);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objDateThai = new DateThai();
        Bindwidget();
    }

    private void Bindwidget() {
        edtTitle = (EditText) findViewById(R.id.editText3);
        edtDetail = (EditText) findViewById(R.id.editText4);

    }

    public void setdate(View view) {
        btn = (ImageButton) findViewById(R.id.imageButton4);

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
            String s = year_x + "-" + month_x + "-" + day_x;
            String s1 = objDateThai.dateThai(s);
            edtgetdate.setText( s1 /*year_x+"-"+month_x+"-"+day_x*/);
            Toast.makeText(insert_homework.this, year_x + "/" +"0"+month_x + "/" + day_x, Toast.LENGTH_SHORT).show();
        }
    };

    public void ClickInsertHW(View view) {
        final Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = 1+calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        strTitleHW = edtTitle.getText().toString();
        strDetailHW = edtDetail.getText().toString();
        String s = y + "-" +m+ "-" + d;
        strDatesave=objDateThai.dateThai(s);
        strDatesent = edtgetdate.getText().toString();
        intStatus = 0;

        if (edtTitle.equals("") || edtDetail.equals("") || strDatesent.equals("") || strDatesave.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(insert_homework.this, "กรอกไม่ครบทุกช่อง", "กรุณกรอกให้ครบทุกช่อง");
        } else {
            long l = objHomeworkTABLE.addValueToHomework(strTitleHW, strDetailHW, strDatesave, strDatesent,intStatus);
            //upNewHW();
            onBackPressed();
        }

    }
    private void upNewHW() {
        //Setup policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }
        //up Value
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePairs.add(new BasicNameValuePair("HW_Title",strTitleHW));
            objNameValuePairs.add(new BasicNameValuePair("HW_Detail",strDetailHW));
            objNameValuePairs.add(new BasicNameValuePair("HW_Datesave",strDatesave ));
            objNameValuePairs.add(new BasicNameValuePair("HW_Datesent",strDatesent ));
            objNameValuePairs.add(new BasicNameValuePair("HW_Status", String.valueOf(intStatus)));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_data_homework.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            Toast.makeText(insert_homework.this,"บันทึก "+strTitleHW+" เสร็จสิ้น" ,Toast.LENGTH_SHORT).show();
            onBackPressed();
        } catch (Exception e) {
            Log.d("AddHW", "UPDATE MY SQL ==>" + e.toString());
        }
    }//upNewTerm
    }


