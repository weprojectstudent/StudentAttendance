package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 10/6/2559.
 */
public class AlertTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private final String ALERT_TABLE = "alertTABLE";
    private final String COLUMN_ID_ALERT = "_alert_id";
    private final String COLUMN_TITLE_ALERT = "alert_title";
    private final String COLUMN_DETAILS_ALERT = "alert_details";
    private final String COLUMN_SAVADATE_ALERT = "alert_savethedate";
    private final String COLUMN_DATEALERT_ALERT = "alert_dateofalert";
    private final String COLUMN_USERNAME_TEACHER_ALERT = "teacher_username";
    private final String COLUMN_STATUS_ALERT = "hwsent_status";
    private DateThai objDateThai;


    public AlertTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
        objDateThai = new DateThai();
    }//AlertTABLE

    public long addValueALERT( String title,String detail,String savethedate,String dateofalert,String teacher_username,int hwsent_status) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_TITLE_ALERT, title);
        objContentValues.put(COLUMN_DETAILS_ALERT, detail);
        objContentValues.put(COLUMN_SAVADATE_ALERT, savethedate);
        objContentValues.put(COLUMN_DATEALERT_ALERT, dateofalert);
        objContentValues.put(COLUMN_USERNAME_TEACHER_ALERT, teacher_username);
        objContentValues.put(COLUMN_STATUS_ALERT, hwsent_status);

        return writerSQLite.insert(ALERT_TABLE, null, objContentValues);
    }
    public long addValueALERTsyn(String id,String title,String detail,String savethedate,String dateofalert,String teacher_username,int hwsent_status) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_ALERT,id);
        objContentValues.put(COLUMN_TITLE_ALERT, title);
        objContentValues.put(COLUMN_DETAILS_ALERT, detail);
        objContentValues.put(COLUMN_SAVADATE_ALERT, savethedate);
        objContentValues.put(COLUMN_DATEALERT_ALERT, dateofalert);
        objContentValues.put(COLUMN_USERNAME_TEACHER_ALERT, teacher_username);
        objContentValues.put(COLUMN_STATUS_ALERT, hwsent_status);

        return writerSQLite.insert(ALERT_TABLE, null, objContentValues);
    }
    public void updateValueALERT(int ID,String title,String detail,String savethedate,String dateofalert,String teacher_username,int hwsent_status) {
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID_ALERT,ID);
        values.put(COLUMN_TITLE_ALERT,title);
        values.put(COLUMN_DETAILS_ALERT, detail);
        values.put(COLUMN_SAVADATE_ALERT, savethedate);
        values.put(COLUMN_DATEALERT_ALERT, dateofalert);
        values.put(COLUMN_USERNAME_TEACHER_ALERT, teacher_username);
        values.put(COLUMN_STATUS_ALERT, hwsent_status);

        writerSQLite.update(ALERT_TABLE, values, COLUMN_ID_ALERT + "=?", new String[]{String.valueOf(ID)});
        writerSQLite.close();

    }

    public String[] listIDHIDE(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_ID_ALERT}, COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listIDAlert

