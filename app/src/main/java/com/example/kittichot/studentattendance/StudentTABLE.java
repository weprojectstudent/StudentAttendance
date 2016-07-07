package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 26/5/2559.
 */
public class StudentTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private static final String STUDENT_TABLE = "studentTABLE";
    private static final String COLUMN_ID_STUDENT = "student_id";
    private static final String COLUMN_PASSWORD_STUDENT = "student_password";
    private static final String COLUMN_NAME_STUDENT = "student_name";
    private static final String COLUMN_SURNAME_STUDENT = "student_surname";
    private static final String COLUMN_CLASSROOM_STUDENT = "student_classroom";
    private static final String COLUMN_NUMBER_STUDENT = "student_number";
    private static final String COLUMN_STATUS_STUDENT = "student_status";
    private static final String AND = " AND ";

    public StudentTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//constructer

    public String[] RoomList() {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(true, STUDENT_TABLE, new String[]{COLUMN_CLASSROOM_STUDENT},
                null, null, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSROOM_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomList

    public String[] ListIDStudent(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_ID_STUDENT},
                COLUMN_CLASSROOM_STUDENT + "=?", new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomList


    public String[] ListNameStudent(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_NAME_STUDENT},
                COLUMN_ID_STUDENT + "=?", new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//Namestudent

    public String[] ListSurNameStudent(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_SURNAME_STUDENT},
                COLUMN_ID_STUDENT + "=?", new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomList

    public String[] ListTest() {
        String s = "ม.3/4";
        String b = "3";
        String listtest[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_ID_STUDENT},
                COLUMN_CLASSROOM_STUDENT + "=?"+AND+COLUMN_NUMBER_STUDENT+"=?", new String[]{s,b}, null, null, null, null);
        /*Cursor cursor = writerSQLite.rawQuery("SELECT * FROM " + STUDENT_TABLE
                + " WHERE " + COLUMN_CLASSROOM_STUDENT + "='ม.3/4'"
                + " AND " + COLUMN_NUMBER_STUDENT + "=3", null);*/
        cursor.moveToFirst();
        listtest = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listtest[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_STUDENT));
            cursor.moveToNext();


        }//for
        return listtest;
    }
    public String[] RoomListIDREGIS(String strings) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(true, STUDENT_TABLE, new String[]{COLUMN_CLASSROOM_STUDENT},
                COLUMN_ID_STUDENT,new String[]{strings}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSROOM_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS

    public String[] ListIDStudentIDREGIS(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_ID_STUDENT},
                COLUMN_ID_STUDENT,new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS

    public String[] ListNameStudentIDREGIS(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_NAME_STUDENT},
                COLUMN_ID_STUDENT + "=?",new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//NamestudentIDREGIS

    public String[] ListSurNameStudentIDREGIS(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_SURNAME_STUDENT},
                COLUMN_ID_STUDENT + "=?",new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS

    public String[] ListClassRoomStudentIDREGIS(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_CLASSROOM_STUDENT},
                COLUMN_ID_STUDENT + "=?",new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSROOM_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS
    public String[] ListClassRoomStudent(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(true,STUDENT_TABLE, new String[]{COLUMN_CLASSROOM_STUDENT},
                COLUMN_ID_STUDENT + "=?",new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSROOM_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS

    public String[] ListNoStudentIDREGIS(String s) {

        String listroom[] = null;
        Cursor cursor = readSQLite.query(STUDENT_TABLE, new String[]{COLUMN_NUMBER_STUDENT},
                COLUMN_ID_STUDENT + "=?",new String[]{s}, null, null, null, null);
        cursor.moveToFirst();
        listroom = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            listroom[i] = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_STUDENT));
            cursor.moveToNext();
        }//for
        cursor.close();

        return listroom;
    }//RoomListIDREGIS

    public long addValueStudent(int ID, String Password, String Name, String Surname, String Classroom, String Number /*,int Status*/) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_STUDENT, ID);
        objContentValues.put(COLUMN_PASSWORD_STUDENT, Password);
        objContentValues.put(COLUMN_NAME_STUDENT, Name);
        objContentValues.put(COLUMN_SURNAME_STUDENT, Surname);
        objContentValues.put(COLUMN_CLASSROOM_STUDENT, Classroom);
        objContentValues.put(COLUMN_NUMBER_STUDENT, Number);
        //objContentValues.put(COLUMN_STATUS_STUDENT,Status);
        return writerSQLite.insert(STUDENT_TABLE, null, objContentValues);
    } // addValueStudent()

    public long updateValueStudent(int ID, String Password, String Name, String Surname, String Classroom, String Number, int Status) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_STUDENT, ID);
        objContentValues.put(COLUMN_PASSWORD_STUDENT, Password);
        objContentValues.put(COLUMN_NAME_STUDENT, Name);
        objContentValues.put(COLUMN_SURNAME_STUDENT, Surname);
        objContentValues.put(COLUMN_CLASSROOM_STUDENT, Classroom);
        objContentValues.put(COLUMN_NUMBER_STUDENT, Number);
        objContentValues.put(COLUMN_STATUS_STUDENT, Status);
        return writerSQLite.update(STUDENT_TABLE, objContentValues, COLUMN_ID_STUDENT + "=?", new String[]{String.valueOf(ID)});
    } //updateValueStudent

}//MainClass
