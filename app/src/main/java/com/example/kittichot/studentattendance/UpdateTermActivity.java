package com.example.kittichot.studentattendance;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class UpdateTermActivity extends ActionBarActivity {
    private SubjectTABLE objSubjectTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private String[] stredtID,strName;
    private EditText strIDTerm,strTermYear;
    private String getstrSpinner,getstrtxtIDTerm,getstrTermYear,getstrUsernameTeacher,getStatus;
    private Spinner spinTerm;
    private String putExtraIdSubject, putExtraUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_term);
        objSubjectTABLE = new SubjectTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        //BindWidget
        BindWidget();
        //getIntent
        getIntentHide();
        //setAllArray
        setAllArray();
        //updateValue
        
    }

    public void updateValue(View view) {
        getstrtxtIDTerm = strIDTerm.getText().toString().trim();
        getstrTermYear = strTermYear.getText().toString().trim();
        getstrUsernameTeacher = putExtraUser;
        getStatus = "0";

        if (getstrtxtIDTerm.equals("") || getstrTermYear.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(UpdateTermActivity.this, "กรอกไม่ครบทุกช่อง", "รหัสเทอม = " + getstrtxtIDTerm + "Username = "
                    + getstrUsernameTeacher + "รหัสรายวิชา = " + getstrSpinner + "ปีการศึกษา" + getstrTermYear);
        } else {
            objTeachdetailTABLE.updateValueToTEACHDETAIL(getstrtxtIDTerm, getstrUsernameTeacher, getstrSpinner, getstrTermYear, 0);
           onBackPressed();
            //updateValuetoSQL();
        }
    }

 /*   private void updateValuetoSQL() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }
        //up Value
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePairs.add(new BasicNameValuePair("term_id",getstrtxtIDTerm ));
            objNameValuePairs.add(new BasicNameValuePair("teacher_user",getstrUsernameTeacher));
            objNameValuePairs.add(new BasicNameValuePair("subject_name",getstrSpinner ));
            objNameValuePairs.add(new BasicNameValuePair("term_year",getstrTermYear ));
            objNameValuePairs.add(new BasicNameValuePair("status",getStatus ));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_update_data_term.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            //Toast.makeText(EditSubject.this, "Update Subject" + strID, Toast.LENGTH_SHORT).show();
            onBackPressed();
        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }*/   //updateValuetoSQL

    private void setAllArray() {
        stredtID = objSubjectTABLE.listSubject();
        final ArrayList<String> spinnerArray = new ArrayList<String>();
        final ArrayList<String> spinnerArrayID = new ArrayList<String>();
        for (int i = 0; i < stredtID.length; i++) {
            strName = objSubjectTABLE.listName(stredtID[i]);
            spinnerArray.add(stredtID[i]+" "+strName[0]);
            spinnerArrayID.add(stredtID[i]);
        }//for
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,spinnerArray);
        spinTerm.setAdapter(adapterSpinner);
        spinTerm.setSelection(adapterSpinner.getPosition(putExtraIdSubject));
        spinTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getstrSpinner = spinnerArrayID.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        
    }

    private void BindWidget() {
        strIDTerm = (EditText) findViewById(R.id.editTxtUpIDTerm);
        strTermYear = (EditText) findViewById(R.id.editTxtYearUp);
        spinTerm = (Spinner) findViewById(R.id.spinnerUp);

    }

    private void getIntentHide() {
        String strgetIDterm = getIntent().getExtras().getString("IDTerm");
        String strgetSubject = getIntent().getExtras().getString("IDSubject");
        String strgetTerm = getIntent().getExtras().getString("TermYear");
        String strIDteacher = getIntent().getExtras().getString("IDTeacher");
        strIDTerm.setText(strgetIDterm);
        strTermYear.setText(strgetTerm);
        putExtraIdSubject = strgetSubject;
        putExtraUser = strIDteacher;
        //Toast.makeText(UpdateTermActivity.this, "Update Subject" + putExtraIdSubject, Toast.LENGTH_SHORT).show();

    }


}
