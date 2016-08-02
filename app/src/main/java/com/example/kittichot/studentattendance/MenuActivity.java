package com.example.kittichot.studentattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MenuActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private String putExtraNAme, putExtraUSer;
    private ListView objListView;
    private Spinner objSpinner;
    private HomeworkTABLE objHomeworkTABLE;
    private String[]strHWID,strHWTT,strHWDT,strDateSave, strDatesent;
    private String getstrHWID,getstrHWTT,getstrHWDT,getstrDateSave, getstrDatesent,getstr;
    private int d, m, y,b;
    private String[] strIDAlert,strTitleAlert,strDetailAlert,strDATEsaveAlert,strDateAlert, strStatusAlert;
    private AlertTABLE objAlertTABLE;
    private DateThai objDateThai;
    private TextView objTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo2);
        actionBar.setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_menu2);
        objHomeworkTABLE = new HomeworkTABLE(this);
        objAlertTABLE = new AlertTABLE(this);
        objDateThai = new DateThai();
        objTextView = (TextView) findViewById(R.id.textView45);
        String strTextShowUser = getIntent().getExtras().getString("Username");
        objTextView.setText(strTextShowUser);

        createListSpinner();
        //createListView();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }//onCreate

        public void onBackPressed(){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Exit");
            dialog.setIcon(R.drawable.alerticon);
            dialog.setCancelable(true);
            dialog.setMessage("Do you want to exit?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            dialog.show();


        }




    private void createListSpinner() {
        objSpinner = (Spinner) findViewById(R.id.spinnerMenuA);
        final ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("กำหนดส่งการบ้านวันนี้");
        spinnerArray.add("บันทึกแจ้งเตือน");

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,spinnerArray);
        objSpinner.setAdapter(adapterSpinner);
        objSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b = position;
                createListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void createListPUTEXTRA() {
        String strTextShow = getIntent().getExtras().getString("Name");
        String strTextShowUser = getIntent().getExtras().getString("Username");
        putExtraNAme = strTextShow;
        putExtraUSer = strTextShowUser;
    }

    private void createListView() {
        objListView = (ListView) findViewById(R.id.listViewHWA);
        if (b == 0) {
            MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(this, strHWID, strHWTT, strHWDT, strDateSave, strDatesent);
            objListView.setAdapter(objMyAdapterHomework);
           MenuActivity.this.onResume();
        } else {
            MyAdapterHomework objMyAdapterHomework = new MyAdapterHomework(this, strIDAlert, strTitleAlert, strDetailAlert, strDATEsaveAlert, strDateAlert);
            objListView.setAdapter(objMyAdapterHomework);
            MenuActivity.this.onResume();
        }
    }//createListview

    private void setupAllArray() {
        Calendar calendar = Calendar.getInstance();
        d = calendar.get(Calendar.DAY_OF_MONTH);
        m = 1+calendar.get(Calendar.MONTH);
        y = calendar.get(Calendar.YEAR);
        getstr = y + "-" + m + "-"+d;
        strHWID = objHomeworkTABLE.listIDHW(objDateThai.dateThaiSelect(getstr));
        strHWTT = objHomeworkTABLE.listTitleHW(objDateThai.dateThaiSelect(getstr));
        strHWDT = objHomeworkTABLE.listDetailHW(objDateThai.dateThaiSelect(getstr));
        strDateSave = objHomeworkTABLE.listDatesaveHW(objDateThai.dateThaiSelect(getstr));
        strDatesent = objHomeworkTABLE.listDatesentHW(objDateThai.dateThaiSelect(getstr));

        //alert
        strIDAlert = objAlertTABLE.listIDAlert(objDateThai.dateThaiSelect(getstr));
        strTitleAlert = objAlertTABLE.listTITLEAlert(objDateThai.dateThaiSelect(getstr));
        strDetailAlert = objAlertTABLE.listDetailAlert(objDateThai.dateThaiSelect(getstr));
        strDATEsaveAlert = objAlertTABLE.listDateSaveAlert(objDateThai.dateThaiSelect(getstr));
        strDateAlert = objAlertTABLE.listDatealertAlert(objDateThai.dateThaiSelect(getstr));
        strStatusAlert = objAlertTABLE.listStatusAlert(objDateThai.dateThaiSelect(getstr));

    }//setupAllArray
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section0);
                break;
           /* case 2:
                mTitle = getString(R.string.title_section1);
                Intent intent0 = new Intent(this,MenuActivity.class);
                createListPUTEXTRA();
                intent0.putExtra("Name", putExtraNAme);
                intent0.putExtra("Username", putExtraUSer);

                startActivity(intent0);
                break;*/
            case 2:
                mTitle = getString(R.string.title_section2);
                Intent intent1 = new Intent(this,AddSubjectActivity.class);
                createListPUTEXTRA();
                intent1.putExtra("Name", putExtraNAme);
                intent1.putExtra("Username", putExtraUSer);
                startActivity(intent1);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                createListPUTEXTRA();
                String strTextShow = getIntent().getExtras().getString("Name");
                String strTextShowUser = getIntent().getExtras().getString("Username");
                putExtraNAme = strTextShow;
                putExtraUSer = strTextShowUser;
                Intent intent3 = new Intent(this, Term_Manager.class);
                intent3.putExtra("Name", putExtraNAme);
                intent3.putExtra("Username", putExtraUSer);
                startActivity(intent3);
                break;
            case 4:  mTitle = getString(R.string.title_section4);
                createListPUTEXTRA();
                Intent intent5 = new Intent(this, SelectListStudent.class);
                intent5.putExtra("Name", putExtraNAme);
                intent5.putExtra("Username", putExtraUSer);
                startActivity(intent5);
                break;
            case 5: mTitle = getString(R.string.title_section5);
                createListPUTEXTRA();
                Intent intent6 = new Intent(this, Homework_manament.class);
                intent6.putExtra("Name", putExtraNAme);
                intent6.putExtra("Username", putExtraUSer);
                startActivity(intent6);
                break;
            case 6: mTitle = getString(R.string.title_section6);
                createListPUTEXTRA();
                Intent intent = new Intent(MenuActivity.this, SelectTermToCheckname.class);
                intent.putExtra("Username", putExtraUSer);
                startActivity(intent);
                break;
            case 7: mTitle = getString(R.string.title_section7);
                createListPUTEXTRA();
                Intent intent8 = new Intent(MenuActivity.this, SelectHomeWork.class);
                intent8.putExtra("Username", putExtraUSer);
                startActivity(intent8);
                break;
            case 8: mTitle = getString(R.string.title_section8);
                createListPUTEXTRA();
                Intent intent9 = new Intent(MenuActivity.this, AlertManagement.class);
                intent9.putExtra("Name", putExtraNAme);
                intent9.putExtra("Username", putExtraUSer);
                startActivity(intent9);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MenuActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupAllArray();
    }
}
