package com.example.kittichot.studentattendance;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class TestActivity extends ActionBarActivity {
    private TermTABLE objTermTABLE;
    private SubjectTABLE objSubjectTABLE ;
    private HomeworkTABLE objHomeworkTABLE;
    private StudentTABLE objStudentTABLE;
    private String[] strTermID,strsubjectID, strTermYear,strTermStatus,strTeacherTerm;//term
    private String[] strListIDsubject, strListNamesubject,strStatussubject;//subject
    private String[] strListIDHW,strListTitelHW,strDetailHW,strDatesaveHW,strDatesentHW,strStatusHW,strTest;//homework
    private int getnumterm,getnumsubject,getnumHW;
    private String strgetID, strgetSubject,strgetTerm,strIDteacher,strStatus;//term
    private String strgetIDsubject, strgetNamesubject,strgetStatussubject;//subject
    private String getstrListIDHW,getstrListTitelHW,getstrDetailHW,getstrDatesaveHW,getstrDatesentHW,getstrStatusHW;//homework
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        objTermTABLE = new TermTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        setArray();
        threadPolicy();
    }

    private void threadPolicy() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }
    }

    private void setArray() {
        //term
        strTermID = objTermTABLE.listTermIDbackup();
        strsubjectID = objTermTABLE.listSubjectIDbackup();
        strTermYear = objTermTABLE.listTermyearbackup();
        strTermStatus = objTermTABLE.listTermstatusbackup();
        strTeacherTerm = objTermTABLE.listTermTeacherbackup();
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
        //homework
        /*strTest = objStudentTABLE.ListTest();
        for (int i = 0;i<strTest.length;i++) {
            String s = strTest[i];
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDiaLog(TestActivity.this, strTest[i],s);
        }*/
    }
    public void clickTestValue(View view) {

        upSQLSubject();



    }//clickTestValueTerm

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
    }//upSQLHomework

    private void upSQLSubject() {
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
