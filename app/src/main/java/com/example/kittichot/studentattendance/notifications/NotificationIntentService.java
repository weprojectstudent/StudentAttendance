package com.example.kittichot.studentattendance.notifications;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.kittichot.studentattendance.AlertTABLE;
import com.example.kittichot.studentattendance.ChecknamestudentTABLE;
import com.example.kittichot.studentattendance.ConnectionDetector;
import com.example.kittichot.studentattendance.HomeworkTABLE;
import com.example.kittichot.studentattendance.HomeworksendingTABLE;
import com.example.kittichot.studentattendance.R;
import com.example.kittichot.studentattendance.RegisterTABLE;
import com.example.kittichot.studentattendance.StudentTABLE;
import com.example.kittichot.studentattendance.SubjectTABLE;
import com.example.kittichot.studentattendance.TeachdetailTABLE;
import com.example.kittichot.studentattendance.broadcast_receivers.NotificationEventReceiver;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by klogi
 *
 *
 */
public class NotificationIntentService extends IntentService {
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE ;
    private HomeworkTABLE objHomeworkTABLE;
    private StudentTABLE objStudentTABLE;
    private AlertTABLE objAlertTABLE;
    private RegisterTABLE objRegisterTABLE;
    private ChecknamestudentTABLE objChecknamestudentTABLE;
    private HomeworksendingTABLE objHomeworksendingTABLE;
    private String[] strTermID,strsubjectID, strTermYear,strTermStatus,strTeacherTerm;//term
    private String[] strHWSDID,strHWSDHWID, strHWSDRegisID,strHWSDdatesend,strHWSDStatus;//Homeworksending
    private String[] strChecknameID,strChecknameIDREGIS, strChecknameDATE,strChecknameStatus;//Checkname
    private String[] strRegisID, strRegisTermID,strRegisIDStudent,strRegisStatus;//Checkname
    private String[] strListIDsubject, strListNamesubject,strStatussubject;//subject
    private String[] strListIDHW,strListTitelHW,strDetailHW,strDatesaveHW,strDatesentHW,strStatusHW,strTest;//homework
    private String[] strIDAlert,strTitleAlert,strDetailAlert,strDATEsaveAlert,strDateAlert,strTeacherAlert,strStatusAlert;//Alert
    private int getnumterm,getnumsubject,getnumHW,getnumAlert,getnumcheckname,getnumRegis,getnumHomeworkSending,getsum;
    private String strgetID, strgetSubject,strgetTerm,strIDteacher,strStatus;//term
    private String strgetHWSDID,strgetHWSDHWID, strgetHWSDRegisID,strgetHWSDdatesend,strgetHWSDStatus;//Homeworksending
    private String strgetChecknameID, strgetChecknameIDREGIS,strgetChecknameDATE,strgetChecknameStatus;//Checkname
    private String strgetRegisID, strgetRegisTermID,strgetRegisIDStudent,strgetRegisStatus;//REGIS
    private String strgetIDsubject, strgetNamesubject,strgetStatussubject;//subject
    private String getstrListIDHW,getstrListTitelHW,getstrDetailHW,getstrDatesaveHW,getstrDatesentHW,getstrStatusHW;//homework
    private String getIDAlert,getTitleAlert,getDetailAlert,getDATEsaveAlert,getDateAlert, getTeacherAlert,getStatusAlert;//Alert

    //syn ข้อมูล
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    final int id = 12;

