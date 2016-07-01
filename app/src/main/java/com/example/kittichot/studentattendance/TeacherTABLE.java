package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 3/31/2016.
 */
public class TeacherTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    public  static final String TABLE_Teacher = "teacherTABLE";
    public static final String COLUMN_USER_Teacher = "teacher_username";
    public static final String COLUMN_Password_Teacher = "teacher_password";
    public static final String COLUMN_NAME_Teacher = "teacher_name";
    public static final String COLUMN_SURNAME_Teacher = "teacher_surname";
    public static final String COLUMN_EMAIL_Teacher = "teacher_email";

    public TeacherTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite =objMyOpenHelper.getReadableDatabase();
    }//constructer
    public String[] searchUser(String strUser) {
        try {
            String strData[] = null;
            Cursor objCursor = readSQLite.query(TABLE_Teacher,
                    new String[]{COLUMN_USER_Teacher, COLUMN_Password_Teacher,
                            COLUMN_NAME_Teacher,COLUMN_SURNAME_Teacher,
                            COLUMN_EMAIL_Teacher},COLUMN_USER_Teacher+"=?",new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strData = new String[objCursor.getColumnCount()];
                    strData[0] = objCursor.getString(0);
                    strData[1] = objCursor.getString(1);
                    strData[2] = objCursor.getString(2);
                    strData[3] = objCursor.getString(3);
                    strData[4] = objCursor.getString(4);
                }//if2
            }//if1
            objCursor.close();
            return strData;
        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }//searchUser
    public long addValueToTeacher(String strUsername,String strPassword,String strName,String strSurname,String strEmail){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER_Teacher,strUsername);
        objContentValues.put(COLUMN_Password_Teacher,strPassword);
        objContentValues.put(COLUMN_NAME_Teacher,strName);
        objContentValues.put(COLUMN_SURNAME_Teacher,strSurname);
        objContentValues.put(COLUMN_EMAIL_Teacher,strEmail);
        return writerSQLite.insert(TABLE_Teacher,null,objContentValues);
    }//addValueToTeacher
    public void updateTeacherPass(String strUsername,String strPassword){
        ContentValues objValues = new ContentValues();
        objValues.put(COLUMN_Password_Teacher,strPassword);
        writerSQLite.update(TABLE_Teacher, objValues, COLUMN_USER_Teacher + "=?", new String[]{strUsername});
    }

}//mainclass