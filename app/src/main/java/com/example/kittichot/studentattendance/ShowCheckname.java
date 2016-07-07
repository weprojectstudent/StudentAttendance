package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowCheckname extends ActionBarActivity {

    private Spinner objSpinner,objSpinnerTERMSHOW,objSpinnerRoomShow;
    private ListView objListView;
    private TeachdetailTABLE objTeachdetailTABLE;
    private StudentTABLE objStudentTABLE;
    private RegisterTABLE objRegisterTABLE;
    private ChecknamestudentTABLE objChecknamestudentTABLE;
    private SubjectTABLE objSubjectTABLE;
    private String[] strYEAR,strIDTERM,strNameSubject, strIDSubject,strIDTERMREGIS;
    private String[] strIDStudent, strroomStudent;
    private String getUsernameTeacher,getYearSelect,getIDTERM,getNAMESUBJECT;
    private String getIDStudent, getRoom;
    private MyAlertDialog objMyAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_checkname);
        objChecknamestudentTABLE = new ChecknamestudentTABLE(this);
        objRegisterTABLE = new RegisterTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objMyAlertDialog = new MyAlertDialog();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getUsernameTeacher = getIntent().getExtras().getString("Username");

        bindwidget();
        createSpinner();

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void bindwidget() {

        objSpinnerTERMSHOW = (Spinner) findViewById(R.id.spinnertermShowCheck);
        objSpinnerRoomShow = (Spinner) findViewById(R.id.spinnerRoomShowCheck);
        objSpinner = (Spinner) findViewById(R.id.spinnerTermYearShow);

    }

    private void createSpinner() {
        strYEAR = objTeachdetailTABLE.listTERMYearselect(getUsernameTeacher);
        final ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < strYEAR.length; i++) {
            arrayList.add(strYEAR[i]);
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arrayList);
        objSpinner.setAdapter(arrayAdapter);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getYearSelect=arrayList.get(position);
                createListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void createListView() {
        strIDTERM = objTeachdetailTABLE.listTermIDslect(getUsernameTeacher, getYearSelect);
        final ArrayList<String> arrayListNAMESubject = new ArrayList<String>();
        final ArrayList<String> arrayListIDTERM = new ArrayList<String>();
        for (int i = 0; i < strIDTERM.length; i++) {
            strIDTERMREGIS = objRegisterTABLE.ListRegisIDTermforYear(strIDTERM[i]);
            for (int i1 = 0; i1 < strIDTERMREGIS.length; i1++) {
                if (strIDTERMREGIS[i1] != null) {
                    arrayListIDTERM.add(strIDTERMREGIS[i1]);
                    strIDSubject = objTeachdetailTABLE.listSubjectIDTERM(strIDTERMREGIS[i1]);
                    for (int i2 = 0; i2 < strIDSubject.length; i2++) {
                        strNameSubject = objSubjectTABLE.listName(strIDSubject[i2]);
                        arrayListNAMESubject.add(strIDSubject[i2]+" "+strNameSubject[i2]);

                    }
                }
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arrayListNAMESubject);
        objSpinnerTERMSHOW.setAdapter(arrayAdapter);
        objSpinnerTERMSHOW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getIDTERM = arrayListIDTERM.get(position);
                getNAMESUBJECT= arrayListNAMESubject.get(position);
                setspinRoom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    private void setspinRoom() {
        int b = 0;
       final ArrayList<String> arrayListRoom = new ArrayList<String>();
        strIDStudent = objRegisterTABLE.ListRegisIDstudent(getIDTERM);
        for (int i = 0; i < strIDStudent.length; i++) {
            String[] strings = objStudentTABLE.ListClassRoomStudent(strIDStudent[i]);
            for (int i1 = 0; i1 < strings.length; i1++) {
                for (int i2 = 0; i2 < arrayListRoom.size(); i2++) {
                    if (String.valueOf(arrayListRoom.get(i2)).equals(strings[i1])) {
                        b++;
                    } else {
                        b = 0;
                    }

                }
                if (b >= 1) {


                } else {
                    arrayListRoom.add(strings[i1]);
                }
            }




        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, arrayListRoom);
        objSpinnerRoomShow.setAdapter(arrayAdapter);
        objSpinnerRoomShow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getRoom = arrayListRoom.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void ClickshowCheck(View view) {
        Intent intent = new Intent(getApplicationContext(),AddSubjectActivity.class);


    }



    private void ShowMenu() {
        CharSequence[] CharItem = {"ดูรายเช็คชื่อของวิชา"+getNAMESUBJECT,"ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(ShowCheckname.this, EditSubject.class);
                        intent.putExtra("IDTERM", getIDTERM);
                        intent.putExtra("NameSubject", getNAMESUBJECT);
                        intent.putExtra("Username",getUsernameTeacher);
                        startActivity(intent);
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

    private void Alerttest() {
        MyAlertDialog myAlertDialog = new MyAlertDialog();
        myAlertDialog.errorDiaLog(this,"",getIDTERM+" "+getNAMESUBJECT);
    }


}
