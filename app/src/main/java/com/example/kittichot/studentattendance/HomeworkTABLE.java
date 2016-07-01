package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 21/4/2559.
 */
public class HomeworkTABLE {
    private MyOpenHelper objMyOpenHelper;
    private DateThai objDateThai;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private static final String TABLE_Homework = "homeworkTABLE";
    private static final String COLUMN_ID_Homework = "_homework_id";
    private static final String COLUMN_TITLE_Homework = "homework_title";
    private static final String COLUMN_DETAILS_Homework = "homework_details";
    private static final String COLUMN_SAVEDATE_Homework = "homework_savedate";
    private static final String COLUMN_SENTDATE_Homework = "homework_datesent";
    //private static final String COLUMN_TERMID_Homework = "term_id";
    private static final String COLUMN_STATUS_Homework = "homework_status";

    public HomeworkTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
        objDateThai = new DateThai();


    }//constructor
    public String[] listID() {
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework},
                COLUMN_STATUS_Homework + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listID

    public String[] listTitle() {
        String strlistTitle[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_TITLE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistTitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistTitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_Homework));
            cursor.moveToNext();

        }//for
            cursor.close();
            return strlistTitle;
    }//listTiTle
    public  String[] listDetail() {
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_DETAILS_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//listDetail

    public  String[] listDatesave() {
        String strlistDatesave[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SAVEDATE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistDatesave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesave[i] =objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVEDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesave;
    }//listDateSave

    public  String[] listDatesent() {
        String strlistDatesent[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SENTDATE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistDatesent = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesent[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SENTDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesent;
    }//listDateSent
    public String[] listIDHide() {
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listID
    public String[] listTitleHide() {
        String strlistTitle[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_TITLE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistTitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistTitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_Homework));
            cursor.moveToNext();

        }//for
            cursor.close();
            return strlistTitle;
    }//listTiTle
    public  String[] listDetailHide() {
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_DETAILS_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//listDetail

    public  String[] listDatesaveHide() {
        String strlistDatesave[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SAVEDATE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistDatesave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesave[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVEDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesave;
    }//listDateSave

    public  String[] listDatesentHide() {
        String strlistDatesent[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SENTDATE_Homework}, COLUMN_STATUS_Homework + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistDatesent = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesent[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SENTDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesent;
    }//listDateSent

    public String[] listIDHW(String s) {
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework}, COLUMN_SENTDATE_Homework+ "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listID


    public String[] listTitleHW(String s) {
        String strlistTitle[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_TITLE_Homework}, COLUMN_SENTDATE_Homework + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistTitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistTitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_Homework));
            cursor.moveToNext();

        }//for
            cursor.close();
            return strlistTitle;
    }//listTiTle


    public  String[] listDetailHW(String s) {
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_DETAILS_Homework}, COLUMN_SENTDATE_Homework + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//listDetail

    public  String[] listDatesaveHW(String s) {
        String strlistDatesave[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SAVEDATE_Homework}, COLUMN_SENTDATE_Homework + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistDatesave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesave[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVEDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesave;
    }//listDateSave

    public  String[] listDatesentHW(String s) {
        String strlistDatesent[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SENTDATE_Homework},COLUMN_SENTDATE_Homework + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistDatesent = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesent[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SENTDATE_Homework)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesent;
    }//listDateSentHW

    public String[] listIDHWbackup() {
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listIDHWbackup

    public String[] listTitleHWbackup() {
        String strlistTitle[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_TITLE_Homework}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistTitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistTitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_Homework));
            cursor.moveToNext();

        }//for
            cursor.close();
            return strlistTitle;
    }//listTiTleHWbackup


    public  String[] listDetailHWbackup() {
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_DETAILS_Homework}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//listDetailHWbackup

    public  String[] listDatesaveHWbackup() {
        String strlistDatesave[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SAVEDATE_Homework}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistDatesave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesave[i] = cursor.getString(cursor.getColumnIndex(COLUMN_SAVEDATE_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesave;
    }//listDateSaveHWbackup

    public  String[] listDatesentHWbackup() {
        String strlistDatesent[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SENTDATE_Homework},  null,null, null, null, null);
        cursor.moveToFirst();
        strlistDatesent = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesent[i] = cursor.getString(cursor.getColumnIndex(COLUMN_SENTDATE_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesent;
    }//listDateSentHWbackup


    public  String[] listStatusHWbackup() {
        String strlistDatesent[] = null;
        Cursor cursor = readSQLite.query(TABLE_Homework, new String[]{COLUMN_ID_Homework,
                COLUMN_SENTDATE_Homework,COLUMN_STATUS_Homework}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistDatesent = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDatesent[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_Homework));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDatesent;
    }//listStatusHWbackup
    public long addValueToHomework(String strTitle, String strDetail,String datesave,String datesent, int strStatus) {
        ContentValues objContentValues = new ContentValues();
        //objContentValues.put(COLUMN_ID_Homework, intID);
        objContentValues.put(COLUMN_TITLE_Homework, strTitle);
        objContentValues.put(COLUMN_DETAILS_Homework, strDetail);
        objContentValues.put(COLUMN_SAVEDATE_Homework,datesave);
        objContentValues.put(COLUMN_SENTDATE_Homework, datesent);
        //objContentValues.put(COLUMN_TERMID_Homework, strTermID);
        objContentValues.put(COLUMN_STATUS_Homework, strStatus);
        return writerSQLite.insert(TABLE_Homework, null, objContentValues);
    }//addvalue
    public long updateValueToHomework(int intID, String strTitle, String strDetail,String datesave,String datesent, int strStatus) {
        ContentValues objContentValues = new ContentValues();
        //objContentValues.put(COLUMN_ID_Homework, intID);
        objContentValues.put(COLUMN_TITLE_Homework, strTitle);
        objContentValues.put(COLUMN_DETAILS_Homework, strDetail);
        objContentValues.put(COLUMN_SAVEDATE_Homework, datesave);
        objContentValues.put(COLUMN_SENTDATE_Homework, datesent);
        //objContentValues.put(COLUMN_TERMID_Homework, strTermID);
        objContentValues.put(COLUMN_STATUS_Homework, strStatus);
        return writerSQLite.update(TABLE_Homework, objContentValues,COLUMN_ID_Homework+"=?",new String[]{String.valueOf(intID)});
    }//updatevalue


}
