package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Insert_Subject extends ActionBarActivity {
    private String edtID, edtName;
    private EditText edtIDsub,edtNameSub;
    private String stredtID, stredtName;
    private SubjectTABLE objSubjectTABLE;


    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_insert__subject);
        objSubjectTABLE = new SubjectTABLE(this);
        //bindWidget
        bindWidget();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void bindWidget() {

        edtIDsub = (EditText) findViewById(R.id.editIDSubject);
        edtNameSub = (EditText) findViewById(R.id.editNameSubject);
    }

    public void onClickCheck(View view) {

            edtID = edtIDsub.getText().toString().trim();
            edtName = edtNameSub.getText().toString();
                //checkZero
                if (edtID.equals("")||edtName.equals("")) {
                    MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                    objMyAlertDialog.errorDiaLog(Insert_Subject.this,"กรอกไม่ครบทุกช่อง","กรุณากรอกข้อความให้ครบที่ช่อง");

                } else {
                    stredtID = edtID;
                    stredtName = edtName;
                    objSubjectTABLE.addValueToSubject(stredtID, stredtName, 0);
                    checkLog();
                    onBackPressed();
                    //up NewSubject
                    //upNewSubject();
                }
            }

    private void upNewSubject() {
        //Setup policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }
        //up Value
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePairs.add(new BasicNameValuePair("subject_id", stredtID));
            objNameValuePairs.add(new BasicNameValuePair("subject_name", stredtName));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_data_subject.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            onBackPressed();
            //Toast.makeText(Insert_Subject.this, "Update Subject" + stredtName, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
        alertInsert();
    }   //newSubject
    private void alertInsert() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.user);
        objAlert.setTitle("บันทัก");
        objAlert.setMessage("บันทึกข้อมูล " + stredtID + "\n" + "เข้าสู้ระบบเรียบร้อย");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                onBackPressed();

            }
        });
        objAlert.show();
    }//welcomeUser
    private void checkLog() {
        Log.d("AddSubject","ID ==>" +stredtID);
        Log.d("AddSubject","Name ==>" +stredtName);
    }
}


