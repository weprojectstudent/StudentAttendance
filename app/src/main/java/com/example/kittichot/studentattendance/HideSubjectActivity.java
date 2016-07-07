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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class HideSubjectActivity extends ActionBarActivity {
    private String[] strListHideID, strListHideName;
    private TextView txtShowTescher;
    private ListView objHideList;
    private MyAdapter objMyAdapter;
    private SubjectTABLE objSubjectTABLE;
    private String strEditHideid, strEditHideName;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hide_subject);
        objSubjectTABLE = new SubjectTABLE(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //BindWidget
        BindWidget();
        //setAllArray
        setAllArray();
        //setListView
        setListView();
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void BindWidget() {
        txtShowTescher = (TextView) findViewById(R.id.textViewTeacherHide);
        objHideList = (ListView) findViewById(R.id.listViewHideSubject);
    }
    private void setAllArray() {
        strListHideID = objSubjectTABLE.listHideSubject();
        strListHideName = objSubjectTABLE.listHideSName();
    }
    private void setListView() {

        objMyAdapter= new MyAdapter(HideSubjectActivity.this,strListHideID,strListHideName);
        objHideList.setAdapter(objMyAdapter);
        objHideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strEditHideid = strListHideID[position];
                strEditHideName = strListHideName[position];
                ShowMenu();
            }
        });
    }
    private void ShowMenu() {
        CharSequence[] CharItem = {"ไม่ซ่อน", "ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        clickCancleHide();
                        dialog.dismiss();
                        break;
                    case 1:
                       dialog.dismiss();
                        break;
                }//switch
                //dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = builder.create();
        objAlertDialog.show();
    }//showmenu
    public void clickCancleHide() {

        objSubjectTABLE.updateValueToSubject(strEditHideid, strEditHideName, "0");

        upNewSubject();


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
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("subject_id", strEditHideid));
            objNameValuePairs.add(new BasicNameValuePair("subject_name", strEditHideName));
            objNameValuePairs.add(new BasicNameValuePair("subject_status", "1"));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_update_data_subject.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            Toast.makeText(HideSubjectActivity.this, "Update Subject" + strEditHideid, Toast.LENGTH_SHORT).show();
            onResume();
        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }   //newSubject
}
