package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kittichot on 3/6/2559.
 */
public class MyAdapterStudent extends BaseAdapter {
    private Context context;
    private String[] IDTERMREGIS;
    //private int[]    NUMLIST;

    public MyAdapterStudent(Context context, String[] IDTERMREGIS )/* int[] NUMLIST)*/ {
        this.context = context;
        this.IDTERMREGIS = IDTERMREGIS;
        //this.NUMLIST = NUMLIST;
    }

    @Override
    public int getCount() {
        return IDTERMREGIS.length;
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

        LayoutInflater objLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_student, parent, false);
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }
        TextView txtViewTitle = (TextView) view.findViewById(R.id.txtIDTERMREGIS);
        txtViewTitle.setText(IDTERMREGIS[position]);

        //TextView txtViewNUM = (TextView) view.findViewById(R.id.txtNUMREGIS);
        //txtViewNUM.setText(NUMLIST[position]);


        return view;
    }


}
