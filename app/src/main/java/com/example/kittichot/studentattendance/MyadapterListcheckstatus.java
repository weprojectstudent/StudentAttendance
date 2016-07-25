package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kittichot on 10/7/2559.
 */
public class MyadapterListcheckstatus extends BaseAdapter {
    private Context objContext;
    private ArrayList<String> IDstudent,Namestudent,Surnamestudent,No,status;
    private String strstatusyes, strstatusno;


    public MyadapterListcheckstatus(Context objContext, ArrayList<String> IDstudent, ArrayList<String> namestudent, ArrayList<String> surnamestudent, ArrayList<String> no, ArrayList<String> status,String statusCheckyes,String statusCheckno) {
        this.objContext = objContext;
        this.IDstudent = IDstudent;
        this.Namestudent = namestudent;
        this.Surnamestudent = surnamestudent;
        this.No = no;
        this.status = status;
        this.strstatusyes = statusCheckyes;
        this.strstatusno = statusCheckno;
    }

    @Override
    public int getCount() {
        return IDstudent.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_row_studentstatus,parent,false);
        if (status.get(position).equals("1")) {
            view.setBackgroundResource(R.color.colorbgnotcome);
        } else {
            view.setBackgroundResource(R.color.colorbgcome);
        }
        TextView textView = (TextView) view.findViewById(R.id.textView52);
        textView.setText(IDstudent.get(position));

        //setup text name
        TextView txtView1 = (TextView) view.findViewById(R.id.textView53);
        txtView1.setText(Namestudent.get(position));

        TextView txtView2 = (TextView) view.findViewById(R.id.textView54);
        txtView2.setText(Surnamestudent.get(position));

        TextView txtView3 = (TextView) view.findViewById(R.id.textView57);
        txtView3.setText(No.get(position));

        TextView txtView4 = (TextView) view.findViewById(R.id.textView56);
        if (status.get(position).equals("1")) {
            txtView4.setText(strstatusno);
        } else {
            txtView4.setText(strstatusyes);
        }


        return view;
    }
}
