package com.example.kittichot.studentattendance;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class EditSubject extends ActionBarActivity {
    private String getExtraID, getExtraName;
    //private SQLiteDatabase readSQLlite, writeSQLite;
   // private MyOpenHelper objMyOpenHelper;
    private EditText edtID, edtName;
    private String strID, strName,strStatus;
    private SubjectTABLE objSubjectTABLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);
        String strTextShowID = getIntent().getExtras().getString("IDSubject");
        String strTextShowName = getIntent().getExtras().getString("NameSubject");
        getExtraID = strTextShowID;
        getExtraName = strTextShowName;
        objSubjectTABLE = new SubjectTABLE(this);
        bindwidget();

    }

    private void bindwidget() {
        edtID = (EditText) findViewById(R.id.editIDSubject);
        edtName = (EditText) findViewById(R.id.editNameSubject);
        edtID.setText(getExtraID);
        //edtID.getText().toString();
        edtName.setText(getExtraName);
        //edtName.getText().toString();
    }//bindwidget

    public void clickupdate(View view) {
        strID = edtID.getText().toString();
        strName = edtName.getText().toString();
        strStatus = "0";
        checklog();
        //upNewSubject();
        objSubjectTABLE.updateValueToSubject(strID, strName, strStatus);
        onBackPressed();


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
            objNameValuePairs.add(new BasicNameValuePair("subject_id",strID ));
            objNameValuePairs.add(new BasicNameValuePair("subject_name", strName));
            objNameValuePairs.add(new BasicNameValuePair("subject_status",strStatus));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_update_data_subject.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
           // Toast.makeText(EditSubject.this, "Update Subject" + strID, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }   //newSubject

    private void checklog() {
        Log.d("editSubject", "ID ==>" +strID);
        Log.d("editSubject", "Name ==>" + strName);

    }

}
