package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kittichot on 3/8/2559.
 */
public class MyAdapterCheckname extends BaseAdapter{
    private Context context;
    private ArrayList<String> detailstudent;

    public MyAdapterCheckname(Context context, ArrayList<String> detailstudent) {
        this.context = context;
        this.detailstudent = detailstudent;
    }

    @Override
    public int getCount() {
        return detailstudent.size();
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
        View view = objLayoutInflater.inflate(R.layout.list_row_checkname, parent, false);

        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }

        TextView listViewsdetail = (TextView) view.findViewById(R.id.textView56);
        listViewsdetail.setText(detailstudent.get(position));
        return view;
    }
}
