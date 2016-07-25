package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class HideAlertActivity extends ActionBarActivity {
    private ListView objListView;
    private String[] strIDAlert,strTitleAlert,strDetailAlert,strDATEsaveAlert,strDateAlert, strStatusAlert;
    private String getIDAlert,getTitleAlert,getDetailAlert,getDATEsaveAlert,getDateAlert, getStatusAlert;
    private String putusernameTeacher;
    private AlertTABLE objAlertTABLE;
    private DateThai objDateThai;
    private TextView textView;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hide_alert);
        objAlertTABLE = new AlertTABLE(this);
        objDateThai = new DateThai();
        String s = getIntent().getExtras().getString("TEACHER");
        putusernameTeacher = s;
        BindWidget();
        SetAllArray();
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
    private void BindWidget() {
        textView = (TextView) findViewById(R.id.textView25);
        textView.setText(putusernameTeacher);
        objListView = (ListView) findViewById(R.id.listViewALERTHide);

    }

    private void SetAllArray() {
        strIDAlert = objAlertTABLE.listIDHIDE();
        strTitleAlert = objAlertTABLE.listTITLEHIDE();
        strDetailAlert = objAlertTABLE.listDetailHIDE();
        strDATEsaveAlert = objAlertTABLE.listDateSaveHIDE();
        strDateAlert = objAlertTABLE.listDatealertHIDE();
        strStatusAlert = objAlertTABLE.listStatusHIDE();

        MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(HideAlertActivity.this, strIDAlert, strTitleAlert, strDetailAlert, strDATEsaveAlert, strDateAlert);
        objListView.setAdapter(objMyAdapterHomework);

        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIDAlert = strIDAlert[position];
                getTitleAlert = strTitleAlert[position];
                getDetailAlert = strDetailAlert[position];
                getDATEsaveAlert = objDateThai.setDateThai(strDATEsaveAlert[position]);
                getDateAlert = objDateThai.setDateThai(strDateAlert[position]);
                ShowMenu();
            }
        });
    }
    private void ShowMenu() {
        CharSequence[] CharItem = {"ยกเลิกการซ่อน", "ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        clickcancleHide();
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
    }//showmenu

    public void clickcancleHide() {

        objAlertTABLE.updateValueALERT(Integer.parseInt(getIDAlert) , getTitleAlert,getDetailAlert,getDATEsaveAlert,getDateAlert,putusernameTeacher,0);

        //upNewSubject();


    }

}
