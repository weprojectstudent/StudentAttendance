package com.example.kittichot.studentattendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kittichot on 3/31/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "StudentAttendance.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEACHER_TABLE ="create table teacherTABLE(teacher_username text primary key,teacher_password text,teacher_name text,teacher_surname text,teacher_email text);";
    private static final String SUBJECT_TABLE ="create table subjectTABLE(subject_id text primary key,subject_name text,subject_status int);";
    private static final String TEACHDETAIL_TABLE = "create table teachdetailTABLE(_term_id integer primary key,teacher_username text,subject_id text,term_year text,term_status int);";
    private static final String HOMEWORK_TABLE = "create table homeworkTABLE(_homework_id integer primary key,homework_title text,homework_details text,homework_savedate date default CURRENT_DATE,homework_datesent date default CURRENT_DATE,term_id text,homework_status int)";
    private static final String STUDENT_TABLE = "create table studentTABLE(student_id int primary key,student_password text,student_name text,student_surname text,student_classroom text,student_number text,student_status int)";
    private static final String REGISTER_TABLE = "create table registerTABLE(_register_id integer primary key,term_id text,Student_id int,register_status int);";
    private static final String CHECKNAMESTUDENT_TABLE = "create table checknamestudentTABLE(_checkname_id integer primary key,register_id int,checkname_date date default CURRENT_DATE,checkname_status int);";
    private static final String HOMEWORKSENDING_TABLE = "create table homeworksendingTABLE(_hwsent_id integer primary key,homework_id int,register_id int,hwsent_datesent date default CURRENT_DATE,hwsent_status int);";
    private static final String ALERT_TABLE = "create table alertTABLE(_alert_id integer primary key,alert_title text,alert_details text,alert_savethedate date default CURRENT_DATE,alert_dateofalert date default CURRENT_DATE,teacher_username text,hwsent_status int);";

    public MyOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }//constructer

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TEACHER_TABLE);
        db.execSQL(SUBJECT_TABLE);
        db.execSQL(TEACHDETAIL_TABLE);
        db.execSQL(HOMEWORK_TABLE);
        db.execSQL(STUDENT_TABLE);
        db.execSQL(REGISTER_TABLE);
        db.execSQL(CHECKNAMESTUDENT_TABLE);
        db.execSQL(HOMEWORKSENDING_TABLE);
        db.execSQL(ALERT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // onCreate(db);
    }
}//mainclass