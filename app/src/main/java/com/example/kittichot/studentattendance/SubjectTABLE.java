package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 4/2/2016.
 */
public class SubjectTABLE {
    //Expicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite,readSqLite;
    public static String SUBJECT_TABLE = "subjectTABLE";
    public static String COLUMN_SUBJECT_ID ="subject_id";
    public static String COLUMN_SUBJECT_NAME = "subject_name";
    public static String COLUMN_SUBJECT_STATUS = "subject_status";

    public SubjectTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSqLite = objMyOpenHelper.getReadableDatabase();
    }//constructor

    public String[] listSName() {
        String strlistSname[] = null;

        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},
                COLUMN_SUBJECT_STATUS+"=?",new String[]{"0"},null,null,COLUMN_SUBJECT_ID+" DESC");
        objCursor.moveToFirst();
        strlistSname = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strlistSname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_NAME));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSname;
    }//listSName
    public String[] listName(String id) {
        String strlistSname[] = null;

        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},
                COLUMN_SUBJECT_STATUS+"=?"+" AND "+COLUMN_SUBJECT_ID+"=?",new String[]{"0",id},null,null,null);
        objCursor.moveToFirst();
        strlistSname = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strlistSname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_NAME));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSname;
    }//listSName


   public String[] listSubject() {

        String strLisSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_STATUS,COLUMN_SUBJECT_ID,COLUMN_SUBJECT_NAME},
                COLUMN_SUBJECT_STATUS+"=?",new String[]{"0"},null,null,COLUMN_SUBJECT_ID+" DESC");
        objCursor.moveToFirst();
        strLisSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

                strLisSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID));
                objCursor.moveToNext();

        }//for
        objCursor.close();
            return strLisSubject;
    }//listSubject

    public String[] listSNamebackup() {
        String strlistSname[] = null;

        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},null,null,null,null,null);
        objCursor.moveToFirst();
        strlistSname = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strlistSname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_NAME));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSname;
    }//listSNamebackup
   public String[] listSubjectbackup() {

        String strLisSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_STATUS,COLUMN_SUBJECT_ID,COLUMN_SUBJECT_NAME},null,null,null,null,null);
        objCursor.moveToFirst();
        strLisSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

                strLisSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID));
                objCursor.moveToNext();

        }//for
        objCursor.close();
            return strLisSubject;
    }//listSubjectbackup

    public String[] listSubjectStatusbackup() {

        String strLisSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_STATUS,COLUMN_SUBJECT_ID,COLUMN_SUBJECT_NAME},null,null,null,null,null);
        objCursor.moveToFirst();
        strLisSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strLisSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_STATUS));
            objCursor.moveToNext();

        }//for
        objCursor.close();
        return strLisSubject;
    }//listSubjectStatusbackup

    public String[] listSNameSearch(String strings) {
        String strlistSname[] = null;

        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},
                COLUMN_SUBJECT_ID+"=?",new String[]{String.valueOf(strings)},null,null,null);
        objCursor.moveToFirst();
        strlistSname = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strlistSname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_NAME));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistSname;
    }//listSName
    public String[] listSubjectSearch(String strings) {

        String strLisSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},
                COLUMN_SUBJECT_ID+"=?",new String[]{String.valueOf(strings)},null,null,null);
        objCursor.moveToFirst();
        strLisSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strLisSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID));
            objCursor.moveToNext();

        }//for
        objCursor.close();
        return strLisSubject;
    }

    public String[] listHideSName() {
        String strlistHideSname[] = null;

        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME,COLUMN_SUBJECT_STATUS},
                COLUMN_SUBJECT_STATUS+"=?",new String[]{"1"},null,null,null);
        objCursor.moveToFirst();
        strlistHideSname = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strlistHideSname[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_NAME));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strlistHideSname;
    }//listSName
    public String[] listHideSubject() {

        String strListHindSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_STATUS,COLUMN_SUBJECT_ID,COLUMN_SUBJECT_NAME},
                COLUMN_SUBJECT_STATUS+"=?",new String[]{"1"},null,null,null);
        objCursor.moveToFirst();
        strListHindSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strListHindSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID));
            objCursor.moveToNext();

        }//for
        objCursor.close();
        return strListHindSubject;
    }
    public String[] iDSubjectloop(String id) {

        String strListHindSubject[] = null;
        Cursor objCursor = readSqLite.query(SUBJECT_TABLE,
                new String[]{COLUMN_SUBJECT_STATUS,COLUMN_SUBJECT_ID,COLUMN_SUBJECT_NAME},
                COLUMN_SUBJECT_ID+"=?",new String[]{id},null,null,null);
        objCursor.moveToFirst();
        strListHindSubject = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {

            strListHindSubject[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SUBJECT_ID));
            objCursor.moveToNext();

        }//for
        objCursor.close();
        return strListHindSubject;
    }

    //addValue subjectTable
    public void addValueToSubject(String strID,String strNAME,int intStatus) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_SUBJECT_ID,strID);
        objContentValues.put(COLUMN_SUBJECT_NAME,strNAME);
        objContentValues.put(COLUMN_SUBJECT_STATUS,intStatus);
        writeSQLite.insert(SUBJECT_TABLE,null,objContentValues);

    }//addValueToSubject

    public void updateValueToSubject(String strupID, String strupName, String strupStatus) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_ID,strupID);
        values.put(COLUMN_SUBJECT_NAME, strupName);
        values.put(COLUMN_SUBJECT_STATUS,strupStatus);
        writeSQLite.update(SUBJECT_TABLE, values, COLUMN_SUBJECT_ID + "=?", new String[]{strupID});
        writeSQLite.close();

    }


}//mainclass
