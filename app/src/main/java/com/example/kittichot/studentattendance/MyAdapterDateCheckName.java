package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kittichot on 26/6/2559.
 */
public class MyAdapterDateCheckName extends BaseAdapter{
    private Context context;
    private String[] DATETERMCHECK;

    public MyAdapterDateCheckName(Context context, String[] DATETERMCHECK) {
        this.context = context;
        this.DATETERMCHECK = DATETERMCHECK;
    }

    @Override
    public int getCount() {
        return DATETERMCHECK.length;
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
        View view = objLayoutInflater.inflate(R.layout.list_date_checkname, parent, false);
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }
        TextView txtViewTitle = (TextView) view.findViewById(R.id.textView40);
        txtViewTitle.setText(DATETERMCHECK[position]);


        return view;
    }
}
