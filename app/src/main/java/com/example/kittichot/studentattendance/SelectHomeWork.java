package com.example.kittichot.studentattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectHomeWork extends ActionBarActivity {
    private Spinner objSpinner,objSpinnerIDTERM,objSpinnerYear;
    private RegisterTABLE objRegisterTABLE;
    private HomeworkTABLE objHomeworkTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private String[] getIDHomeWork,getTiTle,getIDTERM,strtermyear,getIDTerm,getIDStudentR,getIDSubject,getNameSubject;
    private String strSpinner,strspinTERM,strgetSpintterm,strgetSpinIDHW,strIDteacher,termyearslect,strNameSubject;
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        objSpinner = (Spinner) findViewById(R.id.spinnerHWIDtocheck);
        objSpinnerIDTERM = (Spinner) findViewById(R.id.spinnerIDTermHWtocheck);
        objSpinnerYear = (Spinner) findViewById(R.id.spinnerselectyearhome);
        spinnerArrayID = new ArrayList<String>();
        createListView();
        createSpinnerYear();
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void createSpinnerYear() {
       // final ArrayList<String> arrayListYear = new ArrayList<String>();
        strIDteacher = getIntent().getExtras().getString("Username");
        String strIDteacher = getIntent().getExtras().getString("Username");
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
        });


    }


    private void createSpinnerIDTerm(){
            String strIDteacher = getIntent().getExtras().getString("Username");
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
        });

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
        startActivity(intent);

    }
}
