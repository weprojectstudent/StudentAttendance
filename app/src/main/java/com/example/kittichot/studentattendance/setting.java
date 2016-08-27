package com.example.kittichot.studentattendance;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kittichot.studentattendance.broadcast_receivers.NotificationEventReceiver;
import com.example.kittichot.studentattendance.broadcast_receivers_alert.NotificationEventReceiverAlert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class setting extends ActionBarActivity {
    private Switch Switchsyncro, Switchalert;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String namesp = "setting";
    private TextView txt71, txt72;
    private ImageButton imageButton;
    private TeacherTABLE objteacherTABLE;
    private EditText editUser, editPassword;
    private String strUserChoose, strPasswordChoose, strPasswordTure, strName, strUser;
    private SubjectTABLE objSubjectTABLE;
    private HomeworkTABLE objHomeworkTABLE;
    private TeachdetailTABLE objTeachdetailTABLE;
    private StudentTABLE objStudentTABLE;
    private RegisterTABLE registerTABLE;
    private HomeworksendingTABLE homeworksendingTABLE;
    private AlertTABLE alertTABLE;
    private ChecknamestudentTABLE checknamestudentTABLE;
    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;
    private DateThai objDateThai;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Switchsyncro = (Switch) findViewById(R.id.switch1);
        Switchalert = (Switch) findViewById(R.id.switch2);
        sp = getSharedPreferences(namesp, Context.MODE_PRIVATE);
        objteacherTABLE = new TeacherTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objDateThai = new DateThai();
        registerTABLE = new RegisterTABLE(this);
        homeworksendingTABLE = new HomeworksendingTABLE(this);
        checknamestudentTABLE = new ChecknamestudentTABLE(this);
        alertTABLE = new AlertTABLE(this);
        setSwitch();
        setSharedPreferences();
        settingswitchsyncro();
        settingswitchalert();
        txt71 = (TextView) findViewById(R.id.textView71);
        txt71.setText(objDateThai.timesetAlert(sp.getString("Hour", "")));
        txt72 = (TextView) findViewById(R.id.textView72);
        txt72.setText(objDateThai.timesetMinuteAlert(sp.getString("minute","")));
        imageButton = (ImageButton) findViewById(R.id.imageButton5);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(imageButton);
            }
        });
        button = (Button) findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValue(v);
            }
        });

        //txt71.setText(sp.getInt("Hour",Integer.valueOf("")));
       // txt72.setText(sp.getInt("minute",Integer.valueOf("")));

    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        int callCount = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int h = hourOfDay;
            int m = minute;

            editor.putString( "Hour", String.valueOf(h));
            //txt72.setText(m);
            editor.putString( "minute", String.valueOf(m));
            editor.putString("alert","true");
            editor.apply();
            txt71.setText(objDateThai.timesetAlert(sp.getString("Hour", "")));
            txt72.setText(objDateThai.timesetMinuteAlert(sp.getString("minute","")));
            Switchalert.setChecked(Boolean.parseBoolean(sp.getString("alert","")));
            NotificationEventReceiverAlert.setupAlarmalert(getApplicationContext());
        }
    }

    private void setSharedPreferences() {
        editor = sp.edit();
        editor.putString("alert","true");
        editor.apply();
    }

    private void setSwitch() {
        Switchsyncro.setChecked(Boolean.parseBoolean(sp.getString("syncro","")));
        if (Switchsyncro.isChecked()) {
            Switchsyncro.setText("เปิด");
        } else {
            Switchsyncro.setText("ปิด");
        }
        Switchalert.setChecked(Boolean.parseBoolean(sp.getString("alert","")));
        if (Switchalert.isChecked()) {
            Switchalert.setText("เปิด");
        } else {
            Switchalert.setText("ปิด");
        }
    }


    private void settingswitchsyncro() {
        Switchsyncro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switchsyncro.isChecked()) {
                    editor = sp.edit();
                    editor.putString("syncro","true");
                    editor.apply();
                    Switchsyncro.setText("เปิด");
                    NotificationEventReceiver.setupAlarm(getApplicationContext());
                } else {
                    editor = sp.edit();
                    editor.putString("syncro","false");
                    editor.apply();
                    Switchsyncro.setText("ปิด");
                    NotificationEventReceiver.cancelAlarm(getApplicationContext());
                }


            }
        });
    }

    private void settingswitchalert() {
        Switchalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switchalert.isChecked()) {
                    editor = sp.edit();
                    editor.putString("alert","true");
                    editor.apply();
                    Switchalert.setText("เปิด");
                    NotificationEventReceiverAlert.setupAlarmalert(getApplicationContext());
                } else {
                    editor = sp.edit();
                    editor.putString("alert","false");
                    editor.apply();
                    Switchalert.setText("ปิด");
                    NotificationEventReceiverAlert.cancelAlarmalert(getApplicationContext());
                }
            }
        });

    }

    public void addValue(View view) {
        cd = new ConnectionDetector(setting.this);
        isInternetPresent = cd.isConnectingToInternet();

        // ตรวจสอบสถานะการเชื่อมต่ออินเตอร์เน็ต
        if (isInternetPresent) {
            sysnjsonToSQLite();
            synJsonToStudent();
            synJsonTOHomework();
            synJsonTOsubject();
            synJsonToTerm();
            synJsonToregister();
            synJsonToCheckname();
            synJsonToChecknameHW();
            synJsonToAlert();
        } else {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(this,"ไม่มีการเชื่อมต่ออินเทอร์เน็ต","กรุณาเชื่อมต่ออินเทอร์เน็ต");
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
                long valueSubject = objHomeworkTABLE.addValueToHomeworkUpload(Integer.parseInt(strHomeworkID),strHomeworktitle,
                        strHomeworkdetails, objDateThai.dateThai(strHomeworksavethedate), objDateThai.dateThai(strHomeworkdatesent), intHomeworkStatus);
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

    private void synJsonToregister() {
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
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_register.php");
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
                String strregisterid = objJSONObject.getString("register_id");
                String strtermid = objJSONObject.getString("term_id");
                String strstudent_id = objJSONObject.getString("student_id");
                registerTABLE.addValueRegistersyn(strregisterid, strtermid, Integer.parseInt(strstudent_id),0);
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }//upload Value

    }//synJsonToregister

    private void synJsonToCheckname() {
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
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_checkname.php");
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
                String strcheckname_id = objJSONObject.getString("checkname_id");
                String strregister_id = objJSONObject.getString("register_id");
                String strcheckname_date = objJSONObject.getString("checkname_date");
                String strcheckname_status = objJSONObject.getString("checkname_status");
                checknamestudentTABLE.addValueChecknamesyn(strcheckname_id,Integer.parseInt(strregister_id),strcheckname_date,Integer.parseInt(objDateThai.dateThai(strcheckname_status)));
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }//upload Value

    }//synJsonToCheckname

    private void synJsonToChecknameHW() {
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
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_hw_sending.php");
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
                String strhwsent_id = objJSONObject.getString("hwsent_id");
                String strhomework_id = objJSONObject.getString("homework_id");
                String strregister_id = objJSONObject.getString("register_id");
                String strhwsent_datesent = objJSONObject.getString("hwsent_datesent");
                String strhwsent_status = objJSONObject.getString("hwsent_status");
                homeworksendingTABLE.AddAttendanceHomeworksyn(Integer.parseInt(strhwsent_id),Integer.parseInt(strhomework_id),Integer.parseInt(strregister_id),objDateThai.dateThai(strhwsent_datesent),strhwsent_status);
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }//upload Value

    }//synJsonToCheckname

    private void synJsonToAlert() {
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
            HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_get_data_alert.php");
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
                String stralert_id = objJSONObject.getString("alert_id");
                String stralert_title = objJSONObject.getString("alert_title");
                String stralert_details = objJSONObject.getString("alert_details");
                String stralert_savethedate = objJSONObject.getString("alert_savethedate");
                String stralert_dateofalert = objJSONObject.getString("alert_dateofalert");
                String strteacher_username = objJSONObject.getString("teacher_username");
                String stralert_status = objJSONObject.getString("alert_status");
                alertTABLE.addValueALERTsyn(stralert_id,stralert_title,stralert_details,objDateThai.dateThai(stralert_savethedate),objDateThai.dateThai(stralert_dateofalert),strteacher_username,Integer.parseInt(stralert_status));
            }//for

        } catch (Exception e) {
            Log.d("Subject", "UpDate SQLite  ==>" + e.toString());
        }//upload Value

    }//synJsonToCheckname



}