public String[] listTITLEHIDE(){
        String strlisttitle[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_TITLE_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
    strlisttitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlisttitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlisttitle;
    }//strlisttitleAlert

public String[] listDetailHIDE(){
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DETAILS_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
    strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//strlistDetailAlert
public String[] listDateSaveHIDE(){
        String strlistDateSave[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_SAVADATE_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
    strlistDateSave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDateSave[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVADATE_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDateSave;
    }//strlistDateSaveAlert
public String[] listDatealertHIDE(){
        String strlistdatealert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DATEALERT_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
    strlistdatealert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistdatealert[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_DATEALERT_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistdatealert;
    }//listIDAlert

    public String[] listStatusHIDE(){
        String strlistStatusalert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_STATUS_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"1"}, null, null, null);
        cursor.moveToFirst();
        strlistStatusalert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistStatusalert[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistStatusalert;
    }//listIDAlert

    public String[] listID(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_ID_ALERT}, COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listIDAlert

    public String[] listTITLE(){
        String strlisttitle[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_TITLE_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlisttitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlisttitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlisttitle;
    }//strlisttitleAlert

    public String[] listDetail(){
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DETAILS_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//strlistDetailAlert
    public String[] listDateSave(){
        String strlistDateSave[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_SAVADATE_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistDateSave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDateSave[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVADATE_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDateSave;
    }//strlistDateSaveAlert
    public String[] listDatealert(){
        String strlistdatealert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DATEALERT_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistdatealert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistdatealert[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_DATEALERT_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistdatealert;
    }//listIDAlert

    public String[] listStatus(){
        String strlistStatusalert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_STATUS_ALERT},  COLUMN_STATUS_ALERT + "=?",new String[]{"0"}, null, null, null);
        cursor.moveToFirst();
        strlistStatusalert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistStatusalert[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistStatusalert;
    }//listIDAlert

    public String[] listIDAlert(String s){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_ID_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listIDAlert

    public String[] listTITLEAlert(String s){
        String strlisttitle[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_TITLE_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlisttitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlisttitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlisttitle;
    }//strlisttitleAlert

    public String[] listDetailAlert(String s){
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DETAILS_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//strlistDetailAlert
    public String[] listDateSaveAlert(String s){
        String strlistDateSave[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_SAVADATE_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistDateSave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDateSave[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_SAVADATE_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDateSave;
    }//strlistDateSaveAlert
    public String[] listDatealertAlert(String s){
        String strlistdatealert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DATEALERT_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistdatealert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistdatealert[i] = objDateThai.fontDateThai(cursor.getString(cursor.getColumnIndex(COLUMN_DATEALERT_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistdatealert;
    }//listIDAlert

    public String[] listStatusAlert(String s){
        String strlistStatusalert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_STATUS_ALERT}, COLUMN_DATEALERT_ALERT + "=?",new String[]{s}, null, null, null);
        cursor.moveToFirst();
        strlistStatusalert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistStatusalert[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistStatusalert;
    }//listIDAlert

    public String[] listIDSYN(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_ID_ALERT}, null,null, null, null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//listIDAlert

    public String[] listTITLESYN(){
        String strlisttitle[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_TITLE_ALERT}, null, null, null, null, null);
        cursor.moveToFirst();
        strlisttitle = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlisttitle[i] = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlisttitle;
    }//strlisttitleAlert

    public String[] listDetailSYN(){
        String strlistDetail[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DETAILS_ALERT},  null, null, null, null, null);
        cursor.moveToFirst();
        strlistDetail = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDetail[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDetail;
    }//strlistDetailAlert
    public String[] listDateSaveSYN(){
        String strlistDateSave[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_SAVADATE_ALERT}, null, null, null, null, null);
        cursor.moveToFirst();
        strlistDateSave = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistDateSave[i] = objDateThai.dateThaiUPloadValue(cursor.getString(cursor.getColumnIndex(COLUMN_SAVADATE_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistDateSave;
    }//strlistDateSaveAlert
    public String[] listDatealertSYN(){
        String strlistdatealert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_DATEALERT_ALERT}, null, null, null, null, null);
        cursor.moveToFirst();
        strlistdatealert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistdatealert[i] = objDateThai.dateThaiUPloadValue(cursor.getString(cursor.getColumnIndex(COLUMN_DATEALERT_ALERT)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistdatealert;
    }//listIDAlert
    public String[] listTEACHERSYN(){
        String strlistStatusalert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_USERNAME_TEACHER_ALERT},  null, null, null, null, null);
        cursor.moveToFirst();
        strlistStatusalert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistStatusalert[i] = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME_TEACHER_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistStatusalert;
    }//listIDAlert

    public String[] listStatusSYN(){
        String strlistStatusalert[] = null;
        Cursor cursor = readSQLite.query(ALERT_TABLE, new String[]{COLUMN_STATUS_ALERT}, null, null, null, null, null);
        cursor.moveToFirst();
        strlistStatusalert = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistStatusalert[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_ALERT));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistStatusalert;
    }//listIDAlert
}//main
