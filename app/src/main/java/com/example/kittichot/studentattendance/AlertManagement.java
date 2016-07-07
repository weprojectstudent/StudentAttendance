package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AlertManagement extends ActionBarActivity {
    private ListView objListView;
    //private MyAdapterHomework objMyAdapterHomework;
    private AlertTABLE objAlertTABLE;
    private DateThai objDateThai;
    private TextView textView;
    private String putusernameTeacher;
    private String[] strIDAlert,strTitleAlert,strDetailAlert,strDATEsaveAlert,strDateAlert, strStatusAlert;
    private String getIDAlert,getTitleAlert,getDetailAlert,getDATEsaveAlert,getDateAlert, getStatusAlert;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_alert_management);
        objAlertTABLE = new AlertTABLE(this);
        objDateThai = new DateThai();
        BindWidget();
        SetAllArray();
        String s = getIntent().getExtras().getString("Username");
        putusernameTeacher = s;
        textView = (TextView) findViewById(R.id.textView24);
        textView.setText(putusernameTeacher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    private void BindWidget() {

        objListView = (ListView) findViewById(R.id.listViewALERT);

    }

    private void SetAllArray() {
        strIDAlert = objAlertTABLE.listID();
        strTitleAlert = objAlertTABLE.listTITLE();
        strDetailAlert = objAlertTABLE.listDetail();
        strDATEsaveAlert = objAlertTABLE.listDateSave();
        strDateAlert = objAlertTABLE.listDatealert();
        strStatusAlert = objAlertTABLE.listStatus();

       MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(AlertManagement.this, strIDAlert, strTitleAlert, strDetailAlert, strDATEsaveAlert, strDateAlert);
        objListView.setAdapter(objMyAdapterHomework);

        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIDAlert = strIDAlert[position];
                getTitleAlert = strTitleAlert[position];
                getDetailAlert = strDetailAlert[position];
                getDATEsaveAlert = strDATEsaveAlert[position];
                getDateAlert = strDateAlert[position];
                ShowMenu();
            }
        });
    }
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

                        Intent intent = new Intent(AlertManagement.this, UpdateAlert.class);
                        intent.putExtra("ID", getIDAlert);
                        intent.putExtra("TITLE", getTitleAlert);
                        intent.putExtra("DETAIL", getDetailAlert);
                        intent.putExtra("DATESAVE",objDateThai.setDateThai(getDATEsaveAlert));
                        intent.putExtra("DATEALERT", objDateThai.setDateThai(getDateAlert));
                        intent.putExtra("TEACHER", putusernameTeacher);
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

    public void clickHide() {

        objAlertTABLE.updateValueALERT(Integer.parseInt(getIDAlert), getTitleAlert, getDetailAlert, objDateThai.setDateThai(getDATEsaveAlert), objDateThai.setDateThai(getDateAlert), putusernameTeacher, 1);

        //upNewSubject();


    }

    public void clickTOInsertAlert(View view) {
        putusernameTeacher = getIntent().getExtras().getString("Username");
        Intent intent = new Intent(AlertManagement.this, Insert_Alert.class);
        intent.putExtra("teacher", putusernameTeacher);
        startActivity(intent);
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
                Intent intent1 = new Intent(AlertManagement.this, HideAlertActivity.class);
                intent1.putExtra("TEACHER", putusernameTeacher);
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
