package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 9/4/2559.
 */
public class TermTABLE {
    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;
    public static final String TERM_TABLE = "termTABLE";
    public static final String COLUMN_TERM_ID = "term_id";
    public static final String COLUMN_TEACHER_USERNAME_T = "teacher_username";
    public static final String COLUMN_SUBJECT_ID_T = "subject_id";
    public static final String COLUMN_TERM_YEAR = "term_year";
    public static final String COLUMN_TERM_STATUS = "term_status";

    public TermTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }//constructor



    /*public String[] listnameterm() {
        String listTname[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE, new String[]{COLUMN_SUBJECT_ID_T,
                COLUMN_TEACHER_USERNAME_T, COLUMN_SUBJECT_ID_T, COLUMN_TERM_YEAR},
                null, null, null, null, null);
        objCursor.moveToFirst();
        listTname = new String[objCursor.getCount()];
        for (int i=0;i< objCursor.getCount();i++) {
            listTname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();

            return listTname;
    }//listname*/
    public String[] listSubjectID() {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TERM_STATUS+"=?",new String[]{"0"}, null, null, null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectID

    public String[] listTermID() {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TERM_STATUS+"=?",new String[]{"0"}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm
    public String[] listTermyear() {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},COLUMN_TERM_STATUS+"=?",new String[]{"0"}, null, null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTerm
     public String[] listSubjectIDbackup() {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},null, null, null, null,null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectIDbackup

    public String[] listTermIDbackup() {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                null,null, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTermbackup
    public String[] listTermyearbackup() {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},null,null, null,null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTermbackup
    public String[] listTermstatusbackup() {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},null,null, null,null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_STATUS));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTermstatusbackup
    public String[] listTermTeacherbackup() {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,COLUMN_TEACHER_USERNAME_T,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},null,null, null,null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TEACHER_USERNAME_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTermTeacherbackup
    //Hide
    public String[] listSubjectIDHide() {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TERM_STATUS+"=?",new String[]{"1"}, null, null, null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectID

    public String[] listTermIDHide() {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TERM_STATUS+"=?",new String[]{"1"}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm
    public String[] listTermyearHide() {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TERM_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},COLUMN_TERM_STATUS+"=?",new String[]{"1"}, null, null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTerm
    public void  addValueToTerm(String strID,String strTeacherUser,String strSubjectid ,String strTermYear,int intstatus) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_TERM_ID, strID);
        objContentValues.put(COLUMN_TEACHER_USERNAME_T,strTeacherUser);
        objContentValues.put(COLUMN_SUBJECT_ID_T,strSubjectid);
        objContentValues.put(COLUMN_TERM_YEAR,strTermYear);
        objContentValues.put(COLUMN_TERM_STATUS,intstatus);
         writeSQLite.insert(TERM_TABLE,null,objContentValues);
    }//addValueToSubject

    public void updateValueToTerm(String strupID, String strupName, String strupSubjectID,String strupTermYear,int strupStatus) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TERM_ID,strupID);
        values.put(COLUMN_TEACHER_USERNAME_T, strupName);
        values.put(COLUMN_SUBJECT_ID_T,strupSubjectID);
        values.put(COLUMN_TERM_YEAR,strupTermYear);
        values.put(COLUMN_TERM_STATUS,strupStatus);
        writeSQLite.update(TERM_TABLE, values, COLUMN_TERM_ID + "=?", new String[]{strupID});
        writeSQLite.close();

    }
}

