package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 10/6/2559.
 */
public class HomeworksendingTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private final String HOMEWORKSENDING_TABLE = "homeworksendingTABLE";
    private final String COLUMN_ID_HOMEWORKSENDING = "_hwsent_id";
    private final String COLUMN_ID_HOMEWORK = "homework_id";
    private final String COLUMN_ID_REGISTER = "register_id";
    private final String COLUMN_DATESENT_HOMEWORKSENDING = "hwsent_datesent";
    private final String COLUMN_STATUS_HOMEWORKSENDING = "hwsent_status";


    public HomeworksendingTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }

    public String[] CheckHWSDIDFull(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(HOMEWORKSENDING_TABLE, new String[]{COLUMN_ID_HOMEWORKSENDING},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_HOMEWORKSENDING));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//ID
    public String[] CheckHWIDFull(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(HOMEWORKSENDING_TABLE, new String[]{COLUMN_ID_HOMEWORK},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_HOMEWORK));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//ID
    public String[] CheckHWSRegisIDFull(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(HOMEWORKSENDING_TABLE, new String[]{COLUMN_ID_REGISTER},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_ID_REGISTER));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//ID
    public String[] CheckHWSDateSendFull(){
    String strlistID[] = null;
    Cursor cursor = readSQLite.query(HOMEWORKSENDING_TABLE, new String[]{COLUMN_DATESENT_HOMEWORKSENDING},
            null, null, null,null, null);
    cursor.moveToFirst();
    strlistID = new String[cursor.getCount()];
    for (int i = 0; i < cursor.getCount(); i++) {
        strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_DATESENT_HOMEWORKSENDING));
        cursor.moveToNext();

    }//for
    cursor.close();
    return strlistID;
}//ID

    public String[] CheckHWSStatusFull(){
        String strlistID[] = null;
        Cursor cursor = readSQLite.query(HOMEWORKSENDING_TABLE, new String[]{COLUMN_STATUS_HOMEWORKSENDING},
                null, null, null,null, null);
        cursor.moveToFirst();
        strlistID = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strlistID[i] = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_HOMEWORKSENDING));
            cursor.moveToNext();

        }//for
        cursor.close();
        return strlistID;
    }//ID
    public long AddAttendanceHomework(int ID,int IDREGIS ,String DATE,String STATUS){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_HOMEWORK, ID);
        objContentValues.put(COLUMN_ID_REGISTER, IDREGIS);
        objContentValues.put(COLUMN_DATESENT_HOMEWORKSENDING, DATE);
        objContentValues.put(COLUMN_STATUS_HOMEWORKSENDING, STATUS);
        return writerSQLite.insert(HOMEWORKSENDING_TABLE, null, objContentValues);

    }
}
