package com.example.kittichot.studentattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelectedStudents extends ActionBarActivity {
    private TeachdetailTABLE objTeachdetailTABLE;
    private SubjectTABLE objSubjectTABLE;
    private RegisterTABLE objRegisterTABLE;
    private String[] IDTerm, RoomNO,YearList,namesubject,idSubjectterm;
    private String getIDTERM, getRoom,getYear,strIDteacher,getnamesubject;
    private Spinner objspinnerTERM, objspinnerROOM,objspinnerSubjectTerm;
    private String[] getNameA,getIDA,getSurnameA,getSomething;
    private StudentTABLE objStudentTABLE;

   // private  AlertDialog dialog;
    private Button button;
    //List<CharSequence> list = new ArrayList<CharSequence>();
    final ArrayList seletedItems=new ArrayList();

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_selected_students);
        objTeachdetailTABLE = new TeachdetailTABLE(this);
        objStudentTABLE = new StudentTABLE(this);
        objRegisterTABLE = new RegisterTABLE(this);
        objSubjectTABLE = new SubjectTABLE(this);
        //Bindwidget
        Bindwidget();
        //setAllArray
        setAllArray();


    }

    private void Create() {
        IDTerm = objTeachdetailTABLE.listTermIDslect(strIDteacher,getYear);
        idSubjectterm = objTeachdetailTABLE.listSubjectIDslect(strIDteacher,getYear);
        final ArrayList<String> arrayListnamesubject = new ArrayList<String>();
        for (int i = 0; i < IDTerm.length; i++) {
            namesubject = objSubjectTABLE.listName(idSubjectterm[i]);
            for (int i1=0;i1<namesubject.length;i1++)
                arrayListnamesubject.add(namesubject[i1]);
        }

        final ArrayList<String> spinnerArrayIDTERM = new ArrayList<String>();
        for (int i = 0; i < IDTerm.length; i++) {
            spinnerArrayIDTERM.add(IDTerm[i]);
        }//forIDTERM
        ArrayAdapter<String> arrayAdapterIDterm = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, arrayListnamesubject);
        objspinnerSubjectTerm.setAdapter(arrayAdapterIDterm);
        objspinnerSubjectTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getIDTERM = spinnerArrayIDTERM.get(position);
                getnamesubject = arrayListnamesubject.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//objspinnerTerm
        RoomNO = objStudentTABLE.RoomList();
        final ArrayList<String> arrayListRoom = new ArrayList<String>();
        for (int i = 0; i < RoomNO.length; i++) {
            arrayListRoom.add(RoomNO[i]);

        }//forRoom
        final ArrayAdapter<String> arrayAdapterRoom = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayListRoom);
        objspinnerROOM.setAdapter(arrayAdapterRoom);
        objspinnerROOM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getRoom = arrayListRoom.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAllArray() {
        strIDteacher = getIntent().getExtras().getString("Username");
        YearList = objTeachdetailTABLE.listTERMYearselect(strIDteacher);
        final ArrayList<String> arrayStringYear = new ArrayList<String>();
        for (int i = 0;i<YearList.length;i++ ) {
            arrayStringYear.add(YearList[i]);
        }
        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(
                this ,android.R.layout.simple_dropdown_item_1line,arrayStringYear);
        objspinnerTERM.setAdapter(arrayAdapterYear);
        objspinnerTERM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getYear = arrayStringYear.get(position);
                Create();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void Bindwidget() {
        objspinnerTERM = (Spinner) findViewById(R.id.spinnerTERM);
        objspinnerROOM = (Spinner) findViewById(R.id.spinnerROOM);
        objspinnerSubjectTerm = (Spinner) findViewById(R.id.spinnerSubjectterm);
        button = (Button) findViewById(R.id.button5);
    }
    public void IntentValueToSelectstudent( View view) {
        ArrayList<String> objStrings = new ArrayList<String>();
        getIDA = objStudentTABLE.ListIDStudent(getRoom);
        getNameA = objStudentTABLE.ListNameStudent(getRoom);
        getSurnameA = objStudentTABLE.ListSurNameStudent(getRoom);

        for (int i = 0; i < getIDA.length; i++) {
           // getIDstudent=getIDA[i] + " " + getNameA[i] + " " + getSurnameA[i];
            objStrings.add(getIDA[i] + " " + getNameA[i] + " " + getSurnameA[i]);

        }
        final CharSequence[] items = objStrings.toArray(new CharSequence[objStrings.size()]);

        AlertDialog dialog;
//following code will be in your activity.java file


        // arraylist to keep the selected items
        final ArrayList seletedItems=new ArrayList();
        //boolean[] booleen={true};
        final boolean[] b =null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("เลือกรายชื่อนักเรียน");
        builder.setMultiChoiceItems(items,b,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        ListView list = ((AlertDialog) dialog).getListView();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < list.getCount(); i++) {
                            boolean checked = list.isItemChecked(i);

                            if (checked) {
                                String s = list.getItemAtPosition(i).toString();

                                //MyAlertDialog myAlertDialog = new MyAlertDialog();
                                //myAlertDialog.errorDiaLog(SelectedStudents.this,s.substring(0,5),s.substring(5));
                                getSomething = objRegisterTABLE.ListRegisIDstudentandID(Integer.parseInt(s.substring(0,5)),getIDTERM);
                                if(getSomething.length >=1){
                                    MyAlertDialog myAlertDialog = new MyAlertDialog();
                                    myAlertDialog.errorDiaLog(SelectedStudents.this,s.substring(0,5),"มีในรายวิชานี้แล้ว");
                                   //dialog.dismiss();
                                }else {
                                    objRegisterTABLE.addValueRegister(getIDTERM, Integer.parseInt(s.substring(0, 5)), 0);
                                    if (stringBuilder.length() > 0) stringBuilder.append("\n");
                                    stringBuilder.append(list.getItemAtPosition(i));

                                }


                                //MyAlertDialog myAlertDialog = new MyAlertDialog();
                                //myAlertDialog.errorDiaLog(SelectedStudents.this,s.substring(0,5),String.valueOf(f));
                            }

                        }
                        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SelectedStudents.this);
                        myAlertDialog.setTitle("รายชื่อนักเรียนที่เพิ่มของวิชา " + getnamesubject);
                        myAlertDialog.setIcon(R.drawable.user);
                        myAlertDialog.setMessage(String.valueOf(stringBuilder));
                        myAlertDialog.setCancelable(false);
                        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        myAlertDialog.show();


                        /*Check string builder is empty or not. If string builder is not empty.
                          It will display on the screen.
                         */



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel

                    }
                });

        dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();



    }

}
