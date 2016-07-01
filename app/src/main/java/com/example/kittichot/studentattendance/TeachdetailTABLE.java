package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 23/6/2559.
 */
public class TeachdetailTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;
    public static final String TEACHDETAILTABLE_TABLE = "teachdetailTABLE";
    public static final String COLUMN_TERM_ID = "_term_id";
    public static final String COLUMN_TEACHER_USERNAME_T = "teacher_username";
    public static final String COLUMN_SUBJECT_ID_T = "subject_id";
    public static final String COLUMN_TERM_YEAR = "term_year";
    public static final String COLUMN_TERM_STATUS = "term_status";

    public TeachdetailTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }//constructor

    public String[] listSubjectID(String id,String year) {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"0",year}, null, null, null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectID
    public String[] listSubjectIDTERM(String id) {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TERM_ID+"=?"+" AND "+COLUMN_TERM_STATUS+"=?",new String[]{id,"0"}, null, null, null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectID
    public String[] listTermID(String id,String year) {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"0",year}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm
    public String[] listTermIDslect(String id,String year) {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"0",year}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm

    public String[] listSubjectIDslect(String id,String year) {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_STATUS},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"0",year}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm

    public String[] listTermyear(String id,String year) {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"0",year}, null, null, null);
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
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
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
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
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
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
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
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
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
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
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
    public String[] listSubjectIDHide(String id,String year) {
        String strlistSubjectID[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{
                        COLUMN_SUBJECT_ID_T},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"1",year}, null, null, null);
        objCursor.moveToFirst();
        strlistSubjectID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistSubjectID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSubjectID;
    }//listSubjectID

    public String[] listTermIDHide(String id,String year) {
        String strlistterm[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID},
                COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"1",year}, null, null, null);
        objCursor.moveToFirst();
        strlistterm = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistterm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_ID));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistterm;
    }//listTerm
    public String[] listTermyearHide(String id,String year) {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_YEAR},COLUMN_TEACHER_USERNAME_T+"=?"+" AND "+COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_YEAR+"=?",new String[]{id,"1",year}, null, null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTerm

    public String[] listTERMYearselect(String user) {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(true,TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_YEAR},COLUMN_TEACHER_USERNAME_T+"=?" ,new String[]{user}, null, null, null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERM_YEAR));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTerm
    public String[] listIDSubjectforregis(String id) {
        String strlisttermyear[] = null;
        Cursor objCursor = readSQLite.query(TEACHDETAILTABLE_TABLE,
                new String[]{COLUMN_TERM_ID,
                        COLUMN_SUBJECT_ID_T,COLUMN_TERM_YEAR,COLUMN_TERM_STATUS},COLUMN_TERM_STATUS+"=?"+" AND "+COLUMN_TERM_ID+"=?" ,new String[]{"0",id}, null, null, null);
        objCursor.moveToFirst();
        strlisttermyear = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlisttermyear[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID_T));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlisttermyear;
    }//listTerm
    public void  addValueToTEACHDETAIL(String strTeacherUser,String strSubjectid ,String strTermYear,int intstatus) {
        ContentValues objContentValues = new ContentValues();
        //objContentValues.put(COLUMN_TERM_ID, strID);
        objContentValues.put(COLUMN_TEACHER_USERNAME_T,strTeacherUser);
        objContentValues.put(COLUMN_SUBJECT_ID_T,strSubjectid);
        objContentValues.put(COLUMN_TERM_YEAR,strTermYear);
        objContentValues.put(COLUMN_TERM_STATUS,intstatus);
        writeSQLite.insert(TEACHDETAILTABLE_TABLE,null,objContentValues);
    }//addValueToSubject
    public void  addValueToTEACHDETAILALL(String ID,String strTeacherUser,String strSubjectid ,String strTermYear,int intstatus) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_TERM_ID, ID);
        objContentValues.put(COLUMN_TEACHER_USERNAME_T,strTeacherUser);
        objContentValues.put(COLUMN_SUBJECT_ID_T,strSubjectid);
        objContentValues.put(COLUMN_TERM_YEAR,strTermYear);
        objContentValues.put(COLUMN_TERM_STATUS,intstatus);
        writeSQLite.insert(TEACHDETAILTABLE_TABLE,null,objContentValues);
    }//addValueToSubject
    public void updateValueToTEACHDETAIL(String strupID, String strupName, String strupSubjectID,String strupTermYear,int strupStatus) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TERM_ID,strupID);
        values.put(COLUMN_TEACHER_USERNAME_T, strupName);
        values.put(COLUMN_SUBJECT_ID_T,strupSubjectID);
        values.put(COLUMN_TERM_YEAR,strupTermYear);
        values.put(COLUMN_TERM_STATUS,strupStatus);
        writeSQLite.update(TEACHDETAILTABLE_TABLE, values, COLUMN_TERM_ID + "=?", new String[]{strupID});
        writeSQLite.close();

    }



}
