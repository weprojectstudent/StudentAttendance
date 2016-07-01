package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kittichot on 4/3/2016.
 */
public class MyAdapter extends BaseAdapter{
    //Explicit
    private Context objContext;
    private String[] strSubjectID, strSubjectName;


   // private int[] intMyTarget;

    public MyAdapter(Context context, String[] strIDSubject, String[] strNameSubject ) {
        this.objContext = context;
        this.strSubjectID = strIDSubject;
        this.strSubjectName = strNameSubject;
        //this.intMyTarget = targetID;
    }//constructor

    @Override
    public int getCount() {
        return strSubjectID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_row, parent, false);
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }
        //setup Textsubject
        TextView listViewsjID = (TextView) view.findViewById(R.id.showTitle);
        listViewsjID.setText(strSubjectID[position]);

        //setup text name
        TextView listviewName = (TextView) view.findViewById(R.id.showDetail);
        listviewName.setText(strSubjectName[position]);

        //setup img
        //ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        //imageView.setBackgroundResource(intMyTarget[position]);
        return view;
    }//getView

}//Main Class
