package com.example.kittichot.studentattendance;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowListHomeworkCheck extends ActionBarActivity {
    //private ChecknamestudentTABLE objChecknamestudentTABLE;
    private StudentTABLE objStudentTABLE;
    private RegisterTABLE objRegisterTABLE;
    ///private TeachdetailTABLE objTeachdetailTABLE;
    private HomeworkTABLE objHomeworkTABLE;
    private HomeworksendingTABLE objHomeworksendingTABLE;
    private ListView objListView,objListViewDialog;
    private String getIntentRoom, getInTentTERM,getInTentSubject,getInTentUser;
    private String[] IDstudent,IDregis,NameStudent,SurnameStudent,NoStudent,IDSubject,IDTERM,IDregisfordate,
            HWCheckname,IDStudentforDate,Status,getIDstudent,nameHomeWork;
    private String strIDstudent,strIDregis,strNameStudent,strSurnameStudent,strRoomStudent,strIDSubject,
            strIDTERM, strDateCheckname,strNameHW,strIDHW;
    private TextView txtRoomS, txtSubjectS,txtUSERS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_homework_check);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo2);
        objRegisterTABLE = new RegisterTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objHomeworksendingTABLE = new HomeworksendingTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        getIntentRoom = getIntent().getExtras().getString("ROOM");
        getInTentTERM = getIntent().getExtras().getString("TERM");
        getInTentSubject = getIntent().getExtras().getString("SUBJECT");
        getInTentUser = getIntent().getExtras().getString("USERNAME");
        Bindwidget();
    }

    private void Bindwidget() {
        txtSubjectS = (TextView) findViewById(R.id.textView62);
        txtRoomS = (TextView) findViewById(R.id.textView64);
        txtUSERS = (TextView) findViewById(R.id.textView65);
        txtSubjectS.setText(getInTentSubject);
        txtRoomS.setText(getIntentRoom);
        txtUSERS.setText(getInTentUser);
        objListView = (ListView) findViewById(R.id.listView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
         createListView();
    }
    private void createListView() {
        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayList<String> arrayListName = new ArrayList<String>();
        int b = 0;
        IDstudent = objStudentTABLE.ListIDStudent(getIntentRoom);
        // for (int i = 0; i < IDstudent.length;i++) {
        IDregis = objRegisterTABLE.ListRegisIDforterm(getInTentTERM,IDstudent[0]);
        for (int i1 = 0; i1 < IDregis.length; i1++) {
            HWCheckname = objHomeworksendingTABLE.CheckHWSDIDforListAttendance(IDregis[i1]);
            for (int i2 = 0; i2 < HWCheckname.length; i2++) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (arrayList.get(i3).equals(HWCheckname[i2]) ) {
                        b++;
                    } else {

                        b = 0;
                    }//else1

                }//for4
                if (b >= 1) {

                } else {
                    arrayList.add(HWCheckname[i2]);
                    nameHomeWork = objHomeworkTABLE.listNameHWforAttendance(HWCheckname[i2]);
                    for (int i = 0; i < nameHomeWork.length; i++) {
                        arrayListName.add(nameHomeWork[i]);
                    }

                }
            }//for3

        }//for2

        //}//for1
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayListName);
        objListView.setAdapter(arrayAdapter);
        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strIDHW=arrayList.get(position);
                strNameHW = arrayListName.get(position);
                final Dialog dialog = new Dialog(ShowListHomeworkCheck.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setTitle("รายการของห้อง :"+getIntentRoom +" หัวข้อการบ้าน :"+strNameHW);
                dialog.setContentView(R.layout.alertcustomHW);
                dialog.setCancelable(true);
                Button button1 = (Button)dialog.findViewById(R.id.button7HW);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(ShowListHomeworkCheck.this, "Close dialog", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                IDStudentforDate = objStudentTABLE.ListIDStudent(getIntentRoom);
                final ArrayList<String> arrayListStatus = new ArrayList<String>();
                final ArrayList<String> arrayListIDstudent = new ArrayList<String>();
                final ArrayList<String> arrayListnamestudent = new ArrayList<String>();
                final ArrayList<String> arrayListsurnamestudent = new ArrayList<String>();
                final ArrayList<String> arrayListNOstudent = new ArrayList<String>();

                for (int i = 0; i < IDStudentforDate.length; i++) {
                    String[] IDregister = objRegisterTABLE.ListRegisIDstudentandterm(Integer.parseInt(IDStudentforDate[i]), getInTentTERM);
                    for (int i1 = 0; i1 < IDregister.length; i1++) {
                        IDregisfordate = objHomeworksendingTABLE.IDregisFordate(strIDHW, IDregister[i1]);
                        Status = objHomeworksendingTABLE.ChecknameSTATUSFordate(IDregisfordate[i1], strIDHW);
                        for (int i2 = 0; i2 < IDregisfordate.length; i2++) {
                            arrayListStatus.add(Status[i2]);
                            getIDstudent = objRegisterTABLE.ListIDStudentforIDregis(IDregisfordate[i2]);
                            for (int i3 = 0; i3 < getIDstudent.length; i3++) {
                                NameStudent = objStudentTABLE.ListNameStudent(getIDstudent[i3]);
                                SurnameStudent = objStudentTABLE.ListSurNameStudent(getIDstudent[i3]);
                                NoStudent = objStudentTABLE.ListNoStudentIDREGIS(getIDstudent[i3]);
                                arrayListIDstudent.add(getIDstudent[i3]);
                                for (int i4 = 0; i4 < NameStudent.length; i4++) {
                                    arrayListnamestudent.add(NameStudent[i4]);
                                    arrayListsurnamestudent.add(SurnameStudent[i4]);
                                    arrayListNOstudent.add(NoStudent[i4]);
                                }
                            }

                        }
                    }
                }
        for (int i = 0; i < IDregisfordate.length; i++) {

            IDStudentforDate = objRegisterTABLE.ListIDStudentforIDregis(IDregisfordate[i]);
            for (int i1 = 0; i1 < IDStudentforDate.length; i1++) {
NameStudent = objStudentTABLE.ListNameStudent(IDStudentforDate[i]);
                    SurnameStudent = objStudentTABLE.ListSurNameStudent(IDStudentforDate[i]);
                    NoStudent = objStudentTABLE.ListNoStudentIDREGIS(IDStudentforDate[i]);
            }

        }
                objListViewDialog = (ListView) dialog.findViewById(R.id.listViewStatusCheckname);
                MyadapterListcheckstatus myadapterListcheckstatus = new MyadapterListcheckstatus(ShowListHomeworkCheck.this,arrayListIDstudent, arrayListnamestudent, arrayListsurnamestudent,arrayListNOstudent, arrayListStatus);
                objListViewDialog.setAdapter(myadapterListcheckstatus);
                dialog.show();

            }
        });

    }//CreateListview

  }
