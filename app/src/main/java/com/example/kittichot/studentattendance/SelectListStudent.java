package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectListStudent extends ActionBarActivity {
    private StudentTABLE objStudentTABLE;
    private RegisterTABLE objRegisterTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private MyAdapterStudent objMyAdapterStudent;
    private ListView objListView;
    private TextView objTextView;
    private Spinner objSpinner;
    private String[] getNameStudent,getIDStudent,getSurnameStudent,getClassRoomStudent,getNOStudent,getIDStudentR,getNameSubject,getIDSubject,getIDTerm;
    private String getNameS,getIDStudentS,getSurnames,termyearslect;
    private String getRoom,getIDTERM;
    private String[] strings,strtermyear;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_select_list_student);
        objStudentTABLE = new StudentTABLE(this);
        objRegisterTABLE = new RegisterTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        BindWidGet();
        setAllArray();


    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void createListView() {
        String strIDteacher = getIntent().getExtras().getString("Username");
        ArrayList<String> arrayList = new ArrayList<String>();
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


        //objMyAdapterStudent = new MyAdapterStudent(this, getIDStudentR);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        objListView.setAdapter(arrayAdapter);

        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIDStudentS = arrayList1.get(position);
                getIDStudent = objRegisterTABLE.ListRegisIDstudent(getIDStudentS);
                ArrayList<String> objStrings = new ArrayList<String>();
                for (int i = 0; i < getIDStudent.length; i++) {
                    getNameStudent = objStudentTABLE.ListNameStudentIDREGIS(getIDStudent[i]);
                    getSurnameStudent = objStudentTABLE.ListSurNameStudentIDREGIS(getIDStudent[i]);
                    getClassRoomStudent = objStudentTABLE.ListClassRoomStudentIDREGIS(getIDStudent[i]);
                    getNOStudent = objStudentTABLE.ListNoStudentIDREGIS(getIDStudent[i]);
                    for (int o = 0; o < getSurnameStudent.length; o++) {
                        objStrings.add(getIDStudent[i] + " " + String.valueOf(getNameStudent[o]) + " " + String.valueOf(getSurnameStudent[o]) + " " + String.valueOf(getClassRoomStudent[o]) + " " + String.valueOf(getNOStudent[o]) + "\n");
                    }
                }
                final CharSequence[] items = objStrings.toArray(new CharSequence[objStrings.size()]);
                int s = objStrings.toString().length()-1;
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SelectListStudent.this);
                myAlertDialog.setTitle("รายชื่อนักเรียนของวิชา " + getIDStudentS);
                myAlertDialog.setIcon(R.drawable.user);
                myAlertDialog.setMessage(objStrings.toString().substring(1,s));
                myAlertDialog.setCancelable(false);
                myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                myAlertDialog.show();

            }

        });
        objListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getIDStudentS = arrayList1.get(position);
                onshowmenu();
                return true;
            }
        });

    }

    private void onshowmenu() {
        CharSequence[] CharItem = {"ลบ", "ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        getIDStudent = objRegisterTABLE.ListRegisIDstudent(getIDStudentS);
                        ArrayList<String> objStrings = new ArrayList<String>();
                        for (int i = 0; i < getIDStudent.length; i++) {
                            getNameStudent = objStudentTABLE.ListNameStudentIDREGIS(getIDStudent[i]);
                            getSurnameStudent = objStudentTABLE.ListSurNameStudentIDREGIS(getIDStudent[i]);
                            getClassRoomStudent = objStudentTABLE.ListClassRoomStudentIDREGIS(getIDStudent[i]);
                            getNOStudent = objStudentTABLE.ListNoStudentIDREGIS(getIDStudent[i]);
                            for (int o = 0; o < getSurnameStudent.length; o++) {
                                objStrings.add(getIDStudent[i] + " " + String.valueOf(getNameStudent[o]) + " " + String.valueOf(getSurnameStudent[o]) + " " + String.valueOf(getClassRoomStudent[o]) + " " + String.valueOf(getNOStudent[o]) + "\n");
                            }
                        }

                        int s = objStrings.toString().length()-1;
                        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SelectListStudent.this);
                        myAlertDialog.setTitle("ต้องการ ' ลบ ' รายชื่อนักเรียนของวิชา " + getIDStudentS);
                        myAlertDialog.setIcon(R.drawable.user);
                        myAlertDialog.setMessage(objStrings.toString().substring(1,s));
                        myAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ArrayList<String> objStrings = new ArrayList<String>();
                                for (int y=0;y<getIDStudent.length;y++){
                                    strings = objRegisterTABLE.ListRegisIDstudentandterm(Integer.parseInt(getIDStudent[y]),getIDStudentS);
                                   for (int i=0;i<strings.length;i++){
                                       objStrings.add(strings[i]);

                                   }
                                }
                                for (int i = 0;i<objStrings.size();i++) {
                                    objRegisterTABLE.Delete(objStrings.get(i));

                                }
                                onResume();
                            }
                        });

                        myAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        myAlertDialog.show();
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

    }


    public void Selectstudent(View view) {
        String strIDteacher = getIntent().getExtras().getString("Username");
    Intent intent = new Intent(SelectListStudent.this,SelectedStudents.class);
        intent.putExtra("Username", strIDteacher);
    startActivity(intent);
}

    private void setAllArray() {
       // getRoom = getIntent().getExtras().getString("LISTROOM");
        //getIDTERM = getIntent().getExtras().getString("IDTERM");
        //getIDA = objStudentTABLE.ListIDStudent(getRoom);
       // getNameA = objStudentTABLE.ListNameStudent(getRoom);
       // getSurnameA = objStudentTABLE.ListSurNameStudent(getRoom);
        String strIDteacher = getIntent().getExtras().getString("Username");
        strtermyear = objTeachdetailTABLE.listTERMYearselect(strIDteacher);
        final ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < strtermyear.length;i++ ) {
            arrayList.add(strtermyear[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,arrayList);
        objSpinner = (Spinner) findViewById(R.id.spinnertermyearslectlist);
        objSpinner.setAdapter(arrayAdapter);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                termyearslect = arrayList.get(position);
                createListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //getNameA = objRegisterTABLE.ListRegisNUM(getIDA);
    }

    private void BindWidGet() {
        objListView = (ListView) findViewById(R.id.listViewStudentselected);

    }

}
