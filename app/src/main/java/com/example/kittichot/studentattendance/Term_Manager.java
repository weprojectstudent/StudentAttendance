package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Term_Manager extends ActionBarActivity {
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private String[] strTermID, strsubjectID,strTermYear,strtermyearmenage,NameSuject;
    private String putExtraName, putExtraUser;
    private TextView textTeacher;
    private ListView objListView;
    private Spinner objSpinner;
    private String strgetID, strgetSubject,strgetTerm,strIDteacher,strStatus,strgettermyearmenage;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_term__manager);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        //sysJsonToTerm
        //synJsonToTerm();
        //testAddValue
        //test();
        //bindWidget
        bindWidget();
        //set Show teacher user
        setShowteacherUser();
        //setup All Array
        setAllArray();
        //create list view
        CreateSpinner();
        //CreateListView();

    }

    private void CreateSpinner() {
        strtermyearmenage = objTeachdetailTABLE.listTERMYearselected(strIDteacher);
        final ArrayList<String> arrayList = new ArrayList<String>();
        for (int i=0;i<strtermyearmenage.length;i++) {
            arrayList.add(strtermyearmenage[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,arrayList);
        objSpinner.setAdapter(arrayAdapter);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgettermyearmenage = arrayList.get(position);
                CreateListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void clickinsertTerm(View view) {

       String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraName = strTextShow;
        putExtraUser = strTextShowUser;
        Intent intent = new Intent(Term_Manager.this,insert_term.class);
        intent.putExtra("Name", putExtraName);
        intent.putExtra("Username", putExtraUser);
        startActivity(intent);
       /* MyAlertDialog myAlertDialog = new MyAlertDialog();
        for (int i = 0; i < strtermyearmenage.length; i++) {
            myAlertDialog.errorDiaLog(this, strtermyearmenage[i], strtermyearmenage[i]);
        }*/

    }


    private void CreateListView() {

        strTermID = objTeachdetailTABLE.listTermID(strIDteacher,strgettermyearmenage);
        strsubjectID = objTeachdetailTABLE.listSubjectID(strIDteacher,strgettermyearmenage);
        final String[] getNameSubject = new String[strsubjectID.length];
        for (int i = 0; i < strsubjectID.length; i++) {
            NameSuject = objSubjectTABLE.listName(strsubjectID[i]);
            for (int i1 = 0; i1 < NameSuject.length; i1++) {
                getNameSubject[i] = NameSuject[i1];
            }
        }
        strTermYear = objTeachdetailTABLE.listTermyear(strIDteacher,strgettermyearmenage);
        MyAdapterTerm myAdapterTerm = new MyAdapterTerm(Term_Manager.this, strTermID, getNameSubject,strTermYear);
        objListView.setAdapter(myAdapterTerm);

        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strgetID = strTermID[position];
                strgetSubject = strsubjectID[position];
                strgetTerm = strTermYear[position];
                strStatus = "1";
                ShowMenu();
            }
        });
    }
    private void ShowMenu() {
        CharSequence[] CharItem = {"ซ่อน", "แก้ไข", "ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        clickHide();
                        onResume();
                        dialog.dismiss();
                        break;
                    case 1:
                        Intent intent = new Intent(Term_Manager.this, UpdateTermActivity.class);
                        intent.putExtra("IDTerm", strgetID);
                        intent.putExtra("IDSubject", strgetSubject);
                        intent.putExtra("TermYear", strgetTerm);
                        intent.putExtra("IDTeacher", strIDteacher);
                        startActivity(intent);
                        dialog.dismiss();
                        break;
                    case 2:

                        //Intent intent1 = new Intent(Term_Manager.this,TestActivity.class);
                        //startActivity(intent1);
                        dialog.dismiss();
                }//switch
                //dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = builder.create();
        objAlertDialog.show();
    }
    public void clickHide() {

        objTeachdetailTABLE.updateValueToTEACHDETAIL(strgetID, strIDteacher,strgetSubject,strgetTerm,1);

        //updateValuetoSQL();
    }//clickHide

    private void updateValuetoSQL() {
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
            // Toast.makeText(EditSubject.this, "Update Subject" + strID, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }   //updateValuetoSQL

    private void setAllArray() {
        //strIDteacher = getIntent().getExtras().getString("Username");

    }
    private void setShowteacherUser() {
        String strShow = getIntent().getExtras().getString("Name");
        textTeacher.setText(strShow);
    }

    private void bindWidget() {
        strIDteacher = getIntent().getExtras().getString("Username");
    textTeacher = (TextView) findViewById(R.id.txtVteacher);
    objListView = (ListView) findViewById(R.id.listViewShowTerm);
        objSpinner = (Spinner) findViewById(R.id.spintermyearmanage);
}


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showHideItem:
              Intent intent1 = new Intent(Term_Manager.this, HideTermActivity.class);
                intent1.putExtra("IDTeacher", strIDteacher);
                intent1.putExtra("Year", strgettermyearmenage);
                startActivity(intent1);
                return true;
            case R.id.Exit:
                onBackPressed();
                return true;
            case R.id.Cancle:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
