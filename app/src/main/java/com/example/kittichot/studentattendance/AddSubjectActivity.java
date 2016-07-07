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
import android.widget.EditText;
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

public class AddSubjectActivity extends ActionBarActivity {

    //explicit
    private SubjectTABLE objSubjectTABLE;
    private String[] strListID, strListName, strSearchID, strSearchName;
    private TextView txtShowTescher;
    private String putExtraNAme, putExtraUSer;
    private String strEditid, strEditName, strSearch;
    private ListView objList;
    private EditText editText5;
    private MyAdapter objMyAdapter,myAdapter;

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_add_subject);
        objSubjectTABLE = new SubjectTABLE(this);
        strListID = objSubjectTABLE.listSubject();
        strListName = objSubjectTABLE.listSName();
        objMyAdapter=new MyAdapter(this,strListID,strListName);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //bind widget
        bindWidget();
        objList.setAdapter(objMyAdapter);
       /*editText5.getText().toString();
        editText5.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textlength = editText5.getText().length();
                if (editText5 != null) {
                    for (int i = 0; i < strListID.length; i++) {
                        try {
                            if (editText5.getText().toString().equalsIgnoreCase(strListID[i].subSequence(0, textlength).toString())) {
                                strSearch = strListID[i];
                                strSearchID = objSubjectTABLE.listSubjectSearch(strSearch);
                                strSearchName = objSubjectTABLE.listSNameSearch(strSearch);
                            }
                            objList.setAdapter(null);
                            if (objList == null) {
                                myAdapter = new MyAdapter(AddSubjectActivity.this, strSearchID, strSearchName);
                                objList.setAdapter(myAdapter);
                                onResume();
                            }


                        } catch (Exception e) {
                        }
                    }
                }
            }
            @Override
            public void afterTextChanged (Editable s){

            }

        });*/
        //Syn Json to SQLite
        //synJsonTOsubject();
        //setup All Array
        setupAllArray();

        //createListView
        createListView();
        createItem();
        //Button button;
        //Intent intent;
        //setUp Text Show Teacher
        setUpShowTeacher();
        //testSearch
        //testSearch();

    }//onCreate

    private void setUpShowTeacher() {

        String strTextShow = getIntent().getExtras().getString("Name");
        txtShowTescher.setText(strTextShow);
    }




    public void clickAdd(View view) {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraNAme = strTextShow;
        putExtraUSer = strTextShowUser;
        Intent objIntent = new Intent(AddSubjectActivity.this, Insert_Subject.class);
        objIntent.putExtra("Name", putExtraNAme);
        objIntent.putExtra("Username", putExtraUSer);
        startActivity(objIntent);
        onPause();
    }

    private void bindWidget() {

        txtShowTescher = (TextView) findViewById(R.id.textViewTeacher);
        objList = (ListView) findViewById(R.id.listViewSubject);
        editText5 = (EditText) findViewById(R.id.editText5);
    }//bind widget

    private void createListView() {
    }

    //activeClick
    private void createItem() {

        objList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strEditid = strListID[position];
                strEditName = strListName[position];

                checkLog();
                //ShowMenu
                ShowMenu();
            }//event
        });

    }//createListView

    private void ShowMenu() {
        CharSequence[] CharItem = {"ซ่อน", "แก้ไข","ยกเลิก"};
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
                        Intent intent = new Intent(AddSubjectActivity.this, EditSubject.class);
                        intent.putExtra("IDSubject", strEditid);
                        intent.putExtra("NameSubject", strEditName);
                        startActivity(intent);
                        dialog.dismiss();
                        break;
                    case 2:
                        dialog.dismiss();
                        break;
                }//switch
                //dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = builder.create();
        objAlertDialog.show();
    }//showmenu

    private void checkLog() {
        Log.d("editSubject", "EditID ==> " + strEditid);
        Log.d("editSubject", "EditName ==> " + strEditName);
    }

    private void setupAllArray() {


    }// setupAllArray

    public void clickHide() {

        objSubjectTABLE.updateValueToSubject(strEditid, strEditName, "1");

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
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("subject_id", strEditid));
            objNameValuePairs.add(new BasicNameValuePair("subject_name", strEditName));
            objNameValuePairs.add(new BasicNameValuePair("subject_status", "1"));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_update_data_subject.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            Toast.makeText(AddSubjectActivity.this, "Update Subject" + strEditid, Toast.LENGTH_SHORT).show();
            onResume();
        } catch (Exception e) {
            Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
        }
    }   //newSubject

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
                Intent intent1 = new Intent(AddSubjectActivity.this, HideSubjectActivity.class);
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




}//mainCLas