    // เรียกใช้งาน  ConnectionDetector
    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        // ตรวจสอบสถานะการเชื่อมต่ออินเตอร์เน็ต
        if (isInternetPresent) {

            threadPolicy();
            setArray();
            upSQLSubject();
            showDialog();

        } else {
            // หากไม่ได้เชื่อมต่ออินเตอร์เน็ต

        }
    }

    private void showDialog() {
        getnumHomeworkSending = strHWSDID.length;
        getnumcheckname = strChecknameID.length;
        getnumRegis = strRegisID.length;
        getnumHW = strListIDHW.length;
        getnumAlert = strIDAlert.length;
        getnumsubject = strListIDsubject.length;
       getnumterm = strTermID.length;
        getsum = getnumcheckname + getnumRegis + getnumHW + getnumAlert + getnumsubject + getnumterm+getnumHomeworkSending;
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Student Attendance synchronize")
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setTicker("มีการ synchronize ไปยัง server ")
                .setContentText("ทำการ synchronize data ไปยัง server")
                .setSmallIcon(R.drawable.notification_icon);
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =1;i<=getsum;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    builder.setProgress(getsum, i, false);
                    manager.notify(id,builder.build());
                }
                builder.setContentText("synchronize data เสร็จสิ้น");
                builder.setProgress(0, 0, false);
                manager.notify(id,builder.build());
            }
        }).start();
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Title
        alertDialog.setTitle(title);

        // Message
        alertDialog.setMessage(message);

        // OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Show Alert Message
        alertDialog.show();
    }
    private void threadPolicy() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }
    }

    private void setArray() {
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objAlertTABLE = new AlertTABLE(this);
        objRegisterTABLE = new RegisterTABLE(this);
        objChecknamestudentTABLE = new ChecknamestudentTABLE(this);
        objHomeworksendingTABLE = new HomeworksendingTABLE(this);
        //term
        strTermID = objTeachdetailTABLE.listTermIDbackup();
        strsubjectID = objTeachdetailTABLE.listSubjectIDbackup();
        strTermYear = objTeachdetailTABLE.listTermyearbackup();
        strTermStatus = objTeachdetailTABLE.listTermstatusbackup();
        strTeacherTerm = objTeachdetailTABLE.listTermTeacherbackup();
        //term
        //subject
        strListIDsubject =objSubjectTABLE.listSubjectbackup();
        strListNamesubject = objSubjectTABLE.listSNamebackup();
        strStatussubject = objSubjectTABLE.listSubjectStatusbackup();
        //subject
        //homework
        strListIDHW = objHomeworkTABLE.listIDHWbackup();
        strListTitelHW = objHomeworkTABLE.listTitleHWbackup();
        strDetailHW = objHomeworkTABLE.listDetailHWbackup();
        strDatesaveHW = objHomeworkTABLE.listDatesaveHWbackup();
        strDatesentHW = objHomeworkTABLE.listDatesentHWbackup();
        strStatusHW = objHomeworkTABLE.listStatusHWbackup();
        //Alert
        strIDAlert = objAlertTABLE.listIDSYN();
        strTitleAlert= objAlertTABLE.listTITLESYN();
        strDetailAlert= objAlertTABLE.listDetailSYN();
        strDATEsaveAlert= objAlertTABLE.listDateSaveSYN();
        strDateAlert= objAlertTABLE.listDatealertSYN();
        strTeacherAlert= objAlertTABLE.listTEACHERSYN();
        strStatusAlert= objAlertTABLE.listStatusSYN();
        // /Alert
        //checkname
        strChecknameID = objChecknamestudentTABLE.ChecknameID();
        strChecknameIDREGIS = objChecknamestudentTABLE.ChecknameIDREGIS();
        strChecknameDATE = objChecknamestudentTABLE.ChecknameDATE();
        strChecknameStatus = objChecknamestudentTABLE.ChecknameSTATUS();
        //checkname
        //REGIS
        strRegisID = objRegisterTABLE.ListRegisIDFULL();
        strRegisTermID = objRegisterTABLE.ListRegisIDTermFULL();
        strRegisIDStudent = objRegisterTABLE.ListRegisIDStudentFULL();
        strRegisStatus = objRegisterTABLE.ListRegisStatusFULL();
        //REGIS
        //Homeworksending
        strHWSDID = objHomeworksendingTABLE.CheckHWSDIDFull();
        strHWSDHWID = objHomeworksendingTABLE.CheckHWIDFull();
        strHWSDRegisID = objHomeworksendingTABLE.CheckHWSRegisIDFull();
        strHWSDdatesend = objHomeworksendingTABLE.CheckHWSDateSendFull();
        strHWSDStatus = objHomeworksendingTABLE.CheckHWSStatusFull();
        //Homeworksending
    }
    private void upSQLHomework() {

        getnumHW = strListIDHW.length;
        for (int i = 0; i < getnumHW; i++) {
            getstrListIDHW = strListIDHW[i];
            getstrListTitelHW = strListTitelHW[i];
            getstrDetailHW = strDetailHW[i];
            getstrDatesaveHW = strDatesaveHW[i];
            getstrDatesentHW = strDatesentHW[i];
            getstrStatusHW = strStatusHW[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("HW_ID",getstrListIDHW ));
                objNameValuePairs.add(new BasicNameValuePair("HW_Title",getstrListTitelHW ));
                objNameValuePairs.add(new BasicNameValuePair("HW_Detail", getstrDetailHW  ));
                objNameValuePairs.add(new BasicNameValuePair("HW_Datesave",getstrDatesaveHW ));
                objNameValuePairs.add(new BasicNameValuePair("HW_Datesent",getstrDatesentHW ));
                objNameValuePairs.add(new BasicNameValuePair("HW_Status",getstrStatusHW ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_homework.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);
            } catch (Exception e) {
                Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
            }
        }//for
        upSQLAlert();
    }//upSQLHomework

    private void upSQLAlert() {

        getnumAlert = strIDAlert.length;
        for (int i = 0;i<getnumAlert; i++){
            getIDAlert=strIDAlert[i];
            getTitleAlert=strTitleAlert[i];
            getDetailAlert=strDetailAlert[i];
            getDATEsaveAlert=strDATEsaveAlert[i];
            getDateAlert=strDateAlert[i];
            getTeacherAlert=strTeacherAlert[i];
            getStatusAlert=strStatusAlert[i];//Alert
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("alert_id",getIDAlert ));
                objNameValuePairs.add(new BasicNameValuePair("alert_title",getTitleAlert ));
                objNameValuePairs.add(new BasicNameValuePair("alert_details", getDetailAlert  ));
                objNameValuePairs.add(new BasicNameValuePair("alert_savethedate",getDATEsaveAlert ));
                objNameValuePairs.add(new BasicNameValuePair("alert_dateofalert",getDateAlert ));
                objNameValuePairs.add(new BasicNameValuePair("teacher_username",getTeacherAlert ));
                objNameValuePairs.add(new BasicNameValuePair("alert_status",getStatusAlert ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_Alert.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);
            } catch (Exception e) {
                Log.d("Alert", "UPDATE MY SQL ==>" + e.toString());
            }
        }//for

        upSQLRegis();

    }

    private void upSQLRegis() {
        getnumRegis = strRegisID.length;

        for (int i = 0;i<getnumRegis; i++){
            strgetRegisID=strRegisID[i];
            strgetRegisTermID =strRegisTermID[i];
            strgetRegisIDStudent=strRegisIDStudent[i];
            strgetRegisStatus=strRegisStatus[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("register_id",strgetRegisID ));
                objNameValuePairs.add(new BasicNameValuePair("term_id",strgetRegisTermID ));
                objNameValuePairs.add(new BasicNameValuePair("Student_id", strgetRegisIDStudent  ));
                objNameValuePairs.add(new BasicNameValuePair("register_status",strgetRegisStatus ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_regis.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);
            } catch (Exception e) {
                Log.d("Alert", "UPDATE MY SQL ==>" + e.toString());
            }
        }//for
        upSQLCheckname();
    }

    private void upSQLCheckname() {
        getnumcheckname = strChecknameID.length;

        for (int i = 0;i<getnumcheckname; i++){
            strgetChecknameID=strChecknameID[i];
            strgetChecknameIDREGIS =strChecknameIDREGIS[i];
            strgetChecknameDATE=strChecknameDATE[i];
            strgetChecknameStatus=strChecknameStatus[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("checkname_id",strgetChecknameID ));
                objNameValuePairs.add(new BasicNameValuePair("register_id",strgetChecknameIDREGIS ));
                objNameValuePairs.add(new BasicNameValuePair("checkname_date", strgetChecknameDATE  ));
                objNameValuePairs.add(new BasicNameValuePair("checkname_status",strgetChecknameStatus ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_checkname.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);
            } catch (Exception e) {
                Log.d("Alert", "UPDATE MY SQL ==>" + e.toString());
            }
        }//for
        upSQLHomeworksending();
    }

    private void upSQLHomeworksending() {
        getnumHomeworkSending = strHWSDID.length;
        for (int i =0; i<getnumHomeworkSending;i++) {
            strgetHWSDID = strHWSDID[i];
            strgetHWSDHWID = strHWSDHWID[i];
            strgetHWSDRegisID = strHWSDRegisID[i];
            strgetHWSDdatesend = strHWSDdatesend[i];
            strgetHWSDStatus = strHWSDStatus[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("hwsent_id",strgetHWSDID ));
                objNameValuePairs.add(new BasicNameValuePair("homework_id",strgetHWSDHWID ));
                objNameValuePairs.add(new BasicNameValuePair("register_id", strgetHWSDRegisID  ));
                objNameValuePairs.add(new BasicNameValuePair("hwsent_datesent",strgetHWSDdatesend ));
                objNameValuePairs.add(new BasicNameValuePair("hwsent_status",strgetHWSDStatus ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_homeworksending.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);
            } catch (Exception e) {
                Log.d("Alert", "UPDATE MY SQL ==>" + e.toString());
            }
        }//for
        }

    private void upSQLSubject() {
        getnumcheckname = strChecknameID.length;
        getnumRegis = strRegisID.length;
        getnumHW = strListIDHW.length;
        getnumAlert = strIDAlert.length;
        getnumsubject = strListIDsubject.length;
        for (int i = 0; i < getnumsubject; i++) {
            strgetIDsubject = strListIDsubject[i];
            strgetNamesubject = strListNamesubject[i];
            strgetStatussubject = strStatussubject[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("subject_ids",strgetIDsubject ));
                objNameValuePairs.add(new BasicNameValuePair("subject_names",strgetNamesubject));
                objNameValuePairs.add(new BasicNameValuePair("subject_statuss",strgetStatussubject ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_subject.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);

            } catch (Exception e) {
                Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
            }

        }//for

        upSQLTERM();
    }

    private void upSQLTERM() {

        getnumterm = strTermID.length;
        for (int i = 0; i < getnumterm; i++) {
            strgetID = strTermID[i];
            strIDteacher = strTeacherTerm[i];
            strgetSubject = strsubjectID[i];
            strgetTerm = strTermYear[i];
            strStatus = strTermStatus[i];
            try {
                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
                objNameValuePairs.add(new BasicNameValuePair("term_id",strgetID ));
                objNameValuePairs.add(new BasicNameValuePair("teacher_user",strIDteacher));
                objNameValuePairs.add(new BasicNameValuePair("subject_name",strgetSubject ));
                objNameValuePairs.add(new BasicNameValuePair("term_year",strgetTerm ));
                objNameValuePairs.add(new BasicNameValuePair("status",strStatus ));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://we-projectstudent.com/StudentAttendance/php_add_update_data_term.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);

            } catch (Exception e) {
                Log.d("AddSubject", "UPDATE MY SQL ==>" + e.toString());
            }

        }//forTerm

        upSQLHomework();
    }

    }

