package com.example.kittichot.studentattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CheckNameHomeWorkActivity extends ActionBarActivity {
    private ListView objListView;
    private StudentTABLE objStudentTABLE;
    private SubjectTABLE objSubjectTABLE;
    private RegisterTABLE objregisterTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private DateThai objDateThai;
    private String[] getNameA, getIDA, getSurnameA, getClassroom, getNO, getNumID,getIDSubject,getNameSubject,IDREGISALL;
    private String getActionScan,getdate;
    private HomeworksendingTABLE objHomeworksendingTABLE;
    private ArrayList<String> stringShow = new ArrayList<String>();
    private ArrayList<String> stringShowID = new ArrayList<String>();
    private int year_x,month_x, day_x;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    private String getIntentIDTERM,getIntentIDHW,getIntentTITLE,getIntentRoom;
    private int anInt;
    private TextView textViewIDHW,textViewTITIEHW,textViewIDTERM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_name_home_work);
        objStudentTABLE = new StudentTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objregisterTABLE = new RegisterTABLE(this);
        objHomeworksendingTABLE = new HomeworksendingTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objDateThai = new DateThai();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        String strIDTERM = getIntent().getExtras().getString("IDTERM");
        String strIDHW = getIntent().getExtras().getString("IDHW");
        String strTITLE = getIntent().getExtras().getString("TITLE");
        String strRoom = getIntent().getExtras().getString("Room");
        getIntentIDHW = strIDHW;
        getIntentTITLE = strTITLE;
        getIntentIDTERM = strIDTERM;
        getIntentRoom = strRoom;
        getIDSubject = objTeachdetailTABLE.listSubjectIDTERM(getIntentIDTERM);
        for (int i = 0; i < getIDSubject.length; i++) {
            getNameSubject = objSubjectTABLE.listName(getIDSubject[i]);
        }
        textViewIDTERM = (TextView) findViewById(R.id.textIDTERMCHW);
        textViewIDTERM.setText(getNameSubject[0]);
        textViewIDHW = (TextView) findViewById(R.id.textView31);
        textViewIDHW.setText("ID "+getIntentIDHW+" หัวข้อ "+getIntentTITLE);
    }
    public void scanQR(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(CheckNameHomeWorkActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //int i = 0;
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> strings = new ArrayList<String>();
                ArrayList<String> stringID = new ArrayList<String>();
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                String s = contents;

                getNumID = objregisterTABLE.ListRegisIDstudentandterm(Integer.parseInt(s), getIntentIDTERM);
                if (getNumID.length >= 1) {
                        strings.add(s);
                        stringID.add(getNumID[0]);
                } else {
                    MyAlertDialog myAlertDialog = new MyAlertDialog();
                    myAlertDialog.errorDiaLog(this,"ไม่พบข้อมูล","ไม่พบของนักเรีบน ในวิชานี้");
                }

                for (int i = 0; i < strings.size(); i++) {
                    stringShowID.add(stringID.get(i));
                    getNameA = objStudentTABLE.ListNameStudentIDREGIS(strings.get(i));
                    getSurnameA = objStudentTABLE.ListSurNameStudentIDREGIS(strings.get(i));
                    getClassroom = objStudentTABLE.ListClassRoomStudentIDREGIS(strings.get(i));
                    getNO = objStudentTABLE.ListNoStudentIDREGIS(strings.get(i));

                    for (int o = 0; o < getNameA.length; o++) {
                        stringShow.add("รหัสในรายวิชา " + getNumID[o] + " รหัสประจำ " + strings.get(i) + "\n" + "ชื่อ " + String.valueOf(getNameA[o]) + " " + String.valueOf(getSurnameA[o]) + "\n" + "ห้อง " + String.valueOf(getClassroom[o]) + " เลขที่ " + String.valueOf(getNO[o]));
                    }
                }

            }
        }
    }
    private void ShowMenu() {
        CharSequence[] CharItem = {"ลบ", "ยกเลิก"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.iconalertmenu);
        builder.setTitle("เมนู");
        builder.setSingleChoiceItems(CharItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        stringShowID.remove(anInt);
                        stringShow.remove(getActionScan);
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
    public void clickcheckTohomework(View view){
        /*getdate = year_x + "-" + month_x + "-" + day_x;
        for (int s = 0;s<stringShowID.size();s++){
            String s1 = stringShowID.get(s);
            objHomeworksendingTABLE.AddAttendanceHomework(Integer.parseInt(getIntentIDHW),Integer.parseInt(s1), getdate,"0");

        }
        ArrayList<String> string = new ArrayList<String>();
        for (int i=0;i<stringShow.size();i++){

            string.add(stringShow.get(i)+"\n");
        }
        //final CharSequence[] items = strings.toArray(new CharSequence[strings.size()]);
        int s = string.toString().length()-1;
        int s5 = stringShowID.toString().length()-1;
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(CheckNameHomeWorkActivity.this);
        myAlertDialog.setTitle("ทำการเช็คชื่อรักเรียน ");
        myAlertDialog.setIcon(R.drawable.user);
        //myAlertDialog.setMessage(string.toString().substring(1,s));
        myAlertDialog.setMessage(stringShowID.toString().substring(1,s5));
        myAlertDialog.setCancelable(false);
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myAlertDialog.show();*/

        String room = getIntent().getExtras().getString("Room");
        getdate = year_x + "-" + month_x + "-" + day_x;
        String[] strings = objStudentTABLE.ListIDStudent(room);
        int num = strings.length;
        int numrow =0;
        for (int i = 0; i < strings.length; i++) {
            IDREGISALL = objregisterTABLE.ListRegisIDforterm(getIntentIDTERM,strings[i]);
            for (int i1 = 0; i1 < IDREGISALL.length; i1++) {
                String[] s = objHomeworksendingTABLE.ChecknameHomeworkIDC(getIntentIDHW,IDREGISALL[i1],objDateThai.dateThai(getdate));
                if (s.length < 1) {
                    objHomeworksendingTABLE.AddAttendanceHomework(Integer.parseInt(getIntentIDHW),Integer.parseInt(IDREGISALL[i1]), objDateThai.dateThai(getdate),"1");
                } else {

                }
                numrow++;
            }
        }
        if (numrow == num) {
            onupdate();
       }



    }

    private void onupdate() {
        for (int s = 0;s<stringShowID.size();s++){
            String s1 = stringShowID.get(s);
            //objChecknamestudentTABLE.addValueCheckname(Integer.parseInt(s1), getdate, 0);
            objHomeworksendingTABLE.updateAttendanceHomework(Integer.parseInt(getIntentIDHW),Integer.parseInt(s1),objDateThai.dateThai(getdate),"0");
        }
        ArrayList<String> string = new ArrayList<String>();
        for (int i=0;i<stringShow.size();i++){

            string.add(stringShow.get(i)+"\n");
        }
        //final CharSequence[] items = strings.toArray(new CharSequence[strings.size()]);
        int s = string.toString().length()-1;
        int s5 = stringShowID.toString().length()-1;
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(CheckNameHomeWorkActivity.this);
        myAlertDialog.setTitle("ทำการเช็คชื่อรักเรียน");
        myAlertDialog.setIcon(R.drawable.user);
        //myAlertDialog.setMessage(string.toString().substring(1,s));
        myAlertDialog.setMessage(string.toString().substring(1,s));
        myAlertDialog.setCancelable(false);
        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myAlertDialog.show();
    }
    public void onBackPressed(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setIcon(R.drawable.alerticon);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();


    }

    @Override
    protected void onResume() {
        super.onResume();
        objListView = (ListView) findViewById(R.id.listNameCheckHW);
        MyAdapterCheckname myAdapterCheckname = new MyAdapterCheckname(CheckNameHomeWorkActivity.this,stringShow);
        /*objListView.setAdapter(new ArrayAdapter(this
                , android.R.layout.simple_list_item_1,stringShow));*/
        objListView.setAdapter(myAdapterCheckname);
        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActionScan = stringShow.get(position);
                anInt = position;
                ShowMenu();
            }
        });
    }

}
