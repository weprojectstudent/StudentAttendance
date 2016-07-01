package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Homework_manament extends ActionBarActivity {
    private TextView objTextViewTeacher;
    private ListView objListViewHomework;
    private HomeworkTABLE objHomeworkTABLE;
    private String[]strHWID,strHWTT,strHWDT,strDateSave, strDatesent;
    private String getstrHWID,getstrHWTT,getstrHWDT,getstrDateSave, getstrDatesent;
    private DateThai objDateThai;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_homework_manament);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objDateThai = new DateThai();
        bindwidget();
        setupAllArray();
        createListView();
        createItem();


    }

    private void createListView() {

        MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(Homework_manament.this, strHWID,strHWTT, strHWDT, strDateSave, strDatesent);
        objListViewHomework.setAdapter(objMyAdapterHomework);
    }//createListView

    private void setupAllArray() {
        strHWID = objHomeworkTABLE.listID();
        strHWTT = objHomeworkTABLE.listTitle();
        strHWDT = objHomeworkTABLE.listDetail();
        strDateSave = objHomeworkTABLE.listDatesave();
        strDatesent = objHomeworkTABLE.listDatesent();

    }//setupAllArray

    public void clickAddValuehomework(View view) {
        Intent intent = new Intent(Homework_manament.this,insert_homework.class);
        startActivity(intent);
    }

    private void bindwidget() {
        objTextViewTeacher = (TextView) findViewById(R.id.textView8);
        objListViewHomework = (ListView) findViewById(R.id.listViewhomework);
    }

    //activeClick
    private void createItem() {
        objListViewHomework.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getstrHWID = strHWID[position];
               getstrHWTT=strHWTT[position];
                getstrHWDT = strHWDT[position];
                getstrDateSave=strDateSave[position];
                getstrDatesent = strDatesent[position];

                //checkLog();
                //ShowMenu
                ShowMenu();
            }//event
        });
    }//createItem
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
                        dialog.dismiss();
                        break;
                    case 1:
                        Intent intent = new Intent(Homework_manament.this, UpdateHomework.class);
                        intent.putExtra("IDHW", getstrHWID);
                        intent.putExtra("TitleHW", getstrHWTT);
                        intent.putExtra("DetailHW", getstrHWDT);
                        intent.putExtra("DatesaveHW", getstrDateSave);
                        intent.putExtra("DatesentHW", getstrDatesent);
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

    private void clickHide() {
        objHomeworkTABLE.updateValueToHomework(Integer.parseInt(getstrHWID), getstrHWTT, getstrHWDT,objDateThai.setDateThai(getstrDateSave),objDateThai.setDateThai(getstrDatesent), 1);
        onResume();
    }//clickHide
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
                Intent intent1 = new Intent(Homework_manament.this, HideHomeworkActivity.class);
                startActivity(intent1);
                return true;
            case R.id.Exit:
                onBackPressed();
                return true;
            case R.id.Cancle:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
