package com.example.kittichot.studentattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class QrcodeActivity extends ActionBarActivity {
    private ListView objListView;
    private StudentTABLE objStudentTABLE;
    private RegisterTABLE objregisterTABLE;
    private String[] getNameA, getIDA,
            getSurnameA, getClassroom, getNO,
            getNumID,getNameSubject,getIDSubject,IDREGISALL;
    private String getActionScan,getdate;
    private ChecknamestudentTABLE objChecknamestudentTABLE;
    private ArrayList<String> stringShow = new ArrayList<String>();
    private ArrayList<String> stringShowID = new ArrayList<String>();
    private int year_x,month_x, day_x;
    public static final int REQUEST_QR_SCAN = 4;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    private String getIntent,getname;
    private int anInt;
    private TextView textView;
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        objStudentTABLE = new StudentTABLE(this);
        objregisterTABLE = new RegisterTABLE(this);
        objChecknamestudentTABLE = new ChecknamestudentTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH)+1;
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        String s = getIntent().getExtras().getString("IDTERM");
        getIntent = s;
        textView = (TextView) findViewById(R.id.textView27);
        ArrayList<String> arrayList = new ArrayList<String>();
        getIDSubject = objTeachdetailTABLE.listSubjectIDTERM(getIntent);
            for (int i=0;i<getIDSubject.length;i++) {
                getNameSubject = objSubjectTABLE.listName(getIDSubject[i]);
                for (int i1=0;i1<getNameSubject.length;i1++) {
                    getname = getNameSubject[i1];
                }

            }
        textView.setText(getname);
    }

    public void scanBar(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(QrcodeActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void scanQR(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(QrcodeActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
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
                getNumID = objregisterTABLE.ListRegisIDstudentandterm(Integer.parseInt(s), getIntent);

                if (getNumID.length>=1){
                   strings.add(s);
                    stringID.add(getNumID[0]);
               }else {
                    MyAlertDialog myAlertDialog = new MyAlertDialog();
                    myAlertDialog.errorDiaLog(this,"ไม่พบข้อมูล","ไม่พบของนักเรีบน ในเทอม "+getIntent);
                }

                    for (int i = 0; i < strings.size(); i++) {
                        stringShowID.add(stringID.get(i));
                        getNameA = objStudentTABLE.ListNameStudentIDREGIS(strings.get(i));
                        getSurnameA = objStudentTABLE.ListSurNameStudentIDREGIS(strings.get(i));
                        getClassroom = objStudentTABLE.ListClassRoomStudentIDREGIS(strings.get(i));
                        getNO = objStudentTABLE.ListNoStudentIDREGIS(strings.get(i));
                        for (int o = 0; o < getNameA.length; o++) {
                            stringShow.add("รหัสในรายวิชา " + getNumID[o] + " รหัสประจำ " + strings.get(i) + "\n" + " ชื่อ " + String.valueOf(getNameA[o]) + " " + String.valueOf(getSurnameA[o]) + "\n" + "ห้อง " + String.valueOf(getClassroom[o]) + " เลขที่ " + String.valueOf(getNO[o]));
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

        public void clickcheck(View view){
            getdate = year_x + "-0" + month_x + "-" + day_x;
            IDREGISALL = objregisterTABLE.ListRegisIDforterm(getIntent);
            for (int i = 0; i < IDREGISALL.length; i++) {
                objChecknamestudentTABLE.addValueCheckname(Integer.parseInt(IDREGISALL[i]), getdate, 1);
            }
          for (int s = 0;s<stringShowID.size();s++){
                String s1 = stringShowID.get(s);
               //objChecknamestudentTABLE.addValueCheckname(Integer.parseInt(s1), getdate, 0);
              objChecknamestudentTABLE.updateValueCheckname(Integer.parseInt(s1),getdate,0);
           }
            ArrayList<String> string = new ArrayList<String>();
                for (int i=0;i<stringShow.size();i++){

                    string.add(stringShow.get(i)+"\n");
                }
            //final CharSequence[] items = strings.toArray(new CharSequence[strings.size()]);
            int s = string.toString().length()-1;
            int s5 = stringShowID.toString().length()-1;
            AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(QrcodeActivity.this);
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
        objListView = (ListView) findViewById(R.id.listNameCheck);
        objListView.setAdapter(new ArrayAdapter(this
                , android.R.layout.simple_list_item_1,stringShow));
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
