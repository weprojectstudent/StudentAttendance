package com.example.kittichot.studentattendance;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class insert_term extends ActionBarActivity {

    private SubjectTABLE objSubjectTABLE;
    private TeachdetailTABLE objTeachdetailTABLETABLE;
    private String[] stredtID,strName;
    private String strSpinner,strSpinnerID;
    private EditText strIDTerm,strTermYear;
    private String getstrSpinner,getstrtxtIDTerm,getstrTermYear,getstrUsernameTeacher;
    private Spinner spinTerm;
    private String putExtraName, putExtraUser;


    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_insert_term);
        objSubjectTABLE = new SubjectTABLE(this);
        objTeachdetailTABLETABLE = new TeachdetailTABLE(this);
        //synJsonTOsubjectNEW();

        stredtID = objSubjectTABLE.listSubject();

        spinTerm = (Spinner) findViewById(R.id.spinnerinsert);
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
        //BindWidget
        BindWidget();
        spinTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(insert_term.this, "Select : " + spinnerArray.get(position), Toast.LENGTH_SHORT).show();
                strSpinner = spinnerArray.get(position);
                strSpinnerID= spinnerArrayID.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }//onCreate


    private void BindWidget() {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        strIDTerm = (EditText) findViewById(R.id.editTxtIDTerm);
        strIDTerm.setText(strTextShowUser);
        strTermYear = (EditText) findViewById(R.id.editTxtYear);
    }

    public void clickAddValueTerm(View view) {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraName = strTextShow;
        putExtraUser = strTextShowUser;
        //getstrtxtIDTerm = strIDTerm.getText().toString().trim();
        getstrSpinner = strSpinnerID;
        getstrTermYear = strTermYear.getText().toString().trim();
        getstrUsernameTeacher =putExtraUser;
        if ( getstrSpinner.equals("") || getstrUsernameTeacher.equals("") || getstrTermYear.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDiaLog(insert_term.this, "กรอกไม่ครบทุกช่อง","Username = "
                    + getstrUsernameTeacher + "รหัสรายวิชา = " + getstrSpinner + "ปีการศึกษา" + getstrTermYear);
        } else {
            objTeachdetailTABLETABLE.addValueToTEACHDETAIL(getstrUsernameTeacher,getstrSpinner,getstrTermYear,0);
            checklog();
            onBackPressed();
            //upNewTerm();
        }

    }

    private void upNewTerm() {
        //Setup policy
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

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_data_term.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            Toast.makeText(insert_term.this,"Update Subject" +getstrtxtIDTerm ,Toast.LENGTH_SHORT).show();
            onBackPressed();
        } catch (Exception e) {
            Log.d("AddTerm", "UPDATE MY SQL ==>" + e.toString());
        }
    }//upNewTerm

    private void checklog() {
        Log.d("AddTerm","IDTerm ==>"+getstrtxtIDTerm);
        Log.d("AddTerm","UsernameTeacher ==>" +getstrUsernameTeacher);
        Log.d("AddTerm","Subject_ID ==>" +getstrSpinner);
        Log.d("AddTerm","TermYear ==>" +getstrTermYear);
    }

}
