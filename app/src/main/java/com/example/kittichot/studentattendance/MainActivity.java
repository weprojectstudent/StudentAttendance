package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kittichot.studentattendance.broadcast_receivers.NotificationEventReceiver;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private TeacherTABLE objteacherTABLE;
    private EditText editUser, editPassword;
    private String strUserChoose, strPasswordChoose, strPasswordTure, strName, strUser;
    private SubjectTABLE objSubjectTABLE;
    private HomeworkTABLE objHomeworkTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private StudentTABLE objStudentTABLE;
    private Button button;
    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;
    private DateThai objDateThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind Widget
        bindWidget();
        objteacherTABLE = new TeacherTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objDateThai = new DateThai();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        addValue();
        exportDatabaseFile(this,"StudentAttendance");
    }//onCreate
    public boolean exportDatabaseFile(Context context, String dbName) {
        try {
            File dbFile = context.getDatabasePath(dbName);
            File exportFile = new File(Environment.getExternalStorageDirectory() + "/" + dbName);
            FileInputStream fileInputStream = new FileInputStream(dbFile);
            FileOutputStream fileOutputStream = new FileOutputStream(exportFile);
            FileChannel fileInputChannel = fileInputStream.getChannel();
            FileChannel fileOutputChannel = fileOutputStream.getChannel();
            fileInputChannel.transferTo(0, fileInputChannel.size(), fileOutputChannel);
            fileInputStream.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addValue() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        // ตรวจสอบสถานะการเชื่อมต่ออินเตอร์เน็ต
        if (isInternetPresent) {
            deleteAll();
            sysnjsonToSQLite();
            synJsonToStudent();
            synJsonTOHomework();
            synJsonTOsubject();
            synJsonToTerm();

        } else {

        }
    }

    private void synJsonToStudent() {

        //Change Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myThreadPolicy);

        }//if
        //inputestream
        InputStream objInputeSteam = null;
        String strJSON = "";

        //Create InputStream
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_student.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputeSteam = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("Subject", "InputStream ==>" + e.toString());
        }
        //create JSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputeSteam, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {

                objStringBuilder.append(strLine);

            }//while
            objInputeSteam.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {

            Log.d("Subject", "CreateJson ==>" + e.toString());
        }

        //Upload Value to SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                int student_ID = objJSONObject.getInt("student_id");
                String student_Password = objJSONObject.getString("student_password");
                String student_Name = objJSONObject.getString("student_name");
                String student_Surname = objJSONObject.getString("student_surname");
                String student_Classroom = objJSONObject.getString("student_classroom");
                String student_number = objJSONObject.getString("student_number");
                //int intHomeworkStatus = objJSONObject.getInt("homework_status");
                long valueStudent = objStudentTABLE.addValueStudent(student_ID,
                        student_Password, student_Name, student_Surname, student_Classroom, student_number/*intHomeworkStatus*/);
            }//for

        } catch (Exception e) {
            Log.d("Student", "UpDate SQLite  ==>" + e.toString());
        }
    }//synJsonToStudent()

    private void deleteAll() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("StudentAttendance.db", MODE_PRIVATE, null);
        //sqLiteDatabase.delete("teacherTABLE", null, null);
       // sqLiteDatabase.delete("subjectTABLE", null, null);
       // sqLiteDatabase.delete("termTABLE", null, null);
        //sqLiteDatabase.delete("homeworkTABLE", null, null);
        sqLiteDatabase.delete("studentTABLE", null, null);

    }

    public void clickLogin() {
        strUserChoose = editUser.getText().toString().trim();
        strPasswordChoose = editPassword.getText().toString().trim();
        if (strUserChoose.equals("") || strPasswordChoose.equals("")) {
            //Alert Error
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDiaLog(MainActivity.this, "คุณกรอก Username และ Password ไม่ครบ", "กรุณากรอก Username และ Password ให้ครบ");
        } else {
            checkUser();
        }//if
    }//clickLogin

    private void checkUser() {

        try {
            String strDATA[] = objteacherTABLE.searchUser(strUserChoose);
            strUser = strDATA[0];
            strPasswordTure = strDATA[1];
            strName = strDATA[2];
            Log.d("StudentAttendance", "Welcome" + strName);
           checkPassword();
        } catch (Exception e) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDiaLog(MainActivity.this, "ไม่มี Username ", "ไม่มี " + strUserChoose + " ในฐานข้อมูล");
        }

    }//checkUser

    private void checkPassword() {
        if (strPasswordChoose.equals(strPasswordTure)) {

            //Intent to Menu Activity
            welcomeUser();

        } else {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDiaLog(MainActivity.this, "Password ไม่ถูกต้อง", "กรุณกรอก Password ให้ถูกต้อง");
        }//ifPass

    }//checkPassword

    private void welcomeUser() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.user);
        objAlert.setTitle("Welcome to Student Attendance");
        objAlert.setMessage("Welcome " + strName + "\n" + " To Student Attendance");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NotificationEventReceiver.setupAlarm(getApplicationContext());
                Intent objIntent = new Intent(MainActivity.this, MenuActivity.class);
                objIntent.putExtra("Name", strName);
                objIntent.putExtra("Username", strUser);
                startActivity(objIntent);
                finish();

            }
        });
        objAlert.show();
    }//welcomeUser

    private void bindWidget() {
        editUser = (EditText) findViewById(R.id.editText);
        editPassword = (EditText) findViewById(R.id.editText2);
    }//Bind Widget

    private void sysnjsonToSQLite() {
        //setUp policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }//if
        InputStream objInputStream = null;
        String strJson = "";

        //Create objInputStream
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();
        } catch (Exception e) {
            Log.d("Teacher", "Error from input Stream ==>" + e.toString());
        }
        //change InputSteam
        try {
            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }//while
            objInputStream.close();
            strJson = objStringBuilder.toString();

        } catch (Exception e) {
            Log.d("Teacher", "Error Create String ==>" + e.toString());
        }
        //Up Value to SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJson);
            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject objJsonObject = objJsonArray.getJSONObject(i);
                String strUsername = objJsonObject.getString("teacher_username");
                String strPassword = objJsonObject.getString("teacher_password");
                String strName = objJsonObject.getString("teacher_name");
                String strSurname = objJsonObject.getString("teacher_surname");
                String strEmail = objJsonObject.getString("teacher_email");

                long insertValue = objteacherTABLE.addValueToTeacher(strUsername, strPassword, strName, strSurname, strEmail);
            }// for
        } catch (Exception e) {
            Log.d("Teacher", "Error Up Value  ==>" + e.toString());
        }
    }//synJsonToSQLite

    public void synJsonTOHomework() {

        //Change Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myThreadPolicy);

        }//if
        //inputestream
        InputStream objInputeSteam = null;
        String strJSON = "";

        //Create InputStream
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_homework.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputeSteam = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("Subject", "InputStream ==>" + e.toString());
        }
        //create JSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputeSteam, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {

                objStringBuilder.append(strLine);

            }//while
            objInputeSteam.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {

            Log.d("Subject", "CreateJson ==>" + e.toString());
        }

        //Upload Value to SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strHomeworkID = objJSONObject.getString("homework_id");
                String strHomeworktitle = objJSONObject.getString("homework_title");
                String strHomeworkdetails = objJSONObject.getString("homework_details");
                String strHomeworksavethedate = objJSONObject.getString("homework_savethedate");
                String strHomeworkdatesent = objJSONObject.getString("homework_datesent");
                int intHomeworkStatus = objJSONObject.getInt("homework_status");
                long valueSubject = objHomeworkTABLE.addValueToHomework(strHomeworktitle,
                        strHomeworkdetails, strHomeworksavethedate, strHomeworkdatesent, intHomeworkStatus);
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }

    }//synJsonTosubject

    public void synJsonTOsubject() {

        //Change Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myThreadPolicy);

        }//if
        //inputestream
        InputStream objInputeSteam = null;
        String strJSON = "";

        //Create InputStream
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_dataSubject.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputeSteam = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("Subject", "InputStream ==>" + e.toString());
        }
        //create JSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputeSteam, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {

                objStringBuilder.append(strLine);

            }//while
            objInputeSteam.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {

            Log.d("Subject", "CreateJson ==>" + e.toString());
        }

        //Upload Value to SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strSubjectID = objJSONObject.getString("subject_id");
                String strSubjectNAME = objJSONObject.getString("subject_name");
                int strSubjectStatus = objJSONObject.getInt("subject_status");
                // SubjectTABLE objSubjectTABLE = new SubjectTABLE(this);
                objSubjectTABLE.addValueToSubject(strSubjectID, strSubjectNAME, strSubjectStatus);
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }

    }//synJsonTosubject

    private void synJsonToTerm() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myThreadPolicy);

        }//if
        //inputestream
        InputStream objInputSteam = null;
        String strJSON = "";

        //Create InputStream
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_dataTerm.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputSteam = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("Term", "InputStream ==>" + e.toString());
        }
        //create JSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputSteam, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {

                objStringBuilder.append(strLine);

            }//while
            objInputSteam.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {

            Log.d("Term", "CreateJson ==>" + e.toString());
        }

        //Upload Value to SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strTermID = objJSONObject.getString("term_id");
                String strTeacherUserName = objJSONObject.getString("username_teacher");
                String strSubjectID_T = objJSONObject.getString("subject_id");
                String strTermYear = objJSONObject.getString("term_year");
                int strTermStatus = objJSONObject.getInt("term_status");
                objTeachdetailTABLE.addValueToTEACHDETAILALL(strTermID, strTeacherUserName, strSubjectID_T, strTermYear, strTermStatus);
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }//upload Value

    }//synJsonToTerm

    private void testAddValue() {
        objteacherTABLE.addValueToTeacher("username", "password", "name", "surname", "email");
        objteacherTABLE.addValueToTeacher("username2", "password2", "name2", "surname2", "email2");
        objSubjectTABLE.addValueToSubject("MACH1", "คณิตศาสตร์", 0);
        objHomeworkTABLE.addValueToHomework("a", "s", "2016-11-15", "2016-11-15", 0);

    }//testAddValue

    public void  sendEmail(View view){
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        // ตรวจสอบสถานะการเชื่อมต่ออินเตอร์เน็ต
        if (isInternetPresent) {
            Intent intent = new Intent(MainActivity.this, EditPasswordActivity.class);
            startActivity(intent);
            finish();
        } else {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(this,"ไม่มีการเชื่อมต่อ อินเทอร์เน็ต","ต้องเชื่อมต่อ อินเทอร์เน็ต!!");
        }

    }


    @Override
    public void onClick(View v) {
       clickLogin();
        /*Calendar calendar = Calendar.getInstance();
       int d = calendar.get(Calendar.DAY_OF_MONTH);
       int m = 1+calendar.get(Calendar.MONTH);
       int y = calendar.get(Calendar.YEAR);
       String getstr = y + "-" + m + "-"+d;
        MyAlertDialog myAlertDialog = new MyAlertDialog();
        myAlertDialog.errorDiaLog(this,"ไม่มีการเชื่อมต่อ อินเทอร์เน็ต",objDateThai.dateThaiSelect(getstr));*/
    }


}
