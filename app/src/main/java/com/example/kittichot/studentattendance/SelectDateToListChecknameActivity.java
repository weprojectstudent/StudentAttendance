package com.example.kittichot.studentattendance;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class SelectDateToListChecknameActivity extends ActionBarActivity {
    private ListView objListView;
    private MyAdapterDateCheckName objMyAdapterDateCheckName;
    private String getIDTERM;
    private String[] getDateForTerm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_to_list_checkname);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        bindwidget();
        getIntentString();

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getIntentString() {

    }

    private void bindwidget() {

        objListView = (ListView) findViewById(R.id.listViewDateTolistCheckName);

    }
}
