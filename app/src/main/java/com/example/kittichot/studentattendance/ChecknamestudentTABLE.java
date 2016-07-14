package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 10/6/2559.
 */
public class ChecknamestudentTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private SQLiteDatabase deleteSQLite;
    private final String CHECKNAMESTUDENT_TABLE = "checknamestudentTABLE";
    private final String COLUMN_ID_CHECKNAME = "_checkname_id";
    private final String COLUMN_ID_REGISTER = "register_id";
    private final String COLUMN_DATE_CHECKNAME = "checkname_date";
    private final String COLUMN_STATUS_CHECKNAME = "checkname_status";
    private DateThai objDateThai;

    public ChecknamestudentTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
        objDateThai = new DateThai();

    }

    public String[] ChecknameID(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_ID_CHECKNAME},
                 null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_CHECKNAME));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//ID


    public String[] ChecknameIDREGIS(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_ID_REGISTER},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_REGISTER));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS

    public String[] ChecknameDATE(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_DATE_CHECKNAME},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = objDateThai.dateThaiUPloadValue(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CHECKNAME)));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS

    public String[] ChecknameDATEShowList(String idregis){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_DATE_CHECKNAME},
                COLUMN_ID_REGISTER+"=?",new String[]{idregis}, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CHECKNAME));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS

    public String[] ChecknameSTATUS(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_STATUS_CHECKNAME},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_CHECKNAME));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS
    public String[] ChecknameSTATUSFordate(String id,String date){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_STATUS_CHECKNAME},
                COLUMN_ID_REGISTER+"=?"+" AND "+COLUMN_DATE_CHECKNAME+"=?", new String[]{id,date}, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_CHECKNAME));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS
    public String[] ChecknameIDQuery(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(true,CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_DATE_CHECKNAME},
                null, null, null,null,null,null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CHECKNAME));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS

    public String[] ChecknameIDC(String c,String b){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(true,CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_ID_REGISTER},
                COLUMN_ID_REGISTER+"=?"+" AND "+COLUMN_DATE_CHECKNAME+"=?",new String[]{c,b}, null,null,null,null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_REGISTER));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS
    public String[] IDregisFordate(String date,String id){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(true,CHECKNAMESTUDENT_TABLE, new String[]{COLUMN_ID_REGISTER},
                COLUMN_DATE_CHECKNAME+"=?"+" AND "+COLUMN_ID_REGISTER+"=?",new String[]{date,id}, null,null,null,null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_REGISTER));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//IDREGIS


    public long addValueCheckname(int register_id,String checkname_date,int checkname_status){
        ContentValues objContentValues = new ContentValues();
        //objContentValues.put(COLUMN_ID_CHECKNAME,checkname_id);
        objContentValues.put(COLUMN_ID_REGISTER, register_id);
        objContentValues.put(COLUMN_DATE_CHECKNAME, checkname_date);
        objContentValues.put(COLUMN_STATUS_CHECKNAME, checkname_status);
        return writerSQLite.insert(CHECKNAMESTUDENT_TABLE, null, objContentValues);

    }//addValueCheckname

    public long updateValueCheckname(int register_id,String checkname_date,int checkname_status){
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID_CHECKNAME,checkname_id);
        //values.put(COLUMN_ID_REGISTER,register_id);
        //values.put(COLUMN_DATE_CHECKNAME,checkname_date);
        values.put(COLUMN_STATUS_CHECKNAME,checkname_status);
       return writerSQLite.update(CHECKNAMESTUDENT_TABLE, values,
                COLUMN_ID_REGISTER + "=?"+" AND "+COLUMN_DATE_CHECKNAME+"=?",
                new String[]{String.valueOf(register_id),checkname_date});
        //writerSQLite.close();
    }

}//main
