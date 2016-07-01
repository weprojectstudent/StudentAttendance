package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class HideHomeworkActivity extends ActionBarActivity {
    private HomeworkTABLE objHomeworkTABLE;
    private TextView objTextViewTeacher;
    private ListView objListViewHomework;
    private String[]strHWID,strHWTT,strHWDT,strDateSave, strDatesent;
    private String getstrHWID,getstrHWTT,getstrHWDT,getstrDateSave, getstrDatesent;
    private DateThai objDateThai;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hide_homework);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objDateThai = new DateThai();
        bindwidget();
        setupAllArray();
        createListView();
        createItem();

    }//onCreate

    private void bindwidget() {
        objTextViewTeacher = (TextView) findViewById(R.id.txtViewTeacherHideHW);
        objListViewHomework = (ListView) findViewById(R.id.listViewhomeworkHide);
    }//bindwidget
    private void setupAllArray() {
        strHWID = objHomeworkTABLE.listIDHide();
        strHWTT = objHomeworkTABLE.listTitleHide();
        strHWDT = objHomeworkTABLE.listDetailHide();
        strDateSave = objHomeworkTABLE.listDatesaveHide();
        strDatesent = objHomeworkTABLE.listDatesentHide();

    }//setupAllArray
    private void createListView() {

        MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(HideHomeworkActivity.this, strHWID,strHWTT, strHWDT, strDateSave, strDatesent);
        objListViewHomework.setAdapter(objMyAdapterHomework);
    }//createListView
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
        CharSequence[] CharItem = {"ไม่ซ่อน","ยกเลิก"};
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

    private void clickCancleHide() {
        objHomeworkTABLE.updateValueToHomework(Integer.parseInt(getstrHWID), getstrHWTT, getstrHWDT,objDateThai.setDateThai(getstrDateSave),objDateThai.setDateThai(getstrDatesent), 0);
        onResume();
    }//clickHide

}
