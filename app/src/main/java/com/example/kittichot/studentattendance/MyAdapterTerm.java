package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kittichot on 9/4/2559.
 */

public class MyAdapterTerm extends BaseAdapter {
    private Context objContext;
    private String[] strTermID,strTeacher,strSubjectID2, strTermYear;
    public MyAdapterTerm(Context context, String[] strIDTerm, String[] strIDSubject,String[] strTermYear) {
        this.objContext = context;
        this.strTermID = strIDTerm;
       // this.strTeacher = strIDteacher;
        this.strSubjectID2 = strIDSubject;
        this.strTermYear = strTermYear;

    }

    @Override
    public int getCount() {
        return strTermID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_term, parent, false);
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }
        //setup text ID
        TextView textView = (TextView) view.findViewById(R.id.txtIDterm);
        textView.setText(strTermID[position]);

        //setup text name
        TextView txtView1 = (TextView) view.findViewById(R.id.txtIDsubject);
        txtView1.setText(strSubjectID2[position]);

        TextView txtView2 = (TextView) view.findViewById(R.id.txtTermyear);
        txtView2.setText(strTermYear[position]);

        return view;
    }
}
