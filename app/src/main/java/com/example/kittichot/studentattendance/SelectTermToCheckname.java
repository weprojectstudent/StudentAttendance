package com.example.kittichot.studentattendance;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectTermToCheckname extends ActionBarActivity {
    private Spinner objSpinner,StrSpinnerYear;
    private RegisterTABLE objRegisterTABLE;
    private String[] getIDStudentR,getNameSubject,getIDSubject;
    private String strSpinner,strSpinnerIDTerm;
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private String[] IDTerm, RoomNO,YearList,namesubject,idSubjectterm,idregisterm;
    private String getIDTERM, getRoom,getYear,strIDteacher,getnamesubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_term_to_checkname);
        objRegisterTABLE = new RegisterTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        StrSpinnerYear = (Spinner) findViewById(R.id.spinner);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setAllArray();
    }

    private void setAllArray() {
        strIDteacher = getIntent().getExtras().getString("Username");
        YearList = objTeachdetailTABLE.listTERMYearselect(strIDteacher);
        final ArrayList<String> arrayStringYear = new ArrayList<String>();
        for (int i = 0;i<YearList.length;i++ ) {
            arrayStringYear.add(YearList[i]);
        }
        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(
                this ,android.R.layout.simple_dropdown_item_1line,arrayStringYear);
        StrSpinnerYear.setAdapter(arrayAdapterYear);
        StrSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        objSpinner = (Spinner) findViewById(R.id.spinnerTermtocheck);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, spinnerArraynameSubect);

        objSpinner.setAdapter(adapterSpinner);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              strSpinner= arrayList.get(position);
                strSpinnerIDTerm = spinnerArraynameSubect.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void clicktocheckname(View view) {
        Intent intent = new Intent(SelectTermToCheckname.this,QrcodeActivity.class);
        intent.putExtra("IDTERM", strSpinner);
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
                Intent intent1 = new Intent(SelectTermToCheckname.this, ShowCheckname.class);
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
