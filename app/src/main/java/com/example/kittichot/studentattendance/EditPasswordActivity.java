package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kittichot.studentattendance.testEMAIL.SendMail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class EditPasswordActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView textView;
    private String strRandoms,strUsername;
    private EditText editText,editTextPassSix,edtPass;
    private TeacherTABLE objTeacherTABLE;
    private String strPassChoose, strPasswordChoose, strPasswordTure, strName, strUser,strEmail;
    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        objTeacherTABLE = new TeacherTABLE(this);
        textView = (TextView) findViewById(R.id.txtsendEmail);
        textView.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText6);
        editTextPassSix = (EditText) findViewById(R.id.editText7);
        edtPass = (EditText) findViewById(R.id.edttxtPassNew);
        strUsername = editText.getText().toString().trim();
        cd = new ConnectionDetector(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void  sendEmail(){

        isInternetPresent = cd.isConnectingToInternet();
        strUsername = editText.getText().toString().trim();
        if (strUsername.equals("")){
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(this,"คุณยังไม่ได้กรอก Username","กรุณากรอก Username ");
        } else if (isInternetPresent.equals(false)) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(this,"ไม่มีการเชื่อมต่ออินเทอร์เน็ต!","กรุณาตรวจสอบการเชื่อมต่ออินเทอร์เน็ต! ");
        } else {
        try {
            String strDATA[] = objTeacherTABLE.searchUser(strUsername);
            strUser = strDATA[0];
            strPasswordTure = strDATA[1];
            strName = strDATA[2];
            strEmail = strDATA[4];
            Random r = new Random();
            int r_int = r.nextInt((999999 - 143256) + 1) + 143256;
            strRandoms = String.valueOf(r_int);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            String email = strEmail;
            String subject = "ระบบทำการส่งรหัส 6 หลัก ที่คุณขอในเวลา "+formattedDate;
            String message = "เลข 6 หลัก ของ Username " + strUsername + " คือ " + String.valueOf(strRandoms);
            SendMail sm = new SendMail(this, email, subject, message);
            sm.execute();
        } catch (Exception e) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDiaLog(EditPasswordActivity.this, "ไม่มี Username ", "ไม่มี " + strUsername + " ในฐานข้อมูล");
        }
        }
    }


    @Override
    public void onClick(View v) {
        sendEmail();
    }

    public void clickchangepassword(View view){
        strUsername = editText.getText().toString().trim();
        strPasswordChoose = editTextPassSix.getText().toString().trim();
        strPassChoose = edtPass.getText().toString().trim();
        if (strUsername.equals("")) {
            MyAlertDialog myAlertDialog2 = new MyAlertDialog();
            myAlertDialog2.errorDiaLog(this, "คุณยังไม่ได้กรอก Username", "กรุณากรอก Username");
        }else if (strRandoms== null){
            MyAlertDialog myAlertDialog2 = new MyAlertDialog();
            myAlertDialog2.errorDiaLog(this, "คุณยังไม่ได้ขอรหัส 6 หลัก", "กรุณากดขอรหัส 6 หลัก ระบบจะส่งไปทางอีเมล์");
            textView.findFocus();
        } else if (isInternetPresent.equals(false)) {

            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(this,"ไม่มีการเชื่อมต่ออินเทอร์เน็ต!","กรุณาตรวจสอบการเชื่อมต่ออินเทอร์เน็ต! ");

        } else if (strPasswordChoose.equals(strRandoms)) {
            objTeacherTABLE.updateTeacherPass(strUsername, strPassChoose);
            AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(EditPasswordActivity.this);
            myAlertDialog.setTitle("Change Password" + strUsername);
            myAlertDialog.setIcon(R.drawable.user);
            myAlertDialog.setMessage("ทำการเปลี่ยนรหัสผ่าน เรียบร้อย");
            myAlertDialog.setCancelable(false);
            myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(EditPasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            myAlertDialog.show();
        } else {
            MyAlertDialog myAlertDialog2 = new MyAlertDialog();
            myAlertDialog2.errorDiaLog(this, "รหัสของคุณไมถูกต้อง", "รหัสผ่าน 6 หลัก ของคุณ ไม่ถูกต้อง");
        }



    }

    public void onBackPressed(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setIcon(R.drawable.alerticon);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EditPasswordActivity.this, MainActivity.class);
                startActivity(intent);
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
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(EditPasswordActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
