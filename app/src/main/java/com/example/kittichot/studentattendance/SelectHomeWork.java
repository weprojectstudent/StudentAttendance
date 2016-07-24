package com.example.kittichot.studentattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectHomeWork extends ActionBarActivity {
    private Spinner objSpinner,objSpinnerIDTERM,objSpinnerYear,objspinnerRoom;
    private RegisterTABLE objRegisterTABLE;
    private HomeworkTABLE objHomeworkTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private StudentTABLE objStudentTABLE;
    private String strSpinnerIDTerm;
    private String[] getIDHomeWork,getTiTle,strtermyear,getIDTerm,getIDStudentR,getNameSubject;
    private String strSpinner,strspinTERM,strgetSpintterm,strgetSpinIDHW,termyearslect,strNameSubject;
    private String[] IDTerm, RoomNO,YearList,namesubject,idSubjectterm,idregisterm;
    private String getIDTERM, getRoom,getYear,strIDteacher,getnamesubject;
    private String[] strIDStudent,getRoomSubject,getIDSubject;
    private int intID;
    private  ArrayList<String> spinnerArrayID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_home_work);
        objRegisterTABLE = new RegisterTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        objSpinner = (Spinner) findViewById(R.id.spinnerHWIDtocheck);
        objSpinnerIDTERM = (Spinner) findViewById(R.id.spinnerIDTermHWtocheck);
        objSpinnerYear = (Spinner) findViewById(R.id.spinnerselectyearhome);
        objspinnerRoom = (Spinner) findViewById(R.id.spinner3);
        spinnerArrayID = new ArrayList<String>();
        createListView();
        setAllArray();
        //createSpinnerYear();
    }

    //newroom
    private void setAllArray() {
        strIDteacher = getIntent().getExtras().getString("Username");
        YearList = objTeachdetailTABLE.listTERMYearselect(strIDteacher);
        final ArrayList<String> arrayStringYear = new ArrayList<String>();
        for (int i = 0;i<YearList.length;i++ ) {
            arrayStringYear.add(YearList[i]);
        }
        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(
                this ,android.R.layout.simple_dropdown_item_1line,arrayStringYear);
        objSpinnerYear.setAdapter(arrayAdapterYear);
        objSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getYear = arrayStringYear.get(position);
                createSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void createSpinner() {
        strIDteacher = getIntent().getExtras().getString("Username");
        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayList<String> spinnerArraynameSubect = new ArrayList<String>();
        IDTerm = objTeachdetailTABLE.listTermIDslect(strIDteacher,getYear);
        for (int i=0;i<IDTerm.length;i++) {
            idregisterm = objRegisterTABLE.ListRegisIDTermforYear(IDTerm[i]);
            for (int i1=0;i1<idregisterm.length;i1++) {
                if (idregisterm[i1] != null) {
                    arrayList.add(idregisterm[i1]);
                    idSubjectterm = objTeachdetailTABLE.listIDSubjectforregis(idregisterm[i1]);
                    for (int i2=0;i2<idSubjectterm.length;i2++) {
                        namesubject = objSubjectTABLE.listName(idSubjectterm[i2]);
                        spinnerArraynameSubect.add(namesubject[i2]);
                    }
                }
            }
        }

        //getIDStudentR = objRegisterTABLE.ListRegisTerm();
        // ArrayList<String> arrayList = new ArrayList<String>();
        // for (int i=0;i<getIDStudentR.length;i++) {
        //     getIDSubject = objTeachdetailTABLE.listSubjectIDTERM(getIDStudentR[i]);
        //    for (int i1=0;i1<getIDSubject.length;i1++) {
        //         getNameSubject = objSubjectTABLE.listName(getIDSubject[i1]);
        //         arrayList.add(getNameSubject[i1]);
        //       }
        //  }
        //  final ArrayList<String> spinnerArray = new ArrayList<String>();
        // for (int i=0;i<getIDStudentR.length;i++){
        //     spinnerArray.add(getIDStudentR[i]);
        //  }
        //objSpinnerIDTERM = (Spinner) findViewById(R.id.spinnerTermtocheck);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, spinnerArraynameSubect);

        objSpinnerIDTERM.setAdapter(adapterSpinner);
        objSpinnerIDTERM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strspinTERM= arrayList.get(position);
                strSpinnerIDTerm = spinnerArraynameSubect.get(position);
                spinnerRoom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerRoom() {
        int b = 0;
        final ArrayList<String> arrayListRoom = new ArrayList<String>();
        strIDStudent = objRegisterTABLE.ListRegisIDstudent(strspinTERM);
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
        objspinnerRoom.setAdapter(arrayAdapter);
        objspinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getRoom = arrayListRoom.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
//newroom
    private void createSpinnerYear() {
       // final ArrayList<String> arrayListYear = new ArrayList<String>();
        strIDteacher = getIntent().getExtras().getString("Username");
        /*String strIDteacher = getIntent().getExtras().getString("Username");
        strtermyear = objTeachdetailTABLE.listTERMYearselect(strIDteacher);
        final ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < strtermyear.length;i++ ) {
            arrayList.add(strtermyear[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,arrayList);

        objSpinnerYear.setAdapter(arrayAdapter);
        objSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                termyearslect = arrayList.get(position);
                createSpinnerIDTerm();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


    }


    private void createSpinnerIDTerm(){
            /*String strIDteacher = getIntent().getExtras().getString("Username");
            final ArrayList<String> arrayList = new ArrayList<String>();
            final ArrayList<String> arrayList1 = new ArrayList<String>();
            getIDTerm = objTeachdetailTABLE.listTermID(strIDteacher, termyearslect);
            for (int i=0;i<getIDTerm.length;i++) {
                getIDStudentR = objRegisterTABLE.ListRegisIDTermforYear(getIDTerm[i]);
                for (int i1=0;i1<getIDStudentR.length;i1++) {
                    if (getIDStudentR[i1] != null) {
                        arrayList1.add(getIDStudentR[i1]);
                        getIDSubject = objTeachdetailTABLE.listSubjectIDTERM(getIDStudentR[i1]);
                        for (int i2=0;i2<getIDSubject.length;i2++) {
                            getNameSubject = objSubjectTABLE.listName(getIDSubject[i2]);
                            arrayList.add(getNameSubject[i2]);
                        }
                    }
                }
            }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this
                ,android.R.layout.simple_dropdown_item_1line,arrayList);
        objSpinnerIDTERM.setAdapter(arrayAdapter);
        objSpinnerIDTERM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strspinTERM = arrayList1.get(position);
                strNameSubject = arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }

    private void createListView() {
        getIDHomeWork = objHomeworkTABLE.listID();
        getTiTle = objHomeworkTABLE.listTitle();

        final ArrayList<String> spinnerArrayTitle = new ArrayList<String>();
        for (int i=0;i<getIDHomeWork.length;i++){
            spinnerArrayID.add(getIDHomeWork[i]);
            spinnerArrayTitle.add(getTiTle[i]);
        }
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, spinnerArrayTitle);

        objSpinner.setAdapter(adapterSpinner);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSpinner= spinnerArrayTitle.get(position);
                intID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void clicktoCheckHW(View view){
        strgetSpintterm = strspinTERM;
        strgetSpinIDHW = spinnerArrayID.get(intID);
        Intent intent = new Intent(SelectHomeWork.this,CheckNameHomeWorkActivity.class);
        intent.putExtra("IDTERM", strgetSpintterm);
        intent.putExtra("IDHW", strgetSpinIDHW);
        intent.putExtra("TITLE", strSpinner);
        intent.putExtra("Room", getRoom);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showChecknameItem:
                Intent intent1 = new Intent(SelectHomeWork.this, SelectHomeworkToLIstCheckname.class);
                intent1.putExtra("Username", strIDteacher);
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
