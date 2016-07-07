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

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class HideTermActivity extends ActionBarActivity {
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private String[] strTermID, strsubjectID,strTermYear,NameSuject;
    private String putExtraName, putExtraUser;
    private TextView textTeacher;
    private ListView objListView;
    private String strgetID, strgetSubject,strgetTerm,strIDteacher,strStatus;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hide_term);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //setAllArray
        setAllArray();
        //bindWidget
        bindWidget();
        //CreateListView
        CreateListView();
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == android.R.id.home) {
                onBackPressed();
            }
            return super.onOptionsItemSelected(item);
        }

    private void setAllArray() {
        String strTextShowUser = getIntent().getExtras().getString("IDTeacher");
        String strYear = getIntent().getExtras().getString("Year");
        strTermID = objTeachdetailTABLE.listTermIDHide(strTextShowUser,strYear);
        strsubjectID = objTeachdetailTABLE.listSubjectIDHide(strTextShowUser,strYear);
        strTermYear = objTeachdetailTABLE.listTermyearHide(strTextShowUser,strYear);
    }
    private void bindWidget() {
        String strTextShowUser = getIntent().getExtras().getString("IDTeacher");
        strIDteacher = strTextShowUser;
        textTeacher = (TextView) findViewById(R.id.txtVteacherhide);
        objListView = (ListView) findViewById(R.id.listViewShowTermhide);


    }
    private void CreateListView() {
        final String[] getNameSubject = new String[strsubjectID.length];
        for (int i = 0; i < strsubjectID.length; i++) {
            NameSuject = objSubjectTABLE.listName(strsubjectID[i]);
            for (int i1 = 0; i1 < NameSuject.length; i1++) {
                getNameSubject[i] = NameSuject[i1];
            }
        }
        MyAdapterTerm myAdapterTerm = new MyAdapterTerm(HideTermActivity.this, strTermID, getNameSubject,strTermYear);
        objListView.setAdapter(myAdapterTerm);

        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strgetID = strTermID[position];
                strgetSubject = strsubjectID[position];
                strgetTerm = strTermYear[position];
                strStatus = "0";
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
                        clickcancelHide();
                        onResume();
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
    }
    public void clickcancelHide() {

        objTeachdetailTABLE.updateValueToTEACHDETAIL(strgetID,strIDteacher,strgetSubject,strgetTerm,0);

        //upNewSubject();
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
            objNameValuePairs.add(new BasicNameValuePair("term_id",strgetID ));
            objNameValuePairs.add(new BasicNameValuePair("teacher_user",strIDteacher));
            objNameValuePairs.add(new BasicNameValuePair("subject_name",strgetSubject ));
            objNameValuePairs.add(new BasicNameValuePair("term_year",strgetTerm ));
            objNameValuePairs.add(new BasicNameValuePair("status",strStatus ));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_update_data_term.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            onResume();
            // Toast.makeText(EditSubject.this, "Update Subject" + strID, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }
}
