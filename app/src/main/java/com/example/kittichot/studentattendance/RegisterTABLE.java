package com.example.kittichot.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kittichot on 4/6/2559.
 */
public class RegisterTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writerSQLite;
    private SQLiteDatabase readSQLite;
    private static final String REGISTER_TABLE = "registerTABLE";
    private static final String COLUMN_ID_REGISTER = "_register_id";
    private static final String COLUMN_TERMID_REGISTER = "term_id";
    private static final String COLUMN_STUDENTID_REGISTER = "Student_id";
    private static final String COLUMN_STATUS_REGISTER = "register_status";


    public RegisterTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writerSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }
    public String[] ListRegisID( ) {
        String listRegisID[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_ID_REGISTER}, COLUMN_STATUS_REGISTER+"=?",
                new String[]{"0"}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisID = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
            return listRegisID;
    }//LISTREGISTERID
    public String[] RegisIDStudent(String IDStudent,String TERM) {
        String listRegisIDStudent[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_STUDENTID_REGISTER}, COLUMN_STUDENTID_REGISTER+"=?"+" AND "+COLUMN_TERMID_REGISTER+"=?"
                ,new String[]{IDStudent,TERM}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisIDStudent = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisIDStudent[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STUDENTID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisIDStudent;
    }//LISTREGISTERIDSTUDENT
    public String[] ListRegisIDTerm( ) {
        String listRegisIDTerm[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_TERMID_REGISTER}, COLUMN_STATUS_REGISTER+"=?",
                new String[]{"0"}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisIDTerm = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisIDTerm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERMID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisIDTerm;
    }//LISTREGISTERIDTerm
    public String[] ListRegisStatus( ) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_STATUS_REGISTER}, COLUMN_STATUS_REGISTER+"=?",
                new String[]{"0"}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STATUS_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID
    public String[] ListRegisTerm() {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_TERMID_REGISTER}, COLUMN_STATUS_REGISTER+"=?",
                new String[]{"0"}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERMID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID

    public String[] ListRegisIDstudent(String strings) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_STUDENTID_REGISTER}, COLUMN_TERMID_REGISTER+"=?",
               new String[]{strings}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STUDENTID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID


    public String[] ListRegisIDstudentandterm(int strings,String s) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_ID_REGISTER},COLUMN_STUDENTID_REGISTER+"=?"+" AND "+COLUMN_TERMID_REGISTER+"=?",
                new String[]{String.valueOf(strings),s}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID
    public String[] ListRegisIDforterm(String s) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_ID_REGISTER},COLUMN_TERMID_REGISTER+"=?",
                new String[]{s}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID

    public String[] ListRegisIDstudentandID(int strings,String s) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_STUDENTID_REGISTER,COLUMN_TERMID_REGISTER},COLUMN_STUDENTID_REGISTER+"=?"+" AND "+COLUMN_TERMID_REGISTER+"=?",
                new String[]{String.valueOf(strings),s}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STUDENTID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID

    public String[] ListRegisIDFULL( ) {
        String listRegisID[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_ID_REGISTER}, null, null, null, null,null, null);
        objCursor.moveToFirst();
        listRegisID = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisID;
    }//LISTREGISTERID
    public String[] ListRegisIDStudentFULL() {
        String listRegisIDStudent[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_STUDENTID_REGISTER},  null, null, null, null, null, null);
        objCursor.moveToFirst();
        listRegisIDStudent = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisIDStudent[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STUDENTID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisIDStudent;
    }//LISTREGISTERIDSTUDENT
    public String[] ListRegisIDTermFULL( ) {
        String listRegisIDTerm[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_TERMID_REGISTER}, null, null, null, null, null, null);
        objCursor.moveToFirst();
        listRegisIDTerm = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisIDTerm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERMID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisIDTerm;
    }//LISTREGISTERIDTerm
    public String[] ListRegisStatusFULL( ) {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(REGISTER_TABLE,
                new String[]{COLUMN_STATUS_REGISTER}, null, null, null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_STATUS_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID
    public String[] ListRegisIDTERM() {
        String listRegisStatus[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_ID_REGISTER}, COLUMN_TERMID_REGISTER+"=?",new String[]{"เทอม 1 ม.3"} , null, null, null, null);
        objCursor.moveToFirst();
        listRegisStatus = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisStatus[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisStatus;
    }//LISTREGISTERID

    public String[] ListRegisIDTermforYear(String id) {
        String listRegisIDTerm[] = null;
        Cursor objCursor = readSQLite.query(true,REGISTER_TABLE,
                new String[]{COLUMN_TERMID_REGISTER}, COLUMN_STATUS_REGISTER+"=?"+" AND "+COLUMN_TERMID_REGISTER+"=?",
                new String[]{"0",id}, null, null, null, null);
        objCursor.moveToFirst();
        listRegisIDTerm = new String[objCursor.getCount()];
        for (int i = 0; i <objCursor.getCount(); i++) {
            listRegisIDTerm[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TERMID_REGISTER));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return listRegisIDTerm;
    }//LISTREGISTERIDTerm
    public long addValueRegister( String TERMID,int IDSTUDENT ,int Status) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_TERMID_REGISTER, TERMID);
        objContentValues.put(COLUMN_STUDENTID_REGISTER, IDSTUDENT);
        objContentValues.put(COLUMN_STATUS_REGISTER, Status);

        return writerSQLite.insert(REGISTER_TABLE, null, objContentValues);
    } // addValueStudent()

    public void Delete(String id){
        writerSQLite.delete(REGISTER_TABLE, COLUMN_ID_REGISTER+"=?",new String[]{id});

    }
}
